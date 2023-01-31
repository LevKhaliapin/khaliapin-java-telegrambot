package org.telbot.telran.info.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telbot.telran.info.model.Channel;
import org.telbot.telran.info.repository.ChannelRepository;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class ChannelServiceImpl implements ChannelService {

    @Autowired
    private ChannelRepository channelRepository;

    @Override
    public List<Channel> getAllChannels() {
        return channelRepository.findAll();
    }

    @Override
    public List<Channel> getChannelsListById(List<Integer> list) {
        return channelRepository.findAllById(list);
    }

    @Override
    public List<Integer> getAllChannelIdsByListGroupIds(List<Long> list) {
        return getAllChannels().stream().map(Channel::getId).collect(Collectors.toList());
    }

    @Override
    public int getChannelIdByGroupId(Long groupId) {
        return getAllChannels().stream().filter(i -> i.getGroupId() == groupId).map(Channel::getId).findFirst().get();
    }

    @Override
    public Channel getChannelById(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("Incorrect id " + id);
        }
        return channelRepository.findById(id).orElse(null);
    }

    @Override
    public Channel getChannelByGroupId(long groupId) {
        return getAllChannels().stream().filter(ch -> ch.getGroupId() == groupId).findFirst().orElse(null);
    }

    @Override
    public Channel getChannelByTitle(String title) {
        return getAllChannels().stream().filter(ch -> Objects.equals(ch.getTitle(), title)).findFirst().orElse(null);
    }

    @Override
    public Channel save(Channel channel) {
        return channelRepository.save(channel);
    }

    @Override
    public void delete(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("Incorrect id " + id);
        }
        channelRepository.deleteById(id);
    }
}
