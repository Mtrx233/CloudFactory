package com.example.UserProvider.util;

import com.example.UserProvider.bean.Auth;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class EmailUtil extends Thread{
    //配置文件

    String address;
    String theme;
    String context;

    public void senEmail1(String address,String theme,String context){
        this.address=address;
        this.theme=theme;
        this.context=context;
    }

    @Override
    public void run() {
        //设置主机
        Properties properties=new Properties();
        properties.setProperty("mail.host","smtp.qq.com");
        //设置传输协议
        properties.setProperty("mail.transport.protocol","smtp");
        //允许认证
        properties.setProperty("mail.smtp.auth","true");
        //创建认证器对象
        Auth auth =new Auth();
        //获取一个session的会话对象
        Session session=Session.getDefaultInstance(properties,auth);
        //建立邮件服务器连接
        Transport transport= null;
        try {
            transport = session.getTransport();
        } catch (NoSuchProviderException e) {
            e.printStackTrace();
        }
        //连接服务器
        try {
            transport.connect("smtp.qq.com","yzl1154695556@qq.com","ftensoqkwsuuiiea");
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        //创建邮件对象
        MimeMessage message= new MimeMessage(session);
        //设置发件人地址
        try {
            message.setFrom(new InternetAddress("yzl1154695556@qq.com"));
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        //设置收件人邮件
        try {
            message.setRecipients(Message.RecipientType.TO,address);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        //标题
        try {
            message.setSubject(theme);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        try {
            message.setContent(context,"text/html;charset=utf-8");
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        try {
            transport.sendMessage(message, message.getAllRecipients());
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        try {
            transport.close();
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        System.out.println("发送成功");
    }

    //设置主机
    public static void sendEmail(String address,String theme,String context) throws MessagingException {
        //设置主机
        Properties properties=new Properties();
        properties.setProperty("mail.host","smtp.qq.com");
        //设置传输协议
        properties.setProperty("mail.transport.protocol","smtp");
        //允许认证
        properties.setProperty("mail.smtp.auth","true");
        //创建认证器对象
        Auth auth =new Auth();
        //获取一个session的会话对象
        Session session=Session.getDefaultInstance(properties,auth);
        //建立邮件服务器连接
        Transport transport=session.getTransport();
        //连接服务器
        transport.connect("smtp.qq.com","yzl1154695556@qq.com","ftensoqkwsuuiiea");
        //创建邮件对象
        MimeMessage message= new MimeMessage(session);
        //设置发件人地址
        message.setFrom(new InternetAddress("yzl1154695556@qq.com"));
        //设置收件人邮件
        message.setRecipients(Message.RecipientType.TO,address);
        //标题
        message.setSubject(theme);
        message.setContent(context,"text/html;charset=utf-8");
        transport.sendMessage(message, message.getAllRecipients());
        transport.close();
        System.out.println("发送成功");
    }
}
