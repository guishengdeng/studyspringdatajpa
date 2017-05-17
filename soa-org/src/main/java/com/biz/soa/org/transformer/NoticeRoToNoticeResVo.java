package com.biz.soa.org.transformer;

import com.biz.gbck.dao.redis.ro.notice.NoticeRo;
import com.biz.gbck.vo.info.NoticeResVo;
import com.google.common.base.Function;

import java.io.Serializable;

@SuppressWarnings("serial") public class NoticeRoToNoticeResVo
    implements Function<NoticeRo, NoticeResVo>, Serializable {

    @Override public NoticeResVo apply(NoticeRo ro) {
        NoticeResVo vo = null;
        if (ro != null) {
            vo = new NoticeResVo();
            vo.setId(Long.parseLong(ro.getId()));
            vo.setTitle( ro.getTitle());
            vo.setContent(ro.getContent());
            vo.setCreateTime(ro.getCreateTime().getTime());
            vo.setUrl(ro.getUrl());
        }
        return vo;
    }

}
