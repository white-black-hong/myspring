package com.whz.bean;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * @auther whz
 * @create 2022-02-28 09:26
 */
@Data
@AllArgsConstructor
public class Permissions implements Serializable {

    private static final long serialVersionUID = 1L;
    private String id;
    private String permissionsName;
}
