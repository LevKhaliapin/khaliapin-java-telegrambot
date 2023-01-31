package org.telbot.telran.info.service;

import org.telbot.telran.info.model.Channel;
import org.telbot.telran.info.model.UserChannel;

import java.util.List;

public interface UserChannelService {

    void addSubscription(int userId, int channelId);

    List<Channel> findAllChannelByUserId(int userId);

    List<UserChannel> getAllSubscriptions();

    List<UserChannel> getAllActiveSubscriptions();

    List<Integer> getUserIdsByChannelId(int channelId);

    void deleteSubscription(int userId, int channelId);
}
