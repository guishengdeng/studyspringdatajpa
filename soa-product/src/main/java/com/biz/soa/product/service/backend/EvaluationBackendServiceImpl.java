package com.biz.soa.product.service.backend;

import com.biz.core.page.PageResult;
import com.biz.core.util.DateUtil;
import com.biz.core.util.ExecutionUnit;
import com.biz.gbck.dao.mysql.po.product.Evaluation;
import com.biz.gbck.dao.mysql.repository.evaluation.EvaluationRepository;
import com.biz.gbck.dao.mysql.specification.EvaluationSpecification;
import com.biz.gbck.dao.redis.repository.product.EvaluationRedisDao;
import com.biz.gbck.dao.redis.ro.product.EvaluationRo;
import com.biz.gbck.enums.CommonStatusEnum;
import com.biz.gbck.enums.user.ChannelType;
import com.biz.gbck.exceptions.DepotNextDoorException;
import com.biz.gbck.exceptions.evaluation.EvaluationNotExistException;
import com.biz.gbck.exceptions.product.IllegalParameterException;
import com.biz.gbck.vo.evaluation.backend.EvaluationAuditRequestVo;
import com.biz.gbck.vo.evaluation.backend.EvaluationDetailVo;
import com.biz.gbck.vo.evaluation.backend.EvaluationQueryRequestVo;
import com.biz.gbck.vo.evaluation.backend.EvaluationResponseVo;
import com.biz.gbck.vo.evaluation.frontend.VendorIdPageRequestVo;
import com.biz.gbck.vo.user.MemberIdRequestVo;
import com.biz.gbck.vo.user.UserResponseVo;
import com.biz.gbck.vo.vendor.VendorQueryRequestVo;
import com.biz.gbck.vo.vendor.VendorVo;
import com.biz.service.AbstractBaseService;
import com.biz.service.evaluation.EvaluationBackendService;
import com.biz.service.user.UserService;
import com.biz.service.vendor.VendorBackendService;
import com.biz.gbck.transform.product.Evaluation2EvaluationDetailVo;
import com.biz.gbck.transform.product.Evaluation2EvaluationResponseVo;
import com.biz.gbck.transform.product.Evaluation2EvaluationRo;
import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import java.util.List;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 评论serviceImpl
 *
 * @author yangzichun
 * @date 2017/2/8
 */
@Service
public class EvaluationBackendServiceImpl extends AbstractBaseService implements EvaluationBackendService {
    @Autowired
    protected UserService userService;
    @Autowired
    private EvaluationRepository evaluationRepository;
    @Autowired
    private EvaluationRedisDao evaluationRedisDao;
    @Autowired
    private VendorBackendService vendorBackendService;


    /**
     * 根据分页请求分页查询评价 vo
     *
     * @param reqVo 分页请求 vo
     * @return 评价分页结果
     * @throws IllegalParameterException 参数不合法异常
     */
    @Override
    public PageResult<EvaluationResponseVo> findEvaluations(EvaluationQueryRequestVo reqVo) throws DepotNextDoorException {
        if (reqVo == null) {
            throw new IllegalParameterException("分页参数不合法");
        }
        PageRequest pageRequest = new PageRequest(reqVo.getPage(), reqVo.getSize());
        //根据参数查询
        Page<Evaluation> page = evaluationRepository.findAll(new EvaluationSpecification(reqVo), pageRequest);
        if (page == null) {
            return new PageResult<>(reqVo.getPage(), reqVo.getSize(), 0, Lists.<EvaluationResponseVo>newArrayList());
        }
        return new PageResult<>(reqVo.getPage(), page.getSize(), (int) page.getTotalElements(), Lists.transform(page
                .getContent(), new Evaluation2EvaluationResponseVo()));
    }

    @Override
    public EvaluationDetailVo findEvaluation(Long id) throws DepotNextDoorException {
        Evaluation evaluation = evaluationRepository.findOne(id);
        MemberIdRequestVo memberIdRequestVo = new MemberIdRequestVo();
        memberIdRequestVo.setMemberId(evaluation.getMemberId());
        memberIdRequestVo.setChannelCode(ChannelType.BBC.getCode());
        UserResponseVo userResponseVo = userService.findByMemberIdCondition(memberIdRequestVo);
        EvaluationDetailVo evaluationDetailVo = new Evaluation2EvaluationDetailVo().apply(evaluation);
        Preconditions.checkArgument(evaluationDetailVo != null);
        evaluationDetailVo.setAccountName(userResponseVo == null ? null : userResponseVo.getAccountName());
        evaluationDetailVo.setCommonStatus(evaluation.getCommonStatus().getValue() + "");
        return evaluationDetailVo;
    }

