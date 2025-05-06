package org.yakov.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.yakov.model.Subscription;

import java.util.List;

public interface SubscriptionRepository extends JpaRepository<Subscription,Long> {
    List<Subscription> findSubscriptionsByUser_Id(long userId);
    List<Subscription> findTop3ByOrderByRatingDesc();

    Subscription findSubscriptionById(long id);
}
