package com.example.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entities.Users;
import com.example.demo.repositories.UsesRepository;

@Service
public class UsersServiceImplementation implements UsersService {
	@Autowired
	UsesRepository repo;
	@Override
	public String addUsers(Users user) {
		repo.save(user);
		
		return "Done";
	}
	@Override
	public boolean emailExists(String email) {
		if((repo.getByEmail(email))==null)
		{
			return false;
		}
		else
		{
			return true;
		}
	}
	@Override
	public boolean validateUser(String email, String password) {
		Users user=repo.getByEmail(email);
		String db_pass=user.getPassword();
		if(password.equals(db_pass))
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	@Override
	public String getRole(String email) {
		Users user=repo.getByEmail(email);
		return user.getRole();
	}
	@Override
	public Users getUser(String email) {
		
		return repo.getByEmail(email);
	}
	@Override
	public void updateUser(Users user) {
		repo.save(user);
		
	}

}
