package com.madrefoca.crud2.repositories;

import com.madrefoca.crud2.model.Month;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

@Repository
public interface MonthRepository extends JpaRepository<Month, Long> {

}
