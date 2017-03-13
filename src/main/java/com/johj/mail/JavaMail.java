package com.johj.mail;

import org.apache.commons.mail.*;

import java.net.MalformedURLException;

/**
 * Created by wenweizww on 2017/3/13.
 */
public class JavaMail {


    public static void main(String[] args) throws EmailException, MalformedURLException
    {
//        JavaMail simpleMail = new JavaMail();
//        simpleMail.sendTLS();
//        try {
//            MailSender mailSender = new MailSenderImpl();
//            MailMessage mailMessage = new MailMessage();
//
//            mailMessage
//                    .subject("hi，您有一封注册邮件！！！")
//                    .from("support@wuxiapptec.com")
//                    .content("hello worlds ")
//                    .addTo("li_xia@wuxiapptec.com");
//                    //.addFile("/Users/Anne/Documents/3849072.jpg", "/Users/Anne/Documents/vps.md");
//
//            mailSender.debug(true).host("10.111.5.158").username("zhou_wenwei@wuxiapptec.com").password("z.ww1234#");
//
//            //mailSender.host("10.111.5.158").;
//            mailSender.send(mailMessage);
//        } catch (MessagingException e) {
//            e.printStackTrace();
//        }



        MultiPartEmail email = new MultiPartEmail();

        email.setHostName("10.111.5.158");
//        email.setAuthentication("youremail@qq.com", "***");//邮件服务器验证：用户名/密码
        email.setCharset("UTF-8");

        email.setFrom("limssupport@wuxiapptec.com", "马化腾");
        email.addTo("li_xia@wuxiapptec.com");

        email.setSubject("加油奋斗！");
        email.setMsg("come on! 加油奋斗吧，早晚有一天你会出任CEO，迎娶白富美，走上人生巅峰！ http://www.wuxinextcode.com\n\n\n pony ,请勿回复，谢谢");

        EmailAttachment attachment = new EmailAttachment();
        attachment.setPath("d:/lzl.jpg");// 本地文件
        attachment.setDisposition(EmailAttachment.ATTACHMENT);
        attachment.setDescription("林志玲1");
        attachment.setName("lzl_1");
        email.attach(attachment);

//        EmailAttachment attachment2 = new EmailAttachment();
//        attachment2.setURL(new URL("http://mat1.qq.com/datalib_img/star/pic/lib/2007-01-15/2007011511104716122311.jpg"));//远程文件
//        attachment2.setDisposition(EmailAttachment.ATTACHMENT);
//        attachment2.setDescription("林志玲2");
//        attachment2.setName("lzl_2");
//        email.attach(attachment2);

        email.send();

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
