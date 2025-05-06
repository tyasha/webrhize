package org.yakov.service;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.yakov.model.User;
import org.yakov.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public void add(User user) {
        try {
            this.userRepository.save(user);
        } catch (DataIntegrityViolationException e) {
            throw new IllegalArgumentException("User is already exists");
        }
    }

    @Override
    public User get(long id) {
        return this.userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("User does not exists!"));
    }

    @Override
    public User delete(long id) {
        User user = this.get(id);
        this.userRepository.deleteById(id);
        return user;
    }

    @Override
    public void update(User user) {
        User old = this.get(user.getId());
        old.setName(user.getName());
        try {
            this.userRepository.save(old);
        } catch (DataIntegrityViolationException e) {
            throw new IllegalArgumentException("User is already exists!");
        }
    }
}
