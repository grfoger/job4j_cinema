package ru.job4j.cinema.controller;

import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.job4j.cinema.model.User;
import ru.job4j.cinema.service.UserService;

import javax.servlet.http.HttpSession;

@ThreadSafe
@Controller
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    private User getSessionUser(HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            user = new User();
            user.setName("Гость");
        }
        return user;
    }

    @GetMapping("/reg")
    public String formRegistration(Model model, @RequestParam(name = "fail", required = false) Boolean fail, HttpSession session) {
        model.addAttribute("user", getSessionUser(session));
        model.addAttribute("fail", fail != null);
        return "reg";
    }

    @GetMapping("/login")
    public String loginPage(Model model, @RequestParam(name = "fail", required = false) Boolean fail, HttpSession session) {
        model.addAttribute("fail", fail != null);
        model.addAttribute("user", getSessionUser(session));
        return "login";
    }

}
