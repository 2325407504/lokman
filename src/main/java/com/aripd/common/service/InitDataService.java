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

    private static final Logger log = Logger.getLogger(InitDataService.class);

    @Autowired
    AccountRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    public void init() {
        Role adminRole = getRole("ROLE_ADMIN");
        Role userRole = getRole("ROLE_USER");

        /* A user with admin right */
        Account admin = new Account();
        admin.setUsername("john");
        admin.setFirstName("John");
        admin.setLastName("Doe");
        admin.setEmail("admin@mail.com");
        admin.setPassword(DigestUtils.md5Hex("admin"));
        admin.setIsEnabled(true);
        this.userRepository.save(admin);
        admin.getRoles().add(adminRole);
        admin.getRoles().add(userRole);
        this.userRepository.save(admin);
        
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

        // add 100 more users without roles (To test pagination)
        List<Account> listUsers = new ArrayList<Account>();
        Account aUser;
        for (int i = 0; i < 100; i++) {
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
