package com.aripd.service;

import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aripd.domain.User;
import com.aripd.repository.UserRepository;

@Service("userService")
@Transactional
public class UserService {

	protected static Logger logger4J = Logger.getLogger(UserService.class);

	@Autowired
	private UserRepository repository;

	public User getOne(Long id) {
		logger4J.debug("Retrieving person based on his id");
		return repository.findOne(id);
	}

	public User getOneByUsername(String username) {
		logger4J.debug("Retrieving person based on his username");
		return repository.findOneByUsername(username);
	}

	@PreAuthorize("isFullyAuthenticated()")
	public User getActiveUser() {
		org.springframework.security.core.userdetails.User securityUser = (org.springframework.security.core.userdetails.User) (SecurityContextHolder.getContext()).getAuthentication().getPrincipal();
		return getOneByUsername(securityUser.getUsername());
	}

	@PreAuthorize("isFullyAuthenticated()")
	public boolean hasUsername(String username) {
		List<User> list = repository.findAll();
		list.remove(getActiveUser());
		
		Iterator<User> iterator = list.iterator();
		while (iterator.hasNext()) {
			if (iterator.next().getUsername().equals(username)) {
				return true;
			}
		}
		return false;
	}

	public List<User> getAll() {
		logger4J.debug("Retrieving all persons");
		return repository.findAll();
	}

	public Boolean save(User user) {
		//User existing = repository.findOneByUsername(user.getUsername());
		//if (existing != null)
			//return false;

		User saved = repository.save(user);
		if (saved == null)
			return false;

		logger4J.debug("Adding new person");
		return true;
	}

	public Boolean update(User user) {
		User existing = repository.findOneByUsername(user.getUsername());
		if (existing == null)
			return false;

		existing.setFirstname(user.getFirstname());
		existing.setLastname(user.getLastname());

		User saved = repository.save(existing);
		if (saved == null)
			return false;

		logger4J.debug("Editing existing person");
		return true;
	}

	public void delete(Long id) {
		repository.delete(repository.findOne(id));
	}

	public Boolean delete(User user) {
		User existing = repository.findOne(user.getId());
		if (existing == null)
			return false;

		repository.delete(existing);
		User deleted = repository.findOne(user.getId());
		if (deleted != null)
			return false;

		logger4J.debug("Deleting existing person");
		return true;
	}

}
