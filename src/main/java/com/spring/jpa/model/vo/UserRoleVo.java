package com.spring.jpa.model.vo;

/**
 * UserRoleVo
 *
 * @author guisheng.deng
 * @date 2017年03月21日
 * @reviewer
 * @description
 * @see
 */
public class UserRoleVo {
    private Long id;
    private String username;
    private String password;
    private String email;
    private Integer age;
    private String sex;
    private String [] name;//role类实体里的角色名
    private String [] role_id;//role类实体里的role_id

    public String[] getRole_id() {
        return role_id;
    }

    public void setRole_id(String[] role_id) {
        this.role_id = role_id;
    }

    public String[] getName() {
        return name;
    }

    public void setName(String[] name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }



    public UserRoleVo() {
    }
}