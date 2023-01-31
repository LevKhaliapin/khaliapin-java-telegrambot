package org.telbot.telran.info.service;

import org.telbot.telran.info.model.Event;

import java.util.List;

public interface EventService {

    List<Event> getAllEvents();

    List<Event> getNewsEvents();

    List<Event> getNewsEventsAndSend();

    List<Event> getNewEventsByUserId(int userId);

    List<Event> getAllEventsByUserId(int userId);

    List<Event> getNewEventsByUserIdAndSend(int userId);

    void markEventsAsOld(List<Event> events);
}
