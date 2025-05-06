package org.yakov.service;

import org.yakov.model.User;

public interface UserService {
    void add(User user);

    User get(long id);

    User delete(long id);

    void update(User user);
}
