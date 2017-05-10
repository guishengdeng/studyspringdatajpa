package com.biz.service.product.backend;

import com.biz.gbck.dao.mysql.po.tag.SaleTag;
import com.biz.gbck.dao.mysql.repository.saleTag.SaleTagRepository;
import com.biz.gbck.enums.CommonStatusEnum;
import com.biz.gbck.vo.product.backend.SaleTagSearchVo;
import com.biz.gbck.vo.product.backend.SaleTagSpecification;
import com.biz.gbck.vo.product.backend.SaleTagVo;
import com.biz.service.AbstractBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.codelogger.utils.ExceptionUtils.iae;

/**
 * 销售标签 Service
 *
 * @author lzz
 * @date 2017年5月2日
 * @reviewerv
 * @see
 */
@Service
public class SaleTagServiceImpl extends AbstractBaseService implements SaleTagService {
    @Autowired
    private SaleTagRepository saleTagRepository;

    @Override
    public List<SaleTag> listByStatus(CommonStatusEnum status) {
        return saleTagRepository.findByStatus(status);
    }

    @Override
    public void addOrUpdateSaleTag(SaleTagVo vo) {
        iae.throwIfNull(vo, "参数不能为空");
        SaleTag saleTag = new SaleTag();
        if (vo.getId() == null) {
            saleTag.setId(idService.nextId());
        } else if (vo.getId() != null && saleTagRepository.findOne(vo.getId()) != null) {
            saleTag.setId(vo.getId());
        }
        saleTag.setStatus(CommonStatusEnum.ENABLE);
        saleTag.setName(vo.getName());
        saleTag.setShowName(vo.getShowName());
        saleTag.setTag(vo.getTag());
        saleTag.setIdx(vo.getIdx());
        saleTag.setDescription(vo.getDescription());
        saleTag.setSaleStatus(vo.getSaleStatus());
        saleTagRepository.save(saleTag);
    }

    @Override
    public SaleTag findById(Long id) {
        return id == null ? null : saleTagRepository.findOne(id);
    }

    @Override
    public void remove(Long id) {
        saleTagRepository.updateStatus(id, CommonStatusEnum.DISABLE);
    }

    //分页
    @Override
    public Page<SaleTag> searchSaleTag(SaleTagSearchVo saleTagSearchVo) {

        saleTagSearchVo.setStatus(CommonStatusEnum.ENABLE);
        return saleTagRepository.findAll(new SaleTagSpecification(saleTagSearchVo), new PageRequest(saleTagSearchVo.getPage() - 1, saleTagSearchVo.getPageSize(),
                new Sort(Sort.Direction.ASC,"idx")));
    }


}
