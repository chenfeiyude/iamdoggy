package com.iamdoggy.iamdoggy.interfaces.daos.management;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.iamdoggy.iamdoggy.dtos.management.AccountDTO;

@Repository("accountJpaDAO")
public interface AccountJpaDAO extends JpaRepository<AccountDTO, Long>{
	AccountDTO findByUid(String uid);
}
