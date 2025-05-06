package org.yakov.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.yakov.model.User;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
}
