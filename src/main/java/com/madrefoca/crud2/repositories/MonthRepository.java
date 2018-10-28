package com.madrefoca.crud2.repositories;

import com.madrefoca.crud2.entities.Month;
import org.springframework.data.repository.CrudRepository;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface MonthRepository extends CrudRepository<Month, Integer> {

}
