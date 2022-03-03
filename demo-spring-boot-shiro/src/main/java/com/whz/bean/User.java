package com.whz.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.crazycake.shiro.AuthCachePrincipal;

import java.io.Serializable;
import java.util.Set;

/**
 * @auther whz
 * @create 2022-02-28 09:26
 */
@Data
@AllArgsConstructor
public class User implements Serializable, AuthCachePrincipal {

    private static final long serialVersionUID = 4297464181093070302L;

    private String id;
    private String userName;
    private String password;
    /**
     * * 用户对应的角色集合
     */
    private Set<Role> roles;

    @Override
    public String getAuthCacheKey() {
        return null;
    }
}
