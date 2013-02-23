package com.aripd.account.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.aripd.account.domain.Account;

public interface AccountRepository extends JpaRepository<Account, Long> {

	Account findOneByUsername(String username);

	Account findByUsernameOrEmail(String username, String email);

	Page<Account> findByUsernameLike(String username, Pageable pageable);

	//List<Account> findByLastName(String lastName);

	//Page<Account> findByFirstNameLike(String firstName, Pageable pageable);

	//Page<Account> findByLastNameLike(String lastName, Pageable pageable);

	//Page<Account> findByFirstNameLikeAndLastNameLike(String firstName, String lastName, Pageable pageable);

	// @Query("select u from user u where u.role.role = :role")
	// Page<User> findByRole(@Param("role") Integer role, Pageable pageable);
}
