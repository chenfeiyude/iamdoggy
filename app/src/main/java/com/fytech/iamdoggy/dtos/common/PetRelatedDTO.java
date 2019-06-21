package com.fytech.iamdoggy.dtos.common;

import javax.persistence.MappedSuperclass;

import lombok.Data;

/**
 * Common dto for any table related to pet, and will be linked to pet with pid
 * @author chenfeiyu
 *
 */
@MappedSuperclass
@Data
public class PetRelatedDTO extends BaseDTO {
	protected long pid;	
}
