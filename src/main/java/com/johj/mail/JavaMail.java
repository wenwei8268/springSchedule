package com.johj.mail;

import org.apache.commons.mail.*;

import java.net.MalformedURLException;

/**
 * Created by wenweizww on 2017/3/13.
 */
public class JavaMail {


    public static void main(String[] args) throws EmailException, MalformedURLException
    {


    }
    public boolean sendMail(String fromMail,String toMail,String context){
        MultiPartEmail email = new MultiPartEmail();
        //SMTP 服务器地址
        email.setHostName("10.111.5.158");
//        email.setAuthentication("youremail@qq.com", "***");//邮件服务器验证：用户名/密码
        email.setCharset("UTF-8");
        try {
            email.setFrom(fromMail, "John");
            email.addTo(toMail);

            email.setSubject("good luck");
            email.setMsg("come on! 加油奋斗吧，早晚有一天你会出任CEO，迎娶白富美，走上人生巅峰！请勿回复，谢谢");

//            EmailAttachment attachment = new EmailAttachment();
//            attachment.setPath("d:/lzl.jpg");// 本地文件
//            attachment.setDisposition(EmailAttachment.ATTACHMENT);
//            attachment.setDescription("林志玲1");
//            attachment.setName("lzl_1");
//            email.attach(attachment);
            email.send();
            return true;
        }catch(EmailException e){
            e.printStackTrace();
            return false;
        }

    }
}
