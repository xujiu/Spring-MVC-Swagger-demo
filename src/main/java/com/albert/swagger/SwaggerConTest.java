/*
 * Project: greenline-hrs-out-service
 * 
 * File Created at 2015-09-10
 
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

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * TODO
 *
 * @author guolinlin
 * @version V1.0
 * @since 2015-09-10 16:09
 */
@Api(value = "SwaggerConTest", description = "SwaggerConTest", position = 1)
@Controller
@RequestMapping("/SwaggerConTest")

public class SwaggerConTest {

    @ApiOperation(value = "创建用户", notes = "返回用户实体对象", response = Department.class, position = 1)
    @RequestMapping(value = "/1.0/contact/add.do", method= RequestMethod.POST)
    public void add(@RequestBody Department contact,HttpServletResponse response) {


      //  response.setHeader("Location",location);
    }
}
