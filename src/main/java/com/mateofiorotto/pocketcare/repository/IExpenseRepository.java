package com.mateofiorotto.pocketcare.repository;

import com.mateofiorotto.pocketcare.entity.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface IExpenseRepository extends JpaRepository<Expense, UUID> {
    /**
     * Get the list of all expenses by the authenticated user
     * @param userId
     * @return
     */
    @Query(nativeQuery = true,
            value = "SELECT * from expenses exp WHERE exp.user_id = :userId")
    List<Expense> findListExpensesByUserAuthenticated(@Param("userId") UUID userId);

    /**
     * Get a specific expense by ID and the authenticated user
     * @param id
     * @param userId
     * @return
     */
    @Query(nativeQuery = true,
            value = "SELECT * from expenses exp WHERE exp.id = :id AND exp.user_id = :userId")
    Optional<Expense> findExpenseByUserAuthenticated(@Param("id") UUID id, @Param("userId") UUID userId);
}
