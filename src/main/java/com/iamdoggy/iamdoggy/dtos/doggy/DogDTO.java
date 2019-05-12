package com.iamdoggy.iamdoggy.dtos.doggy;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.iamdoggy.iamdoggy.dtos.common.AnimalDTO;

@Entity
@Table(name ="dog")
public class DogDTO extends AnimalDTO {
}
