package com.biz.gbck.dao.mysql.po.org;

import com.biz.gbck.dao.mysql.po.security.Admin;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

/**
 * 隔壁仓库
 *
 * @author: liubin
 * @date 4/20/17 09:22
 */
@Entity
@Table(name = "org_warehouse")
public class WarehousePo extends Company {


    @OneToMany
    private List<Admin> admins;



}
