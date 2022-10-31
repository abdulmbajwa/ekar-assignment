package com.ekar.assignment.repository;

import com.ekar.assignment.model.CounterInformation;
import org.springframework.data.repository.CrudRepository;

public interface CounterInformationRepository extends CrudRepository<CounterInformation, Long> {
}
