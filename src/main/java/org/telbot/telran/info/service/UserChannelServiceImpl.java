package org.telbot.telran.info.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telbot.telran.info.model.Channel;
import org.telbot.telran.info.model.UserChannel;
import org.telbot.telran.info.repository.UserChannelRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserChannelServiceImpl implements UserChannelService {

    @Autowired
    private UserChannelRepository userChannelRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private ChannelService channelService;

    @Override
    public void addSubscription(int userId, int channelId) {
        if (userId <= 0) {
            throw new IllegalArgumentException("Incorrect id " + userId);
        }
        if (channelId <= 0) {
            throw new IllegalArgumentException("Incorrect id " + channelId);
        }
        if (!checkUserAndChannel(userId, channelId)) {
            return;
        }
        UserChannel findUserChannel = findUserChannel(userId, channelId);
        if (findUserChannel != null) {
            findUserChannel.setActive(true);
            userChannelRepository.save(findUserChannel);
        } else {
            userChannelRepository.save(new UserChannel(userId, channelId));
        }
    }

    @Override
    public List<Channel> findAllChannelByUserId(int userId) {
        if (userId <= 0) {
            throw new IllegalArgumentException("Incorrect id " + userId);
        }
        List<Integer> channelIds = getAllSubscriptions().stream()
                .filter(userChannel -> userChannel.getUserId() == userId)
                .map(UserChannel::getChannelId)
                .collect(Collectors.toList());
        return channelService.getChannelsListById(channelIds);
    }

    @Override
    public List<UserChannel> getAllSubscriptions() {
        return userChannelRepository.findAll();
    }

    @Override
    public List<UserChannel> getAllActiveSubscriptions() {
        return userChannelRepository.findAll().stream().filter(UserChannel::isActive).toList();
    }

    @Override
    public List<Integer> getUserIdsByChannelId(int channelId) {
        if (channelId <= 0) {
            throw new IllegalArgumentException("Incorrect id " + channelId);
        }
        return getAllActiveSubscriptions().stream().filter(uch -> uch.getChannelId() == channelId).map(UserChannel::getUserId).collect(Collectors.toList());
    }

    @Override
    public void deleteSubscription(int userId, int channelId) {
        if (userId <= 0) {
            throw new IllegalArgumentException("Incorrect id " + userId);
        }
        if (channelId <= 0) {
            throw new IllegalArgumentException("Incorrect id " + channelId);
        }
        if (!checkUserAndChannel(userId, channelId)) {
            return;
        }
        UserChannel findUserChannel = findUserChannel(userId, channelId);
        if (findUserChannel != null) {
            findUserChannel.setActive(false);
            userChannelRepository.save(findUserChannel);
        }
    }

    private boolean checkUserAndChannel(int userId, int channelId) {
        return userService.getUser(userId) != null && channelService.getChannelById(channelId) != null;
    }

    private UserChannel findUserChannel(int userId, int channelId) {
        UserChannel userChannel = userChannelRepository.findAll().stream()
                .filter(uc -> uc.getUserId() == userId && uc.getChannelId() == channelId)
                .findFirst().orElse(null);
        return userChannel;
    }
}
