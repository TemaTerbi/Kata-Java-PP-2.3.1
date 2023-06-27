package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import web.models.User;
import web.service.UserServiceImpl;

import javax.transaction.Transactional;

@Controller
public class HelloController {

	@Autowired
	private UserServiceImpl userService;

	@Autowired
	public void setUserService(UserServiceImpl userService) {
		this.userService = userService;
	}

	@GetMapping(value = "/")
	public String getUsers(Model model) {
		model.addAttribute("listUsers", userService.getUsers());
		return "index";
	}

	@GetMapping(value = "/add")
	public String addUserOpenPage(Model model) {
		User user = new User();
		model.addAttribute("user", user);
		return "addUser";
	}

	@PostMapping(value = "/add")
	public String addUserToDB(@ModelAttribute("user") User user) {
		userService.addUser(user);
		return "redirect:/";
	}

	@GetMapping("/delete")
	public String deleteUserById(@RequestParam("id") Long id) {
		userService.deleteUser(id);
		return "redirect:/";
	}

	@GetMapping(value = "/edit/{id}")
	public String showEditPage(Model model, @PathVariable("id") long id) {
		User user = userService.getUserById(id);
		model.addAttribute("user", user);
		return "editUser";
	}

	@PostMapping(value = "/edit")
	public String edit(@ModelAttribute("user") User user) {
		userService.editUser(user);
		return "redirect:/";
	}
}