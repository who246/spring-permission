package com.demo.web.back.sys.exception;

import com.demo.common.BaseException;


public class UserException extends BaseException{

    public UserException( String defaultMessage) {
        super("sys.user", defaultMessage);
        
    }
    
}
