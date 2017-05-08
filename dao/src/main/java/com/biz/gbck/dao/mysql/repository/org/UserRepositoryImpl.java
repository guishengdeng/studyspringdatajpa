package com.biz.gbck.dao.mysql.repository.org;


import com.biz.gbck.common.dao.mysql.CommonManageAbleDao;
import com.biz.gbck.dao.mysql.po.org.UserPo;
import com.biz.gbck.vo.search.SearchUserReqVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by defei on 3/11/16.
 */
@Repository("userRepositoryImpl")
public class UserRepositoryImpl extends CommonManageAbleDao
    implements UserDao {

    public List<UserPo> searchUser(SearchUserReqVo vo) {
        List<Predicate> predicates = new ArrayList<>();
        Root<UserPo> form;
        CriteriaBuilder cb = this.getEntityManager().getCriteriaBuilder();
        CriteriaQuery<UserPo> criteriaQuery = cb.createQuery(UserPo.class);
        form = criteriaQuery.from(UserPo.class);
//        if (ValueUtils.getValue(vo.getId()) != 0) {
//            predicates.add(cb.equal(form.get("id"), vo.getId()));
//        } else
        if (StringUtils.isNotEmpty(vo.getMobile())) {
            predicates.add(cb.equal(form.<String>get("mobile"), vo.getMobile() ));
        } else if (StringUtils.isNoneEmpty(vo.getAccount())) {
            predicates.add(cb.like(form.<String>get("account"), "%" + vo.getAccount() + "%"));
        }
        criteriaQuery.where(predicates.toArray(new Predicate[0]));
        Query q = getEntityManager().createQuery(criteriaQuery);
        List<UserPo> result = q.getResultList();
        return result;
        //        } else {
        ////            predicates.add(cb.equal(form.get("status"), UserStatus.DISABLED.getValue()));
        //            criteriaQuery.where(predicates.toArray(new Predicate[0]));
        //            Query q = getEntityManager().createQuery(criteriaQuery);
        //            List<UserPo> result = q.getResultList();
        //            return result;
        //        }
    }

    @Override public Long findMaxUserId() {
        Query query = getEntityManager().createQuery("SELECT id FROM UserPo ORDER BY id DESC");
        try {
            Object id = query.setMaxResults(1).getSingleResult();
            return id == null ? 0L : Long.valueOf(id.toString());
        } catch (NoResultException e) {
            return 0L;
        }
    }
}
