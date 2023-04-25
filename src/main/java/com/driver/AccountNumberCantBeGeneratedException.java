package com.driver;

public class AccountNumberCantBeGeneratedException extends Exception{
    public AccountNumberCantBeGeneratedException(String str){
        super(str);
    }
}
