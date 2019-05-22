package com.iamdoggy.iamdoggy.dtos.common;

import javax.persistence.MappedSuperclass;

/**
 * Common dto for any table related to pet, and will be linked to pet with pid
 * @author chenfeiyu
 *
 */
@MappedSuperclass
public class PetRelatedDTO extends BaseDTO {
	protected long pid;

	public long getPid() {
		return pid;
	}

	public void setPid(long pid) {
		this.pid = pid;
	}
	
	
}
