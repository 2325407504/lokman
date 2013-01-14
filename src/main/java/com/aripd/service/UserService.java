package com.aripd.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aripd.domain.User;
import com.aripd.repository.UserRepository;

@Service("userService")
@Transactional
public class UserService {

	protected static Logger logger = Logger.getLogger("service");

	@Autowired
	private UserRepository repository;

	public User get(Long id) {
		logger.debug("Retrieving person based on his id");
		return repository.findOne(id);
	}

	public List<User> getAll() {
		logger.debug("Retrieving all persons");
		return repository.findAll();
	}

	public Boolean save(User user) {
		User existing = repository.findOneByUsername(user.getUsername());
		if (existing != null)
			return false;

		User saved = repository.save(user);
		if (saved == null)
			return false;

		logger.debug("Adding new person");
		return true;
	}

	public Boolean update(User user) {
		User existing = repository.findOneByUsername(user.getUsername());
		if (existing == null)
			return false;

		// Only firstName, lastName, and role fields are updatable
		existing.setFirstName(user.getFirstName());
		existing.setLastName(user.getLastName());
		existing.getRole().setRole(user.getRole().getRole());

		User saved = repository.save(existing);
		if (saved == null)
			return false;

		logger.debug("Editing existing person");
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

		logger.debug("Deleting existing person");
		return true;
	}

}
