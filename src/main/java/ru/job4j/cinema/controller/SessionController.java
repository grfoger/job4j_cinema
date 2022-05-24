package ru.job4j.cinema.controller;

import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.job4j.cinema.model.User;
import ru.job4j.cinema.service.SessionService;
import ru.job4j.cinema.service.UserService;

@Controller
@ThreadSafe
public class SessionController {
    private final SessionService sessionService;
    private final UserService userService;

    public SessionController(SessionService sessionService, UserService userService) {
        this.sessionService = sessionService;
        this.userService = userService;
    }

    @GetMapping("/index")
    public String index(Model model) {
        model.addAttribute("films", sessionService.getAllFree());
        return "index";
    }

    @GetMapping("/tickets")
    public String tickets(Model model) {
        model.addAttribute("tickets", userService.getUserTickets(new User()));
        return "tickets";
    }

    @GetMapping("/login")
    public String login(Model model) {
        return "login";
    }

    @GetMapping("/reg")
    public String reg(Model model) {
        return "reg";
    }
}
