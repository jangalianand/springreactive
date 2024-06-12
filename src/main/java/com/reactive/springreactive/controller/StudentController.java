package com.reactive.springreactive.controller;

import com.reactive.springreactive.entity.Student;
import com.reactive.springreactive.service.impl.StudentServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentServiceImpl studentService;

    @PostMapping
    public Mono<Student> saveStudent(@RequestBody Student student) {
        log.info("Request to save student: {}", student);
        return studentService.saveSingleStudent(student)
                .doOnSuccess(savedStudent -> log.info("Successfully saved student: {}", savedStudent))
                .doOnError(error -> log.error("Error saving student: {}", student, error));
    }

    @PostMapping("/batch")
    public Flux<Student> saveAllStudents(@RequestBody Flux<Student> students) {
        log.info("Request to save batch of students");
        return studentService.saveAllStudents(students)
                .doOnComplete(() -> log.info("Successfully saved all students"))
                .doOnError(error -> log.error("Error saving batch of students", error));
    }

    @GetMapping("/{id}")
    public Mono<Student> findStudentById(@PathVariable Integer id) {
        log.info("Request to find student by ID: {}", id);
        return studentService.findStudentById(id)
                .doOnSuccess(student -> log.info("Found student: {}", student))
                .doOnError(error -> log.error("Error finding student by ID: {}", id, error));
    }

    @GetMapping
    public Flux<Student> findAllStudents() {
        log.info("Request to find all students");
        return studentService.findAllStudents()
                .doOnComplete(() -> log.info("Successfully retrieved all students"))
                .doOnError(error -> log.error("Error finding all students", error));
    }

    @DeleteMapping("/{id}")
    public Mono<String> deleteStudentById(@PathVariable Integer id) {
        log.info("Request to delete student by ID: {}", id);
        return studentService.findStudentById(id)
                .flatMap(student -> studentService.deleteById(id)
                        .then(Mono.just("Student with ID: " + id + " deleted"))
                        .doOnSuccess(msg -> log.info(msg))
                        .doOnError(error -> log.error("Error deleting student by ID: {}", id, error)));
    }

    @DeleteMapping
    public Mono<Void> deleteAllStudents() {
        log.info("Request to delete all students");
        return studentService.deleteAll()
                .doOnSuccess(unused -> log.info("Successfully deleted all students"))
                .doOnError(error -> log.error("Error deleting all students", error));
    }
}
