package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import web.models.User;
import web.service.UserService;
import web.service.UserServiceImpl;

import javax.transaction.Transactional;

@Controller
public class HelloController {

	private UserService userService;

	public HelloController(UserService userService) {
		this.userService = userService;
	}

	@GetMapping(value = "/")
	public String getUsers(Model model) {
		model.addAttribute("listUsers", userService.getUsers());
		return "index";
	}

	@GetMapping(value = "/add")
	public String addUser(Model model) {
		User user = new User();
		model.addAttribute("user", user);
		return "addUser";
	}

	@PostMapping(value = "/add")
	public String addUser(@ModelAttribute("user") User user) {
		userService.addUser(user);
		return "redirect:/";
	}

	@GetMapping("/delete")
	public String deleteUser(@RequestParam("id") Long id) {
		userService.deleteUser(id);
		return "redirect:/";
	}

	@GetMapping(value = "/edit")
	public String editUser(@RequestParam(value = "id") long id, Model model) {
		model.addAttribute("user", userService.getUserById(id));
		return "editUser";
	}

	@PostMapping(value = "/edit")
	public String editUser(@ModelAttribute("user") User user) {
		userService.editUser(user);
		return "redirect:/";
	}
}