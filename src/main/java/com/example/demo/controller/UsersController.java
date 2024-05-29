package com.example.demo.controller;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.entities.Song;
import com.example.demo.entities.Users;
import com.example.demo.services.SongService;
import com.example.demo.services.UsersService;

import jakarta.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;



@Controller
public class UsersController {
	@Autowired
	UsersService service;
	@Autowired
	SongService songService;
	@PostMapping("/register")
	public String addUsers(@ModelAttribute Users user)
	{
		boolean userStatus=service.emailExists(user.getEmail());
		if(userStatus==false)
		{
			service.addUsers(user);
			System.out.println("User added");
		}
		else
		{
			System.out.println("Email exists");
		}
		return "home";
	}
	@PostMapping("/validate")
	public String validateUser(@RequestParam("email") String email, @RequestParam("password") String password, HttpSession session, Model model) 
	{
		
		if(service.validateUser(email,password)==true)
		{
			session.setAttribute("email", email);
			String role=service.getRole(email);
			if(role.equals("admin"))
			{
				return "adminHome";
			}
			else
			{
				Users user=service.getUser(email);
				boolean userStatus=user.isPremium();
				model.addAttribute("isPremium", userStatus);
				List<Song> songsList=songService.fetchAllSongs();
				model.addAttribute("songs", songsList);
				return "customerHome";
			}
		}
		else
		{
			return "login";
		}
	}
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		
		return "login";
	}
	
	
	

}
