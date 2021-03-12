package com.antra.report.client.controller;

import com.antra.report.client.entity.UserEntity;
import com.antra.report.client.pojo.LoginVO;
import com.antra.report.client.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

/**
 * @author: Albert
 * @date: 3/9/21 23:20
 * @description:
 */

@RestController
@RequestMapping("/login")
public class LoginController {
    private static final Logger log = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    UserService userService;

    @PostMapping(value = "/do_login")
    public int dologin( @RequestBody @Validated LoginVO loginVo) {
        log.info(loginVo.toString());
        UserEntity userEntity = userService.checkPassword(loginVo);

        if(userEntity!=null){
            return 1;
        }
        return 0;
    }
}
