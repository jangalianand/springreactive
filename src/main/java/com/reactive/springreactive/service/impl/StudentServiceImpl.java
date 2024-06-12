package com.reactive.springreactive.service.impl;

import com.reactive.springreactive.entity.Student;
import com.reactive.springreactive.repo.StudentRepository;
import com.reactive.springreactive.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    StudentRepository studentRepository;

    @Override
    public Flux<Student> saveAllStudents(Flux<Student> students) {
        log.info("Request to save batch of students");
        return studentRepository.saveAll(students)
                .doOnComplete(() -> log.info("Successfully saved all students"))
                .doOnError(error -> log.error("Error saving batch of students", error));
    }

    @Override
    public Mono<Student> saveSingleStudent(Student student) {
        log.info("Request to save student: {}", student);
        return studentRepository.save(student)
                .doOnSuccess(savedStudent -> log.info("Successfully saved student: {}", savedStudent))
                .doOnError(error -> log.error("Error saving student: {}", student, error));
    }

    @Override
    public Mono<Student> findStudentById(Integer id) {
        log.info("Request to find student by ID: {}", id);
        return studentRepository.findById(id)
                .doOnSuccess(student -> log.info("Found student: {}", student))
                .doOnError(error -> log.error("Error finding student by ID: {}", id, error));
    }

    @Override
    public Flux<Student> findAllStudents() {
        log.info("Request to find all students");
        return studentRepository.findAll()
                .doOnComplete(() -> log.info("Successfully retrieved all students"))
                .doOnError(error -> log.error("Error finding all students", error));
    }

    @Override
    public Mono<Void> deleteById(Integer id) {
        log.info("Request to delete student by ID: {}", id);
        return studentRepository.deleteById(id)
                .doOnSuccess(unused -> log.info("Successfully deleted student with ID: {}", id))
                .doOnError(error -> log.error("Error deleting student by ID: {}", id, error));
    }

    @Override
    public Mono<Void> deleteAll() {
        log.info("Request to delete all students");
        return studentRepository.deleteAll()
                .doOnSuccess(unused -> log.info("Successfully deleted all students"))
                .doOnError(error -> log.error("Error deleting all students", error));
    }
}
