package com.biz.gbck.transform.notice;

import com.biz.gbck.dao.mysql.po.info.NoticePo;
import com.biz.gbck.dao.redis.ro.notice.NoticeRo;
import com.google.common.base.Function;

import java.io.Serializable;

@SuppressWarnings("serial") public class NoticePoToNoticeRo
    implements Function<NoticePo, NoticeRo>, Serializable {

    @Override public NoticeRo apply(NoticePo po) {
        NoticeRo ro = null;
        if (po != null) {
            ro = new NoticeRo();
            ro.setId(po.getId().toString());
            ro.setTitle(po.getTitle());
            ro.setContent(po.getContent());
            ro.setCreateTime(po.getCreateTime());
            ro.setUrl(po.getUrl());
        }
        return ro;
    }

}
