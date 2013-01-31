package com.aripd.account.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.aripd.account.domain.Account;

/**
 * Specifies methods used to obtain and modify account related information which
 * is stored in the database.
 * 
 * @author aripd.com
 */
public interface AccountRepository extends JpaRepository<Account, Long> {

	/**
	 * Finds accounts by using the last name as a search criteria.
	 * 
	 * @param lastName
	 * @return A list of accounts which last name is an exact match with the
	 *         given last name. If no persons is found, this method returns an
	 *         empty list.
	 */
	public List<Account> findByLastName(String lastName);

	Account findOneByUsername(String username);

	Account findByUsernameOrEmail(String username, String email);

	Page<Account> findByUsernameLike(String username, Pageable pageable);

	Page<Account> findByFirstNameLike(String firstName, Pageable pageable);

	Page<Account> findByLastNameLike(String lastName, Pageable pageable);

	Page<Account> findByFirstNameLikeAndLastNameLike(String firstName,
			String lastName, Pageable pageable);

	// @Query("select u from user u where u.role.role = :role")
	// Page<User> findByRole(@Param("role") Integer role, Pageable pageable);
}
