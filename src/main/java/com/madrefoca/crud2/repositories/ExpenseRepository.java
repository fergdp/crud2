package com.madrefoca.crud2.repositories;

import com.madrefoca.crud2.model.Expense;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long> {
    Page<Expense> findByMonthId(Long monthId, Pageable pageable);
}
