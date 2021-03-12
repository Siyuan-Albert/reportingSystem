package com.antra.report.client.service;

import com.antra.report.client.entity.UserEntity;
import com.antra.report.client.pojo.LoginVO;

public interface UserService {
    UserEntity checkPassword(LoginVO loginVO);
}
