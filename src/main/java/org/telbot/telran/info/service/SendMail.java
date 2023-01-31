package org.telbot.telran.info.service;

import org.telbot.telran.info.model.User;

public interface SendMail {
    void sendMailByUser(User user);

    void sendMailWithNewEvents();

}
