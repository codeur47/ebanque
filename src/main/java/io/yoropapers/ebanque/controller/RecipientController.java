package io.yoropapers.ebanque.controller;

import io.yoropapers.ebanque.dao.RecipientDao;
import io.yoropapers.ebanque.model.Recipient;
import io.yoropapers.ebanque.model.User;
import io.yoropapers.ebanque.service.RecipientService;
import io.yoropapers.ebanque.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
public class RecipientController {

    private RecipientService recipientService;
    private UserService userService;

    @Autowired
    public RecipientController(RecipientService recipientService, UserService userService) {
        this.recipientService = recipientService;
        this.userService = userService;
    }

    @GetMapping("/recipient")
    public String recipient(Model model, Principal principal) {
        commonElement(model,principal);
        model.addAttribute("recipient", new Recipient());
        return "addrecipient";
    }

    @GetMapping("/recipientList")
    public String recipientList(Model model, Principal principal) {
        commonElement(model,principal);
        model.addAttribute("recipientList", recipientService.findAllByUserUsername(userService.findUserByUsername(principal.getName()).getUsername()));
        return "recipientList";
    }

    @PostMapping("/saveRecipient")
    public String recipientPost(@ModelAttribute("recipient") Recipient recipient, Principal principal,Model model) {
        User user = userService.findUserByUsername(principal.getName());
        model.addAttribute("user", user);
        recipient.setUser(userService.findUserByUsername(principal.getName()));
        recipientService.save(recipient);
        return "redirect:/recipientList";
    }

    @GetMapping("/editRecipient")
    public String recipientEdit(@RequestParam("recipientId") Long recipientId,
                                Model model, Principal principal) {
        commonElement(model, principal);
        model.addAttribute("recipientList", recipientService.findAllByUserUsername(userService.findUserByUsername(principal.getName()).getUsername()));
        model.addAttribute("recipient", recipientService.findRecipientById(recipientId));
        return "addrecipient";
    }

    @GetMapping("/deleteRecipient")
    public String recipientDelete(@RequestParam("recipientId") Long recipientId,
                                  Model model, Principal principal) {
        User user = userService.findUserByUsername(principal.getName());
        model.addAttribute("user", user);
        recipientService.deleteRecipient(recipientService.findRecipientById(recipientId));
        model.addAttribute("recipientList", recipientService.findAllByUserUsername(userService.findUserByUsername(principal.getName()).getUsername()));
        model.addAttribute("recipient", new Recipient());
        return "redirect:/recipientList";
    }

    public void commonElement(Model model, Principal principal){
        User user = userService.findUserByUsername(principal.getName());
        model.addAttribute("user", user);
        model.addAttribute("primaryAccount", user.getPrimaryAccount());
        model.addAttribute("savingsAccount", user.getSavingsAccount());
    }


}
