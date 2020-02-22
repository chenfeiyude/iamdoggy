package com.feiyu4fun.iamdoggy.interfaces.daos.management;

import com.feiyu4fun.iamdoggy.dtos.management.AccountDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("accountJpaDAO")
public interface AccountJpaDAO extends JpaRepository<AccountDTO, Long>{
	AccountDTO findByUid(String uid);
}
