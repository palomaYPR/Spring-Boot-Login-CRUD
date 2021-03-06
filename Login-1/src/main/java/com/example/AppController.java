package com.example;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.service.UserService;
 
@Controller
public class AppController {
	@Autowired
	private UserRepository userRepo;
	
	 @Autowired
	 private UserService userService;
	
	@GetMapping("")
	public String viewHomePage() {
		return "index";
	}
	
	@GetMapping("/register")
	public String showRegistrationForm(Model model) {
	    model.addAttribute("user", new User());
	     
	    return "signup_form";
	}
	
	@PostMapping("/process_register")
	public String processRegister(User user) {
	    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	    String encodedPassword = passwordEncoder.encode(user.getPassword());
	    user.setPassword(encodedPassword);
	    System.out.print("HASH: " + passwordEncoder);
	    userRepo.save(user);
	     
	    return "register_success";
	}
	
	@GetMapping("/users")
	public String listUsers(Model model) {
		//List<User> listUsers = userRepo.findAll();
		//model.addAttribute("listUsers", listUsers);
		model.addAttribute("list", userService.getAll());
		return "users";
	}
	
	@GetMapping("/save/{id}")
	 public String showSave(@PathVariable("id") Long id, Model model) {
	  if (id != null && id != 0) {
	   model.addAttribute("user", userService.get(id));
	  } else {
	   model.addAttribute("user", new User());
	  }
	  return "save";	
	 }
	 
	 @PostMapping("/save")
	 public String save(User persona, Model model) {
	  userService.save(persona);
	  return "redirect:/users";
	 }
	 
	 @GetMapping("/delete/{id}")
	 public String delete(@PathVariable Long id, Model model) {
	  userService.delete(id);
	 
	  return "redirect:/users";
	 }
}
