package io.yoropapers.ebanque.controller;

import java.security.Principal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import io.yoropapers.ebanque.service.RecipientService;
import io.yoropapers.ebanque.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import io.yoropapers.ebanque.model.User;
import io.yoropapers.ebanque.model.UserRole;
import io.yoropapers.ebanque.service.RoleService;
import io.yoropapers.ebanque.service.UserService;

/**
 * HomeController
 */
@Controller
public class HomeController {

    private UserService userService;
    private RoleService roleService;
    private TransactionService transactionService;
    private RecipientService recipientService;

    @Autowired
    public HomeController(UserService userService, RoleService roleService,TransactionService transactionService,RecipientService recipientService) {
        this.userService = userService;
        this.roleService = roleService;
        this.transactionService = transactionService;
        this.recipientService = recipientService;
    }

    @GetMapping("/")
    public String home() {
        return "redirect:/login";
    }

    
    @GetMapping("/index")
    public String index() {
        return "login";
    }
    
    
    @GetMapping("/login")
    public String login() {
        return "login";
    }

    
    @GetMapping("/page-register")
    public String signup(Model model) {
        model.addAttribute("user", new User());
        return "page-register";
    }

    @PostMapping("/page-register")
    public String createUser(@ModelAttribute("user") User user, Model model) {
        if(userService.existsUsersByUsernameAndEmail(user.getUsername(), user.getEmail())){
            if(userService.existsUserByUsername(user.getUsername())) model.addAttribute("usernameExists","true");
            if(userService.existsUsersByEmail(user.getEmail())) model.addAttribute("emailExists","true");
            return "page-register";
        }else {
            Set<UserRole> userRoles = new HashSet<>();
            userRoles.add(new UserRole(user, roleService.findRoleByName("ROLE_USER")));
            userService.createUser(user, userRoles);
            return "redirect:/";
        }        
    }

    @GetMapping("/dashboard")
    public String dashboard(Principal principal, Model model){
        User user = userService.findUserByUsername(principal.getName());
        commonElement(model,principal);
        model.addAttribute("recipientList", recipientService.findAllByUserUsername(userService.findUserByUsername(principal.getName()).getUsername()));
        model.addAttribute("primaryTransactionList", transactionService.findPrimaryTransactionListByUsername(user.getUsername()).stream()
                                                                    .sorted((pt1, pt2)-> pt2.getDate().compareTo(pt1.getDate()))
                                                                    .limit(5)
                                                                    .collect(Collectors.toList()));
        model.addAttribute("savingsTransactionList", transactionService.findSavingsTransactionListByUsername(user.getUsername()).stream()
                                                                    .sorted((pt1, pt2)-> pt2.getDate().compareTo(pt1.getDate()))
                                                                    .limit(5)
                                                                    .collect(Collectors.toList()));

        return "dashboard";
    }

    @GetMapping("/primaryaccount")
    public String primaryAccount(Principal principal, Model model){
        commonElement(model,principal);
        model.addAttribute("primaryTransactionList", transactionService.findPrimaryTransactionListByUsername(userService.findUserByUsername(principal.getName()).getUsername()).stream()
                .sorted((pt1, pt2)-> pt2.getDate().compareTo(pt1.getDate()))
                .collect(Collectors.toList()));

        return "primaryaccount";
    }

    @GetMapping("/savingsaccount")
    public String savingsAccount(Principal principal, Model model){
        commonElement(model,principal);
        model.addAttribute("savingsTransactionList", transactionService.findSavingsTransactionListByUsername(userService.findUserByUsername(principal.getName()).getUsername()).stream()
                .sorted((pt1, pt2)-> pt2.getDate().compareTo(pt1.getDate()))
                .collect(Collectors.toList()));

        return "savingsaccount";
    }

    @GetMapping("/profile")
    public String profile(Principal principal, Model model){
        commonElement(model,principal);
        return "profile";
    }

    public void commonElement(Model model, Principal principal){
        User user = userService.findUserByUsername(principal.getName());
        model.addAttribute("user", user);
        model.addAttribute("primaryAccount", user.getPrimaryAccount());
        model.addAttribute("savingsAccount", user.getSavingsAccount());
    }


}