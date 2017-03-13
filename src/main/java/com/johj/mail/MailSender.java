package com.johj.mail;

import javax.mail.MessagingException;

/**
 * Created by wenweizww on 2017/3/13.
 */
public interface MailSender {
    void send(MailMessage mailMessage) throws MessagingException;

    void send(MailMessage mailMessage, boolean isAsync) throws MessagingException;

    void send(MailMessage... mailMessages) throws MessagingException;

    MailSender host(String host);

    MailSender port(int port);

    MailSender username(String username);

    MailSender password(String password);

    MailSender auth(boolean auth);

    MailSender debug(boolean debug);

    MailSender protocol(String protocol);

    MailSender load(String mailProperties);
}
