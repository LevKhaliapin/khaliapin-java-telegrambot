package org.telbot.telran.info.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.telbot.telran.info.model.Channel;
import org.telbot.telran.info.service.ChannelService;

import java.util.List;

@RestController
@RequestMapping("/channels")
public class ChannelController {
    @Autowired
    private ChannelService channelService;

    @GetMapping
    public List<Channel> list() {
        try {
            return channelService.getAllChannels();
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getLocalizedMessage());
        }
    }

    @GetMapping("/{id}")
    public Channel getChannel(@PathVariable(name = "id") int id) {
        try {
            return channelService.getChannelById(id);
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getLocalizedMessage());
        }
    }

    @PostMapping
    public Channel createChannel(@RequestBody Channel channel) {
        try {
            return channelService.save(channel);
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getLocalizedMessage());
        }
    }

    @DeleteMapping("/{id}")
    public void deleteChannel(@PathVariable(name = "id") int id) {
        try {
            channelService.delete(id);
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getLocalizedMessage());
        }
    }
}
