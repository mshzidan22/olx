package com.zidan.user;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface UserRepository extends CrudRepository<User, Integer> {

	public User findByAccountEmailAndAccountPassword(String email , String password);
	public User findByAccountEmail(String email);
}
