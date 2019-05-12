package com.iamdoggy.iamdoggy.dtos.management;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.iamdoggy.iamdoggy.dtos.common.BaseDTO;

@Entity
@Table(name ="user")
public class UserDTO extends BaseDTO {
    private String uid;
    private String username;
    private String password;

}
