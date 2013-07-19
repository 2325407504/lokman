package com.aripd.project.lgk.repository;

import com.aripd.account.domain.Account;
import com.aripd.project.lgk.domain.Employeeworkinghour;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeworkinghourRepository extends JpaRepository<Employeeworkinghour, Long> {

    public List<Employeeworkinghour> findByAccount(Account account);
}
