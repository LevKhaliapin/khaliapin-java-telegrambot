package org.telbot.telran.info.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telbot.telran.info.model.Message;
import org.telbot.telran.info.repository.MessageRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    private MessageRepository messageRepository;

    @Override
    public List<Message> getAllMessages() {
        return messageRepository.findAll();
    }

    @Override
    public List<Message> getAllNewMessages() {
        return messageRepository.findAll().stream().filter(Message::isUnread).toList();
    }

    @Override
    public List<Long> getAllGroupIdOfNewMessages() {
        return getAllNewMessages().stream().map(Message::getChatId).collect(Collectors.toList());
    }

    @Override
    public Message getMessage(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("Incorrect id " + id);
        }
        return messageRepository.findById(id).orElse(null);
    }

    @Override
    public void saveMessage(long chatId, String title, String text, boolean unread) {
        Message message = new Message(chatId, title, text, unread);
        messageRepository.save(message);
    }

    @Override
    public void saveMessage(Message message) {
        messageRepository.save(message);
    }

    @Override
    public void deleteMessage(int messageId) {
        if (messageId <= 0) {
            throw new IllegalArgumentException("Incorrect id " + messageId);
        }
        messageRepository.deleteById(messageId);
    }
}
