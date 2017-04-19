package com.biz.core.util;

import com.google.common.collect.Maps;
import java.util.Map;
import org.apache.commons.lang3.time.StopWatch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 性能计时器
 *
 * @author yanweijin
 * @usage
 * @reviewer
 * @since 2016年10月13日
 */
public class Timers {
    private static final Logger logger = LoggerFactory.getLogger(Timers.class);
    private StopWatch sw = new StopWatch();
    private Map<String, Long> map = Maps.newLinkedHashMap();
    private long preTime = 0;
    private boolean enabled = true;

    private Timers() {
    }

    public static Timers createAndBegin(boolean enabled) {
        Timers timers = new Timers();
        timers.enabled = enabled;
        if (!enabled) return timers;
        timers.sw.start();
        return timers;
    }

    public void record(String tag) {
        if (!enabled) return;
        sw.split();
        long splitTime = sw.getSplitTime();
        long sub = splitTime - preTime;
        //		time.add(sub);
        map.put(tag, sub);
        preTime = splitTime;
    }

    private Map<String, Long> out() {
        if (!enabled) return map;
        sw.stop();
        long all = sw.getTime();
        map.put("总计用时", all);
        return map;
    }

    public void print(String tag) {
        if (!enabled) return;
        logger.debug(tag + " {}", this.out());
    }
}
