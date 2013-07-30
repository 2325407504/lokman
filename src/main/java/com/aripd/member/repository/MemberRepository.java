package com.aripd.member.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.aripd.member.domain.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {

	Member findOneByUsername(String username);

	Member findByUsernameOrEmail(String username, String email);

	Page<Member> findByUsernameLike(String username, Pageable pageable);

	// @Query("select u from user u where u.role.role = :role")
	// Page<User> findByRole(@Param("role") Integer role, Pageable pageable);
}
