package com.iamdoggy.iamdoggy.dtos.doggy;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.iamdoggy.iamdoggy.dtos.common.PetRelatedDTO;

@Entity
@Table(name ="activity_log")
public class ActivityLogDTO extends PetRelatedDTO {

}
