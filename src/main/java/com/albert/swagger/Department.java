/*
 * Project: HelloWorld-MVC-Swagger
 * 
 * File Created at 2015-09-11
 
 * Copyright 2012 Greenline.com Corporation Limited.
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of
 * Greenline Company. ("Confidential Information").  You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into
 * with Greenline.com.
 */
package com.albert.swagger;

import java.io.Serializable;

/**
 * TODO
 *
 * @author guolinlin
 * @version V1.0
 * @since 2015-09-11 14:51
 */
public class Department implements Serializable {

    private String id;

    private String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
