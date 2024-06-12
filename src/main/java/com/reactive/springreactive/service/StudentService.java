package com.reactive.springreactive.service;


import com.reactive.springreactive.entity.Student;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface StudentService {
    Flux<Student> saveAllStudents(Flux<Student> students);

    Mono<Student> saveSingleStudent(Student student);

    Mono<Student> findStudentById(Integer id);

    Flux<Student> findAllStudents();

    Mono<Void> deleteAll();

    Mono<Void> deleteById(Integer id);
}
