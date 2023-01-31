package org.telbot.telran.info.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.telbot.telran.info.model.Channel;
import org.telbot.telran.info.model.UserChannel;
import org.telbot.telran.info.service.UserChannelService;

import java.util.List;

@RestController
@RequestMapping("/userChannels")
public class UserChannelController {
    @Autowired
    private UserChannelService UserChannelService;

    @GetMapping
    public List<UserChannel> list() {
        try {
            return UserChannelService.getAllSubscriptions();
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getLocalizedMessage());
        }
    }

    @GetMapping("/{id}")
    public List<Channel> findAllChannelByUserId(@PathVariable(name = "id") int id) {
        try {
            return UserChannelService.findAllChannelByUserId(id);
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getLocalizedMessage());
        }
    }

    @PostMapping("/{userId}/{channelId}")
    public void addSubscription(@PathVariable(name = "userId") int userId, @PathVariable(name = "channelId") int channelId) {
        try {
            UserChannelService.addSubscription(userId, channelId);
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getLocalizedMessage());
        }
    }

    @DeleteMapping("/{userId}/{channelId}")
    public void deleteUser(@PathVariable(name = "userId") int userId, @PathVariable(name = "channelId") int channelId) {
        try {
            UserChannelService.deleteSubscription(userId, channelId);
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getLocalizedMessage());
        }
    }
}
