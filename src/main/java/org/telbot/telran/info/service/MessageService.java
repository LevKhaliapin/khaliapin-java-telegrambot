package org.telbot.telran.info.service;

import org.telbot.telran.info.model.Message;

import java.util.List;

public interface MessageService {
    List<Message> getAllMessages();

    List<Message> getAllNewMessages();

    List<Long> getAllGroupIdOfNewMessages();

    Message getMessage(int id);

    void saveMessage(long chatId, String title, String text, boolean unread);

    void saveMessage(Message message);

    void deleteMessage(int messageId);

}
