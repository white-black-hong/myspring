package com.whz.bean;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.util.Set;

/**
 * @auther whz
 * @create 2022-02-28 09:26
 */
@Data
@AllArgsConstructor
public class Role implements Serializable {
    private static final long serialVersionUID = 1L;

    private String id;
    private String roleName;
    /**
     * 角色对应权限集合
     */
    private Set<Permissions> permissions;
}
