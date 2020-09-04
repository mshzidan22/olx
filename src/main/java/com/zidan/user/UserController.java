package com.zidan.user;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
@Controller
public class UserController {
    @Autowired  
	private UserService userService;
	
	@GetMapping("/register")
	public String register(Model model) {
		model.addAttribute("user", new User());
		return "register";
	}
	
   @PostMapping("/register")
   public String addUser(@Valid @ModelAttribute("user") User user ,BindingResult result) {
	   if(result.hasErrors()) {
		   return "register";
	   }
	   userService.addUser(user);
	   System.out.println(result.getErrorCount());
	   return "redirect:login";
   }
   
   @GetMapping("/login")
	public String login(Model model) {
		model.addAttribute("user", new User());
		
		return "login";
	}
	
	@PostMapping("/login")
	public String chkLogin(@ModelAttribute("user") User user ,HttpSession session) {
		User userd=userService.getUserByEmailAndPassword(user.getAccount().getEmail(), user.getAccount().getPassword());
		if(userd == null) {
			return "login";
		}
		else
		{
			session.setAttribute("user", userd);
			return "redirect:home";
		}
	}
}
