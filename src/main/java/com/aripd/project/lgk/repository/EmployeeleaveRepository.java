package com.aripd.project.lgk.repository;

import com.aripd.account.domain.Account;
import com.aripd.project.lgk.domain.Employeeleave;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeleaveRepository extends JpaRepository<Employeeleave, Long> {

    public List<Employeeleave> findByAccount(Account account);
}
