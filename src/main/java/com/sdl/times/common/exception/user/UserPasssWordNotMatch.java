package com.sdl.times.common.exception.user;

import com.sdl.times.common.exception.CustomException;

public class UserPasssWordNotMatch extends CustomException {
    public UserPasssWordNotMatch() {
        super("用户名或密码错误", 500);
    }
}
