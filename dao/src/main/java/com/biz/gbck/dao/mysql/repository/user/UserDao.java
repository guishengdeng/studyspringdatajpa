package com.biz.gbck.dao.mysql.repository.user;

import com.biz.gbck.dao.mysql.po.org.UserPo;
import com.biz.gbck.vo.search.SearchUserReqVo;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;

/**
 * Created by defei on 3/11/16.
 */
@NoRepositoryBean
public interface UserDao {

    List<UserPo> searchUser(SearchUserReqVo vo);

    Long findMaxUserId();
}
