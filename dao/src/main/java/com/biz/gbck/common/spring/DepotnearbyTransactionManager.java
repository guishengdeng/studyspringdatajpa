package com.biz.gbck.common.spring;

import com.biz.gbck.vo.mq.MQMessage;
import org.codelogger.utils.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.support.DefaultTransactionStatus;

import java.util.ArrayList;
import java.util.List;

import static com.google.common.collect.Lists.newArrayList;
import static org.codelogger.utils.ValueUtils.getValue;


public class DepotnearbyTransactionManager extends JpaTransactionManager
    implements ApplicationEventPublisherAware {

    private static final Logger logger =
        LoggerFactory.getLogger(DepotnearbyTransactionManager.class);


    protected static ApplicationEventPublisher applicationEventPublisher;

    public static final ThreadLocal<List<ApplicationEvent>> events =
        new ThreadLocal<List<ApplicationEvent>>();

    public static final ThreadLocal<List<Task>> tasks =
        new ThreadLocal<List<Task>>();

    public static final ThreadLocal<List<MQMessage>> mqMsgs = new ThreadLocal<>();

    public static final ThreadLocal<Boolean> inTransactions = new ThreadLocal<Boolean>();

    public DepotnearbyTransactionManager() {
        super();
    }

    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    protected void doBegin(Object transaction, TransactionDefinition definition) {
        super.doBegin(transaction, definition);
        inTransactions.set(true);
    }

   /* protected void doCommit(DefaultTransactionStatus status) {
        super.doCommit(status);
        boolean isDebugEnabled = logger.isDebugEnabled();
        List<ApplicationEvent> list = events.get();
        if (list != null && !list.isEmpty()) {
            for (ApplicationEvent event : list) {
                if (isDebugEnabled) {
                    logger.debug("applicationEventPublisher.publishEvent {}, hashcode {},ts {}",
                        event.getClass().getCanonicalName(), event.hashCode(),
                        event.getTimestamp());
                }
                applicationEventPublisher.publishEvent(event);
            }
            list.clear();
        }
        List<MQMessage> messages = mqMsgs.get();
        if(CollectionUtils.isNotEmpty(messages)){
            MQService mqService = getMQService();
            for (MQMessage message : messages) {
                try {
                    mqService.sendMessage(message);
                } catch (Exception e) {
                    logger.warn("Send message:{} to '{}' failed.", message.getQueueName(), message.getData(), e);
                }
            }
            messages.clear();
        }
        List<Task> tasks = DepotnearbyTransactionManager.tasks.get();
        if(CollectionUtils.isNotEmpty(tasks)){
            for (Task task : tasks) {
                try {
                    task.justDoIt();
                } catch (Throwable e) {
                    logger.warn("Do task failed.", e);
                }
            }
            tasks.clear();
        }
        inTransactions.set(false);
    }
*/
    protected void doRollback(DefaultTransactionStatus status) {
        super.doRollback(status);
        List<ApplicationEvent> list = events.get();
        if (list != null && !list.isEmpty()) {
            if (logger.isDebugEnabled()) {
                logger.debug("rooback && clear event");
            }
            list.clear();
        }
        List<MQMessage> messages = mqMsgs.get();
        if(CollectionUtils.isNotEmpty(messages)){
            messages.clear();
        }
        List<Task> tasks = DepotnearbyTransactionManager.tasks.get();
        if(CollectionUtils.isNotEmpty(tasks)){
            tasks.clear();
        }
        inTransactions.set(false);
    }


    public static void publishEvent(ApplicationEvent event) {
        Boolean transFlag = inTransactions.get();
        if (transFlag != null && transFlag.booleanValue()) {
            List<ApplicationEvent> list = events.get();
            if (list == null) {
                list = new ArrayList<ApplicationEvent>();
                events.set(list);
            }
            if (logger.isDebugEnabled()) {
                logger.debug("thread is in Transcation send push envet to list");
            }
            list.add(event);
        } else {
            if (logger.isDebugEnabled()) {
                logger.debug(
                    "thread is not in Transcation send event now! applicationEventPublisher is : {} event is:{} ",
                    applicationEventPublisher, event);
            }
            applicationEventPublisher.publishEvent(event);
        }
    }

    public static void doWhenTransactionalSuccess(Task task) {

        if(task == null){
            return;
        }
        Boolean transFlag = inTransactions.get();
        if(getValue(transFlag)) {
            List<Task> tasks = DepotnearbyTransactionManager.tasks.get();
            if (tasks == null) {
                tasks = newArrayList();
            }
            tasks.add(task);
            DepotnearbyTransactionManager.tasks.set(tasks);
        } else {
            task.justDoIt();
        }
    }

    /*public static void sendMessage(MQMessage mqMessage) throws CommonException {
        Boolean transFlag = getValue(inTransactions.get());
        if(transFlag){
            List<MQMessage> messages = mqMsgs.get();
            if(messages == null){
                messages = newArrayList();
                mqMsgs.set(messages);
            }
            messages.add(mqMessage);
        } else {
            getMQService().sendMessage(mqMessage);
        }

    }

    private static MQService getMQService(){

        MQService mqService = SpringContextUtil.getBean(MQService.class);
        if(mqService == null){
            mqService = (MQService) SpringContextUtil.getBean("mqService");
        }
        return mqService;
    } */

    public interface Task {

        /**
         * Just do IT... 所以有多少人成为了IT男??
         */
        void justDoIt();
    }
}
