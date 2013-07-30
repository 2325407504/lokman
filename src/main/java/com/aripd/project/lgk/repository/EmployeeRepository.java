package com.aripd.project.lgk.repository;

import com.aripd.project.lgk.domain.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    public Employee findOneByTckimlikno(String tckimlikno);
}
