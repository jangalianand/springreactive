package com.reactive.springreactive.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.stereotype.Component;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "students")
public class Student {
    @Id
    private Integer id;

    @Column("first_name")
    private String firstName;

    @Column("last_name")
    private String lastName;

    @Column("age")
    private Integer age;

}
