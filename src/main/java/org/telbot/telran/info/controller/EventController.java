package org.telbot.telran.info.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.telbot.telran.info.model.Event;
import org.telbot.telran.info.service.EventService;

import java.util.List;

@RestController
@RequestMapping("/events")
public class EventController {

    @Autowired
    private EventService eventService;

    @GetMapping("/{id}")
    public List<Event> getNewEventsByUserIdAndSend(@PathVariable(name = "id") int id) {
        try {
            return eventService.getNewEventsByUserIdAndSend(id);
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getLocalizedMessage());
        }
    }

    @GetMapping
    public List<Event> getNewEventList() {
        try {
            return eventService.getNewsEventsAndSend();
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getLocalizedMessage());
        }
    }
}
