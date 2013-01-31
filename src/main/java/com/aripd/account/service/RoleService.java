package com.aripd.account.service;

import java.util.List;

import com.aripd.account.domain.Role;
import com.aripd.account.dto.RoleDto;
import com.aripd.account.exception.RoleNotFoundException;

/**
 * Declares methods used to obtain and modify role information.
 * 
 * @author aripd.com
 */
public interface RoleService {

	public Role getOne(Long id);

	public List<Role> getAll();

	public Role save(RoleDto formData) throws RoleNotFoundException;

	/**
	 * Creates a new role.
	 * 
	 * @param created
	 *            The information of the created role.
	 * @return The created role.
	 */
	public Role create(RoleDto created);

	/**
	 * Deletes an role.
	 * 
	 * @param id
	 *            The id of the deleted role.
	 * @return The deleted role.
	 * @throws RoleNotFoundException
	 *             if no role is found with the given id.
	 */
	public Role delete(Long id) throws RoleNotFoundException;

	/**
	 * Finds all roles.
	 * 
	 * @return A list of roles.
	 */
	public List<Role> findAll();

	/**
	 * Finds role by id.
	 * 
	 * @param id
	 *            The id of the wanted role.
	 * @return The found role. If no role is found, this method returns
	 *         null.
	 */
	public Role findById(Long id);

	/**
	 * Updates the information of an role.
	 * 
	 * @param updated
	 *            The information of the updated role.
	 * @return The updated role.
	 * @throws RoleNotFoundException
	 *             if no person is found with given id.
	 */
	public Role update(RoleDto role) throws RoleNotFoundException;

}
