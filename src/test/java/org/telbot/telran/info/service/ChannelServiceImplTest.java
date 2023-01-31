package org.telbot.telran.info.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.telbot.telran.info.model.Channel;
import org.telbot.telran.info.repository.ChannelRepository;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
class ChannelServiceImplTest {

    @Autowired
    ChannelService channelService;

    @Autowired
    ChannelRepository channelRepository;

    @BeforeEach
    void setUp() {
        channelRepository.deleteAll();
    }

    @Test
    void getAllChannels() {
        List<Channel> all = channelRepository.findAll();
        assertEquals(0, all.size());
        channelService.save(new Channel("Run1", 111));
        channelService.save(new Channel("Run2", 222));
        channelService.save(new Channel("Run3", 333));
        List<Channel> all1 = channelRepository.findAll();
        assertEquals(3, all1.size());
    }

    @Test
    void getChannelsListById() {
    }

    @Test
    void getAllChannelIdsByListGroupIds() {
    }

    @Test
    void getChannelIdByGroupId() {
    }

    @Test
    void getChannelById() {
    }

    @Test
    void getChannelByGroupId() {
    }

    @Test
    void getChannelByTitle() {
    }

    @Test
    void save() {
    }

    @Test
    void delete() {
    }
}