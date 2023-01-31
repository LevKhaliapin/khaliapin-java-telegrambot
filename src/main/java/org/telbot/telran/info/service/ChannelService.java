package org.telbot.telran.info.service;

import org.telbot.telran.info.model.Channel;

import java.util.List;

public interface ChannelService {

    List<Channel> getAllChannels();

    List<Channel> getChannelsListById(List<Integer> list);

    Channel getChannelById(int id);

    Channel getChannelByGroupId(long groupId);

    List<Integer> getAllChannelIdsByListGroupIds(List<Long> list);

    int getChannelIdByGroupId(Long groupId);

    Channel getChannelByTitle(String title);

    Channel save(Channel channel);

    void delete(int id);
}
