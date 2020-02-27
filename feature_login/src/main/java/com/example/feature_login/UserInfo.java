package com.example.feature_login;

/**
 * @author：jooper Email：jooperge@163.com
 * 描述：用户实体类
 * 修改历史:
 * <p>
 * 创建于： 2020/2/27
 */
public class UserInfo {
    /**
     * 用户姓名
     */
    private String name;
    /**
     * 用户年龄
     */
    private String age;
    /**
     * 用户登录token
     */
    private String token;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
