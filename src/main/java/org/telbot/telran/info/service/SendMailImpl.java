package org.telbot.telran.info.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.telbot.telran.info.model.User;

@Service
public class SendMailImpl implements SendMail {
    @Autowired
    private EventService eventService;

    @Override
    public void sendMailByUser(User user) {
        eventService.getNewEventsByUserIdAndSend(user.getId());
    }

    @Scheduled(fixedRate = 10000)
    @Override
    public void sendMailWithNewEvents() {
        eventService.getNewsEventsAndSend();
    }
}
