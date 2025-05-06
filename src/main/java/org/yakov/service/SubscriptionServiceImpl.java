package org.yakov.service;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.yakov.model.Subscription;
import org.yakov.model.User;
import org.yakov.repository.SubscriptionRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SubscriptionServiceImpl implements SubscriptionService {

    private final SubscriptionRepository subscriptionRepository;

    private final UserService userService;

    @Override
    public void add(long idUser, Subscription subscription) {
        User user = this.userService.get(idUser);
        subscription.setUser(user);
        try {
           this.subscriptionRepository.save(subscription);
        } catch (DataIntegrityViolationException e) {
            throw new IllegalArgumentException("Subscription is already exists");
        }
    }

    @Override
    public List<Subscription> getBestSubscriptions() {
        return this.subscriptionRepository.findTop3ByOrderByRatingDesc();
    }

    @Override
    public List<Subscription> getSubscriptionsByUserId(long idUser) {
        return this.subscriptionRepository.findSubscriptionsByUser_Id(idUser);
    }

    @Override
    public Subscription delete(long id) {
        Subscription subscription = this.subscriptionRepository.findSubscriptionById(id);
        this.subscriptionRepository.deleteById(id);
        return subscription;
    }
}
