package org.telbot.telran.info.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telbot.telran.info.model.Channel;
import org.telbot.telran.info.role.Role;
import org.telbot.telran.info.model.User;
import org.telbot.telran.info.repository.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ChannelService channelService;

    @Override
    public List<User> list() {
        return userRepository.findAll();
    }

    @Override
    public User getUser(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("Incorrect id " + id);
        }
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public void delete(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("Incorrect id " + id);
        }
        userRepository.deleteById(id);
    }

    @Override
    public List<User> listRoleUser() {
        return list().stream().filter(u -> u.getRole().equals(Role.USER)).collect(Collectors.toList());
    }

    public int numOfUsers() {
        return listRoleAdmin().size();
    }

    @Override
    public List<User> listRoleAdmin() {
        return list().stream().filter(u -> u.getRole().equals(Role.ADMIN)).collect(Collectors.toList());
    }

    public int numOfAdmins() {
        return listRoleAdmin().size();
    }

    private void getInfoUserChannel(int userId, int channelId) {
        User user = getUser(userId);
        Channel channel = channelService.getChannelById(channelId);
    }
}
