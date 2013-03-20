package com.aripd.account.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aripd.account.domain.Account;
import com.aripd.account.domain.Customer;
import com.aripd.account.domain.Role;
import com.aripd.account.repository.AccountRepository;
import com.aripd.account.repository.RoleRepository;
import com.aripd.project.lgk.repository.RegionRepository;

@Service("initDataService")
public class AccountDataService {

	@Autowired
	AccountRepository userRepository;

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	RegionRepository regionRepository;

	public void init() {
		Role superadminRole = getRole("ROLE_SUPERADMIN", "Süper Yönetici");
		Role adminRole = getRole("ROLE_ADMIN", "Yönetici");
		Role userRole = getRole("ROLE_USER", "Kullanıcı");
		Role role1 = getRole("ROLE_L1", "Rol1");
		Role role2 = getRole("ROLE_L2", "Rol2");
		Role role3 = getRole("ROLE_L3", "Rol3");
		Role role4 = getRole("ROLE_L4", "Rol4");
		Role role5 = getRole("ROLE_L5", "Rol5");

		Account admin1 = new Account();
		admin1.setRegion(regionRepository.findOne(1L));
		admin1.setCustomer(new Customer("Cem", "ARIPD"));
		admin1.setUsername("cem");
		admin1.setEmail("cem@aripd.com");
		admin1.setPassword(DigestUtils.md5Hex("cem"));
		admin1.setActive(true);
		this.userRepository.save(admin1);
		admin1.getRoles().add(superadminRole);
		admin1.getRoles().add(adminRole);
		admin1.getRoles().add(userRole);
		this.userRepository.save(admin1);

		Account admin2 = new Account();
		admin2.setRegion(regionRepository.findOne(1L));
		admin2.setCustomer(new Customer("John", "Doe"));
		admin2.setUsername("john");
		admin2.setEmail("admin@mail.com");
		admin2.setPassword(DigestUtils.md5Hex("admin"));
		admin2.setActive(true);
		this.userRepository.save(admin2);
		admin2.getRoles().add(adminRole);
		admin2.getRoles().add(userRole);
		this.userRepository.save(admin2);

		Account user = new Account();
		user.setRegion(regionRepository.findOne(1L));
		user.setCustomer(new Customer("Jane", "Doe"));
		user.setUsername("jane");
		user.setEmail("user@mail.com");
		user.setPassword(DigestUtils.md5Hex("user"));
		user.setActive(true);
		this.userRepository.save(user);
		user.getRoles().add(userRole);
		this.userRepository.save(user);

		/* users no any right */
		List<Account> listUsers = new ArrayList<Account>();
		Account aUser;
		for (int i = 0; i < 100; i++) {
			aUser = new Account();
			aUser.setRegion(regionRepository.findOne(2L));
			aUser.setCustomer(new Customer("FirstName_" + i, "LastName_" + i));
			aUser.setUsername("user" + i);
			aUser.setEmail("User_" + i + "@mail.com");
			aUser.setPassword(DigestUtils.md5Hex("user"));
			aUser.setActive(true);
			listUsers.add(aUser);
		}
		this.userRepository.save(listUsers);
		
		for (Account account : listUsers) {
			account.getRoles().add(userRole);
		}
		this.userRepository.save(listUsers);
	}

	private Role getRole(final String code, final String name) {
		Role result = roleRepository.findOneByCode(code);
		if (result == null) {
			result = new Role();
			result.setCode(code);
			result.setName(name);
			roleRepository.save(result);
		}
		return result;
	}
}
