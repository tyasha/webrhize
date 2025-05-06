package org.yakov.service;

import org.yakov.model.Subscription;

import java.util.List;

public interface SubscriptionService {
    void add(long idUser, Subscription subscription);

    List<Subscription> getBestSubscriptions();

    List<Subscription> getSubscriptionsByUserId(long idUser);

    Subscription delete(long id);
}
