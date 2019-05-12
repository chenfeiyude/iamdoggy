package com.iamdoggy.iamdoggy.dtos.common;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class BaseDTO {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    protected Long id;

    public Long getId() {
        return id;
    }

}

