package com.reactive.springreactive.repo;

import com.reactive.springreactive.entity.Student;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends ReactiveCrudRepository<Student,Integer> {
}
