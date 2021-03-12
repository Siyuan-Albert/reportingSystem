package com.antra.report.client.service;

import com.antra.report.client.controller.LoginController;
import com.antra.report.client.entity.UserEntity;
import com.antra.report.client.exception.RequestNotFoundException;
import com.antra.report.client.pojo.LoginVO;
import com.antra.report.client.repository.UserRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.apache.commons.codec.digest.DigestUtils;
import java.util.Optional;

/**
 * @author: Albert
 * @date: 3/10/21 02:25
 * @description:
 */
@Service
public class UserServiceImpl implements UserService {
    private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);
    @Autowired
    UserRepo userRepo;

    @Override
    public UserEntity checkPassword(LoginVO loginVO) {
        //save a user
        UserEntity standardUser = new UserEntity();
        standardUser.setMobile("123456");
        standardUser.setPassword(DigestUtils.md5Hex("123456"));
        userRepo.save(standardUser);
        String mobile = loginVO.getMobile();
        String pwd = loginVO.getPassword();
        UserEntity inputUser = new UserEntity();
        inputUser.setMobile(mobile);
        inputUser.setPassword(pwd);
        UserEntity userInfo = userRepo.findById(mobile).orElseThrow(RequestNotFoundException::new);
        log.info("pwd from database：" + userInfo.getPassword());
        log.info("pwd from website："+ pwd);
        if(userInfo.getPassword().equals(pwd)){
            return userInfo;
        }
        return null;
    }
}
