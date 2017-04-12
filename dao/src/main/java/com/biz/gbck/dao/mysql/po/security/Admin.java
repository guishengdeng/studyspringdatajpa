package com.biz.gbck.dao.mysql.po.security;

import com.biz.gbck.dao.mysql.po.enums.CommonStatusEnum;
import com.google.common.collect.Sets;
import java.io.Serializable;
import java.sql.Date;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import javax.persistence.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * 管理员
 *
 * @author david-liu
 * @date 2017年04月07日
 * @reviewer
 */
@Entity
@Table(name = "adm_admin")
public class Admin implements Serializable, UserDetails {

    private static final long serialVersionUID = -4877271072444358347L;

    /**
     * 用户名
     */
    @Id
    @Column(length = 20, nullable = false)
    private String username;

    /**
     * 密码
     */
    @Column(length = 200, nullable = false)
    private String password;

    /**
     * 名字
     */
    @Column(length = 50, nullable = false)
    private String name;

    /**
     * 联系电话
     */
    @Column(length = 50)
    private String phone;

    /**
     * 状态
     */
    @Column
    @Enumerated(EnumType.STRING)
    private CommonStatusEnum status = CommonStatusEnum.ENABLE;

    /**
     * 所拥有的权限
     */
    @ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JoinTable(name = "users_roles", joinColumns = {
            @JoinColumn(name = "username", referencedColumnName = "username")}, inverseJoinColumns = {
            @JoinColumn(name = "roleId", referencedColumnName = "id")}, uniqueConstraints = {
            @UniqueConstraint(columnNames = {"username", "roleId"})})
    private List<Role> roles;

    /**
     * 权限字符串
     */
    @Column(columnDefinition = "MEDIUMTEXT")
    private String authorityString;

    /**
     * 菜单
     */
    @Transient
    private List<Menu> menus;

    @Column
    private Date createDate;

    @Column(length = 50)
    private String createBy;

    @Override
    public String getPassword() {
        return password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public CommonStatusEnum getStatus() {
        return status;
    }

    public void setStatus(CommonStatusEnum status) {
        this.status = status;
    }

    public String getAuthorityString() {
        return authorityString;
    }

    public void setAuthorityString(String authorityString) {
        this.authorityString = authorityString;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public List<Menu> getMenus() {
        return menus;
    }

    public void setMenus(List<Menu> menus) {
        this.menus = menus;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<GrantedAuthority> authorities = Sets.newHashSet();
        if (roles != null) {
            roles.forEach(role -> {
                role.getMenuItems().forEach(menuItem -> {
                    if (StringUtils.isNotBlank(menuItem.getSymbol())) {
                        String[] roleSymbol = menuItem.getSymbol().split("[^\\w_]+");
                        Arrays.stream(roleSymbol).forEach(symbol -> authorities.add(new AdminAuthority(symbol)));
                    }
                });

                role.getResources().forEach(resource -> {
                    if (StringUtils.isNotBlank(resource.getSymbol())) {
                        String[] roleSymbol = resource.getSymbol().split("[^\\w_]+");
                        Arrays.stream(roleSymbol).forEach(symbol -> authorities.add(new AdminAuthority(symbol)));
                    }
                });
            });
            System.out.println(" auth size:" + authorities.size());
        }
        return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return this.status.isEnable();
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }
}