    @Override
    @Transactional
    public void auditEvaluation(EvaluationAuditRequestVo reqVo) throws DepotNextDoorException {
        //判断参数异常
        if (reqVo == null || reqVo.getId() == null) {
            CommonStatusEnum[] status = CommonStatusEnum.values();
            int[] statusValues = new int[status.length];
            for (int i = 0; i < status.length; i++) {
                statusValues[i] = status[i].getValue();
            }
            if (!ArrayUtils.contains(status, reqVo.getStatus()))
                System.out.println(ArrayUtils.contains(statusValues, reqVo.getStatus()));
            throw new IllegalParameterException("参数异常");
        }
        final Evaluation evaluation = evaluationRepository.findOne(reqVo.getId());
        if (evaluation == null) {
            throw new EvaluationNotExistException("未找到评论");
        }
        //修改评价po审核状态
        CommonStatusEnum status = reqVo.getStatus() == CommonStatusEnum.ENABLE.getValue() ? CommonStatusEnum.ENABLE : CommonStatusEnum.DISABLE;
        evaluation.setCommonStatus(status);
        EvaluationRo evaluationRo = null;
        if (evaluation.getCommonStatus() == CommonStatusEnum.ENABLE) { //评论审核通过
            MemberIdRequestVo memberIdRequestVo = new MemberIdRequestVo();
            memberIdRequestVo.setMemberId(evaluation.getMemberId());
            memberIdRequestVo.setChannelCode(ChannelType.BBC.getCode());
            UserResponseVo userResponseVo = userService.findByMemberIdCondition(memberIdRequestVo);
            evaluationRo = new Evaluation2EvaluationRo().apply(evaluation);
            Preconditions.checkArgument(evaluationRo != null);
            if (userResponseVo != null) {
                evaluationRo.setAccountName(userResponseVo.getAccountName());
            }
            evaluationRo.setUpdateTimestamp(DateUtil.now());
            evaluationRepository.save(evaluation);
        }
        final EvaluationRo finalEvaluationRo = evaluationRo;
        delayExecuteRedisOpt(new ExecutionUnit() {
            @Override
            public void execute() {
                if (finalEvaluationRo != null) {
                    evaluationRedisDao.save(finalEvaluationRo);
                } else {
                    evaluationRedisDao.delete(evaluation.getId());
                }
            }
        });
        evaluationRepository.save(evaluation);
    }

    @Override
    public void statVendorScore() {
        //获取全部的店铺
        int page = 1;
        int pageSize = 100;

        VendorQueryRequestVo vo = new VendorQueryRequestVo();
        vo.setPage(page);
        vo.setPageSize(pageSize);
        boolean finished = false;
        do {
            logger.debug("开始统计店铺评分, 第{}页,", page);
            List<VendorVo> vendorVos = vendorBackendService.findAll();
            if (CollectionUtils.isNotEmpty(vendorVos)) {
                for (VendorVo vendorVo : vendorVos) {
                    try {
                        this.statVendorScore(vendorVo);
                    } catch (Exception e) {
                        logger.error("统计店铺[id={}, name={}]评分异常", vendorVo.getId(), vendorVo.getVendorName());
                    }
                }
                if (vendorVos.size() < pageSize) {
                    finished = true;
                } else {
                    page++;
                }
            } else {
                finished = true;
            }
            logger.debug("完成统计店铺评分, 第{}页, 每页{}个", page, pageSize);
        } while (!finished);
    }

    private void statVendorScore(VendorVo vendorVo) {
        logger.debug("统计店铺[id={}, name={}]评分", vendorVo.getId(), vendorVo.getVendorName());
        //根据vendorId查询该店铺下全部的通过审核的评价
        VendorIdPageRequestVo reqVo = new VendorIdPageRequestVo();
        reqVo.setVendorId(Long.valueOf(vendorVo.getId()));
        List<EvaluationRo> evaluationVos = evaluationRedisDao.findByVendorId(reqVo);
        if (CollectionUtils.isEmpty(evaluationVos)) {
            return;
        }
        //计算总分
        Integer attitudeSum = 0;
        Integer attitudeNum = 0;
        Integer logisticsSum = 0;
        Integer logisticsNum = 0;
        Integer descriptionSum = 0;
        Integer descriptionNum = 0;
        for (EvaluationRo evaluationRo : evaluationVos) {
            if (evaluationRo.getAtitudeScore() != null) {
                attitudeSum += evaluationRo.getAtitudeScore();
                attitudeNum++;
            }
            if (evaluationRo.getLogisticsScore() != null) {
                logisticsSum += evaluationRo.getLogisticsScore();
                logisticsNum++;
            }
            if (evaluationRo.getDescriptionScore() != null) {
                descriptionSum += evaluationRo.getDescriptionScore();
                descriptionNum++;
            }
        }
        //计算平均得分(由于订单评分设计是10总分（最小单位0.5），店铺评分是50总分（最小单位0.1）)
        Integer attitudeScore = attitudeSum * 5 / evaluationVos.size();
        Integer logisticsScore = logisticsSum * 5 / evaluationVos.size();
        Integer descriptionScore = descriptionSum * 5 / evaluationVos.size();
        //如果数据库中没有该店铺的评分，分配新的id
        vendorVo.setAttitudeScore(attitudeScore);
        vendorVo.setAttitudeNum(attitudeNum);
        vendorVo.setLogisticsScore(logisticsScore);
        vendorVo.setLogisticsNum(logisticsNum);
        vendorVo.setDescriptionScore(descriptionScore);
        vendorVo.setDescriptionNum(descriptionNum);
        vendorBackendService.updateVendor(vendorVo);
    }
}
