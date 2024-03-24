package com.placementcell.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.placementcell.entities.Users;
import com.placementcell.repository.UserRepository;
@Service
public class UserInfoServices implements UserDetailsService {

	@Autowired
	private UserRepository userRepository; 
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		Optional<Users> user= userRepository.FindByEmail(username);
	    return user.map(UserInfoDetails::new).orElseThrow(()-> new UsernameNotFoundException("User Not Found"+ username));
	}
	
	public String addUser(Users users)
	{
		System.out.println(users);
		
		users.setPassword(passwordEncoder.encode(users.getPassword()));
		Users u=userRepository.save(users);
		System.out.println(u);
		return "User added Successfully";
	}

	
	
}
