package com.biz.soa.product.service.backend;

import com.biz.gbck.dao.mysql.po.product.meta.ApartTag;
import com.biz.gbck.dao.mysql.repository.apartTag.ApartTagRepository;
import com.biz.gbck.exceptions.product.ApartTagNotFoundException;
import com.biz.gbck.transform.product.ApartTag2ApartTagListItemVo;
import com.biz.gbck.vo.product.backend.ApartTagCreateVo;
import com.biz.gbck.vo.product.backend.ApartTagListItemVo;
import com.biz.gbck.vo.product.backend.ApartTagUpdateVo;
import com.biz.service.AbstractBaseService;
import com.biz.service.product.backend.ApartTagService;
import com.google.common.collect.Lists;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 江南
 * @usage
 * @reviewer
 * @since 2016/12/28
 */
@Service
public class ApartTagServiceImpl extends AbstractBaseService implements ApartTagService {

    @Autowired
    private ApartTagRepository apartTagRepository;

    /**
     * 返回ApartTag列表
     * 1.查询未被删除的ApartTag
     * 2.返回被Transform的ListVo
     */
    @Override
    public List<ApartTagListItemVo> listApartTag() {
        return Optional.of(apartTagRepository.findByDeleteFlag(Boolean.FALSE))
                .orElse(Lists.newArrayList()).stream()
                .map(apartTag -> new ApartTag2ApartTagListItemVo().apply(apartTag)).collect(Collectors.toList());
    }

    /**
     * 创建ApartTag
     * 1.创建ApartTagPo,并将vo转换po
     * 2.设置Po的ID
     * 3.持久化Po
     */
    @Override
    public ApartTagCreateVo createApartTag(ApartTagCreateVo apartTagCreateVo) {
        ApartTag apartTag = new ApartTag();
        apartTag.form(apartTagCreateVo);
        apartTag.setId(idService.nextId());
        apartTagRepository.save(apartTag);
        apartTagCreateVo.setId(apartTag.getId());
        return apartTagCreateVo;
    }

    /**
     * 更新ApartTag
     * 1.查找ApartTag
     * 2.从vo更新ApartTag
     * 3.持久化ApartTag
     * 4.返回vo
     *
     * @throws ApartTagNotFoundException
     */
    @Override
    public ApartTagUpdateVo updateApartTag(ApartTagUpdateVo vo) throws ApartTagNotFoundException {
        ApartTag apartTag = apartTagRepository.findOne(vo.getId());
        if (apartTag == null) {
            throw new ApartTagNotFoundException("ApartTag不存在");
        }
        apartTag.form(vo);
        apartTagRepository.save(apartTag);
        vo.setId(apartTag.getId());
        return vo;
    }

    /**
     * 删除ApartTag
     * 1.查找ApartTag
     * 2.判断ApartTag是否存在和被删除 抛出异常
     * 3.设置ApartTag的删除标志位
     * 4.保存ApartTag
     *
     * @throws ApartTagNotFoundException
     */
    @Override
    public boolean deleteApartTag(Long apartTagId) throws ApartTagNotFoundException {
        ApartTag apartTag = apartTagRepository.findOne(apartTagId);
        if (apartTag == null) {
            throw new ApartTagNotFoundException("ApartTag不存在");
        }
        if (apartTag.getDeleteFlag() == Boolean.TRUE) {
            return true;
        }
        apartTag.setDeleteFlag(Boolean.TRUE);
        apartTagRepository.save(apartTag);
        return true;
    }

    /**
     * 获取ApartTag更新信息
     * 1.查找ApartTag,不存在抛出异常
     * 2.根据Po组装ApartTagUpdateVo并返回
     *
     * @throws ApartTagNotFoundException
     */
    @Override
    public ApartTagUpdateVo getApartTagUpdateVo(Long apartTagId) throws ApartTagNotFoundException {
        ApartTag apartTag = apartTagRepository.findOne(apartTagId);
        if (apartTag == null) {
            throw new ApartTagNotFoundException("ApartTag不存在");
        }
        ApartTagUpdateVo resp = new ApartTagUpdateVo();
        resp.setId(apartTag.getId());
        resp.setLogo(apartTag.getLogo());
        resp.setDescription(apartTag.getDescription());
        resp.setStatus(apartTag.getStatus());
        resp.setName(apartTag.getName());
        resp.setIdx(apartTag.getIdx());
        return resp;
    }
}
