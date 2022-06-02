package ru.job4j.cinema.controller;

import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.job4j.cinema.model.Ticket;
import ru.job4j.cinema.model.User;
import ru.job4j.cinema.service.SessionService;
import ru.job4j.cinema.service.UserService;

import javax.servlet.http.HttpSession;
import java.util.Collection;
import java.util.List;

@Controller
@ThreadSafe
public class SessionController {
    private final SessionService sessionService;
    private final UserService userService;

    private User getSessionUser(HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            user = new User();
            user.setName("Гость");
        }
        return user;
    }

    public SessionController(SessionService sessionService, UserService userService) {
        this.sessionService = sessionService;
        this.userService = userService;
    }

    @GetMapping("/index")
    public String index(Model model, HttpSession session) {
        model.addAttribute("user", getSessionUser(session));
        model.addAttribute("films", sessionService.getAll());
        model.addAttribute("ticket", new Ticket());
        return "index";
    }

    @PostMapping("/takeRow")
    public String takeRowPost(@ModelAttribute Ticket ticket, HttpSession session) {
        session.setAttribute("ticket", ticket);
        return "redirect:/takeRow";
    }

    @GetMapping("/takeRow")
    public String takeRowGet(Model model, HttpSession session) {
        model.addAttribute("user", getSessionUser(session));
        model.addAttribute("rows", sessionService.rows());
        return "takeRow";
    }

    @PostMapping("/takeCell")
    public String takeCellPost(@ModelAttribute Ticket ticket, HttpSession session) {
        Ticket sessionTicket = (Ticket) session.getAttribute("ticket");
        sessionTicket.setRow(ticket.getRow());
        return "redirect:/takeCell";
    }

    @GetMapping("/takeCell")
    public String takeCellGet(Model model, HttpSession session) {
        Ticket sessionTicket = (Ticket) session.getAttribute("ticket");
        model.addAttribute("user", getSessionUser(session));
        model.addAttribute("cells", sessionService.freeCells(sessionTicket.getSessionId(), sessionTicket.getRow()));
        return "takeCell";
    }

    @PostMapping("/tickets")
    public String ticketsPost(@ModelAttribute Ticket ticket, HttpSession session) {
        Ticket sessionTicket = (Ticket) session.getAttribute("ticket");
        sessionTicket.setCell(ticket.getCell());
        sessionTicket.setUserId(getSessionUser(session).getId());
        sessionService.addTicket(sessionTicket);
        return "redirect:/tickets";
    }

    @GetMapping("/tickets")
    public String tickets(Model model, HttpSession session) {
        User user = (User) session.getAttribute("user");
        model.addAttribute("user", getSessionUser(session));
        model.addAttribute("tickets", userService.getUserTickets(user));
        return "tickets";
    }

}
