package org.telbot.telran.info.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

@Service
public class TelegramBot extends TelegramLongPollingBot implements Switchable {
    private boolean enabled = false;
    @Value("${bot.name: }")
    private String botName;

    @Value("${bot.key: }")
    private String token;

    @Autowired
    private CommandService commandService;
    @Autowired
    private MessageService messageService;

    @Override
    public String getBotUsername() {
        return botName;
    }

    @Override
    public String getBotToken() {
        return token;
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.getMessage().isCommand()) {
            commandService.executeCommand(update.getMessage().getText(), this);
            return;
        }
        if (!enabled) {
            return;
        }
        Message message = update.getMessage();
        Chat chat = message.getChat();
        if (chat.isGroupChat()) {
            Long chatId = message.getChatId();
            String title = chat.getTitle();
            String text = message.getText();
            messageService.saveMessage(chatId, title, text, true);
        }
    }

    @Override
    public void on() {
        enabled = true;
    }

    @Override
    public void off() {
        enabled = false;
    }
}
