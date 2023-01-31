package com.example.UserProvider.bean;


import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

public class Auth extends Authenticator {
    @Override
    protected PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication("yzl1154695556@qq.com","ekymfxsdfauxjaae");
    }
}
