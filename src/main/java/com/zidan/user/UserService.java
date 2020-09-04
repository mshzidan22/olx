package com.zidan.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class UserService {
   @Autowired
	private UserRepository userRepository;
   
   public void addUser(User user) {
	   userRepository.save(user);
	   
   }
   
	public User getUserByEmailAndPassword(String email ,String  password) {
	       System.out.println(userRepository.findById(new Integer(2)));
			return userRepository.findByAccountEmailAndAccountPassword(email, password);
			
			
			
		}
   
   
}
