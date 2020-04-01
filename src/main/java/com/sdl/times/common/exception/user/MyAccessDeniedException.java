package com.sdl.times.common.exception.user;

import org.springframework.security.access.AccessDeniedException;
public class MyAccessDeniedException extends AccessDeniedException {
    public MyAccessDeniedException(String msg) {
        super(msg);
    }
}
