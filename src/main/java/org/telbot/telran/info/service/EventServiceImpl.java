package org.telbot.telran.info.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.telbot.telran.info.model.Event;
import org.telbot.telran.info.model.Message;
import org.telbot.telran.info.model.Statistic;
import org.telbot.telran.info.repository.EventRepository;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;

@Service
public class EventServiceImpl implements EventService {
    @Autowired
    private EventRepository eventRepository;
    @Autowired
    private MessageService messageService;
    @Autowired
    private UserChannelService userChannelService;
    @Autowired
    private StatisticService statisticService;
    @Autowired
    private ChannelService channelService;
    @Autowired
    private UserService userService;

    @Scheduled(cron = "${cron.scheduler.expression}")
    public void createNewEvents1() {
        List<Message> messages = messageService.getAllNewMessages().stream().filter(Message::isUnread).toList();
        if (messages.isEmpty()) return;
        Map<Long, String> channelMap2 = messages.stream()
                .collect(Collectors.toMap(Message::getChatId, Message::getText, (s, a) -> s + ", " + a));
        channelMap2.forEach((groupId, text) -> {
            List<Integer> listUserId = userChannelService.getUserIdsByChannelId(channelService.getChannelIdByGroupId(groupId));
            for (int userId : listUserId) {
                Event event = new Event();
                event.setUserId(userId);
                event.setText(text);
                eventRepository.save(event);
            }
        });
        statisticService.createStatistic(new Statistic("Date: " + LocalDateTime.now() + "; " +
                " number of admins/users: " + userService.numOfAdmins() + "/" + userService.numOfUsers() + "; " +
                " number of groups: " + channelService.getAllChannels().size() + "; " +
                " number of events: " + getNewsEvents().size() + "; " +
                " number of SMS: " + messages.size()));
        markMessagesAsRead(messages);
    }

    @Scheduled(cron = "${cron.scheduler.statistic}")
    public void createStatisticForWeek() {
        int numOfSMS = messageService.getAllMessages().size();
        statisticService.createStatistic(new Statistic("Date: " + LocalDateTime.now() + "; " +
                " number of admins/users: " + userService.numOfAdmins() + "/" + userService.numOfUsers() + "; " +
                " number of groups: " + channelService.getAllChannels().size() + "; " +
                " number of events for week: " + getNewsEvents().size() + "; " +
                " number of SMS for week: " + messageService.getAllMessages()));
    }

    @Override
    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    @Override
    public List<Event> getNewsEvents() {
        return eventRepository.findAll().stream().filter(Event::isUnsent).collect(toList());
    }

    @Override
    public List<Event> getNewsEventsAndSend() {
        List<Event> list = eventRepository.findAll().stream().filter(Event::isUnsent).collect(toList());
        markEventsAsOld(list);
        return list;
    }

    @Override
    public List<Event> getNewEventsByUserId(int userId) {
        if (userId <= 0) {
            throw new IllegalArgumentException("Incorrect id " + userId);
        }
        List<Event> eventList = getNewsEvents().stream().filter(e -> e.getUserId() == userId).collect(toList());
        return eventList;
    }

    @Override
    public List<Event> getNewEventsByUserIdAndSend(int userId) {
        if (userId <= 0) {
            throw new IllegalArgumentException("Incorrect id " + userId);
        }
        List<Event> eventList = getNewsEvents().stream().filter(e -> e.getUserId() == userId).collect(toList());
        markEventsAsOld(eventList);
        return eventList;
    }

    @Override
    public List<Event> getAllEventsByUserId(int userId) {
        if (userId <= 0) {
            throw new IllegalArgumentException("Incorrect id " + userId);
        }
        return getAllEvents().stream().filter(e -> e.getUserId() == userId).collect(toList());
    }

    @Override
    public void markEventsAsOld(List<Event> newEvents) {
        for (Event e : newEvents) {
            e.setUnsent(false);
            eventRepository.save(e);
        }
    }

    public void markMessagesAsRead(List<Message> messages) {
        for (Message m : messages) {
            m.setUnread(false);
            messageService.saveMessage(m);
        }
    }
}
