package com.iamdoggy.iamdoggy.dtos.common;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class AnimalDTO extends BaseDTO {
    protected int age;
    protected String state;
}
