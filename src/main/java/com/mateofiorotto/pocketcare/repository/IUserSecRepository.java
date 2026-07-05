package com.mateofiorotto.pocketcare.repository;

import com.mateofiorotto.pocketcare.entity.UserSec;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface IUserSecRepository extends JpaRepository<UserSec, UUID> {
    Optional<UserSec> findByEmail(String email);
}
