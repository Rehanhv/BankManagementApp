package com.Project.BankManagement.repo;

import com.Project.BankManagement.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepo extends JpaRepository<Account, Long> {
}
