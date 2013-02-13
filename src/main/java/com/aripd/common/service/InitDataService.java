package com.aripd.common.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aripd.account.domain.Account;
import com.aripd.account.domain.Role;
import com.aripd.account.repository.AccountRepository;
import com.aripd.account.repository.RoleRepository;

/**
 * Data initialization service
 */
@Service("initDataService")
public class InitDataService {

	protected static Logger logger = Logger.getLogger(InitDataService.class);

	@Autowired
	AccountRepository userRepository;

	@Autowired
	RoleRepository roleRepository;

	public void init() {
		Role superadminRole = getRole("ROLE_SUPERADMIN");
		Role adminRole = getRole("ROLE_ADMIN");
		Role userRole = getRole("ROLE_USER");
		Role role1 = getRole("ROLE_L1");
		Role role2 = getRole("ROLE_L2");
		Role role3 = getRole("ROLE_L3");
		Role role4 = getRole("ROLE_L4");
		Role role5 = getRole("ROLE_L5");

		/* A user with admin right */
		Account admin1 = new Account();
		admin1.setUsername("cem");
		admin1.setFirstName("Cem");
		admin1.setLastName("ARIPD");
		admin1.setEmail("cem@aripd.com");
		admin1.setPassword(DigestUtils.md5Hex("cem"));
		admin1.setIsEnabled(true);
		this.userRepository.save(admin1);
		admin1.getRoles().add(superadminRole);
		admin1.getRoles().add(adminRole);
		admin1.getRoles().add(userRole);
		this.userRepository.save(admin1);

		/* A user with admin right */
		Account admin2 = new Account();
		admin2.setUsername("john");
		admin2.setFirstName("John");
		admin2.setLastName("Doe");
		admin2.setEmail("admin@mail.com");
		admin2.setPassword(DigestUtils.md5Hex("admin"));
		admin2.setIsEnabled(true);
		this.userRepository.save(admin2);
		admin2.getRoles().add(adminRole);
		admin2.getRoles().add(userRole);
		this.userRepository.save(admin2);

		/* A user with no admin right */
		Account user = new Account();
		user.setUsername("jane");
		user.setFirstName("Jane");
		user.setLastName("Doe");
		user.setEmail("user@mail.com");
		user.setPassword(DigestUtils.md5Hex("user"));
		user.setIsEnabled(true);
		this.userRepository.save(user);
		user.getRoles().add(userRole);
		this.userRepository.save(user);

		/* users no any right */
		List<Account> listUsers = new ArrayList<Account>();
		Account aUser;
		for (int i = 0; i < 100; i++) {
			logger.debug("Adding a new record");
			aUser = new Account();
			aUser.setUsername("user" + i);
			aUser.setFirstName("FirstName_" + i);
			aUser.setLastName("LastName_" + i);
			aUser.setEmail("User_" + i + "@mail.com");
			aUser.setPassword(DigestUtils.md5Hex("user"));
			aUser.setIsEnabled(true);
			listUsers.add(aUser);
		}
		this.userRepository.save(listUsers);
	}

	private Role getRole(final String roleCode) {
		Role result = roleRepository.findOneByCode(roleCode);
		if (result == null) {
			result = new Role();
			result.setCode(roleCode);
			roleRepository.save(result);
		}
		return result;
	}
}
