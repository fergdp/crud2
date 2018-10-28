package com.madrefoca.crud2.repositories;

import com.madrefoca.crud2.entities.Expense;
import org.springframework.data.jpa.repository.JpaRepository;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface ExpenseRepository extends JpaRepository<Expense, Long> {

}
