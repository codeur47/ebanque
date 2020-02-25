package io.yoropapers.ebanque.controller;

import io.yoropapers.ebanque.model.Recipient;
import io.yoropapers.ebanque.model.User;
import io.yoropapers.ebanque.service.RecipientService;
import io.yoropapers.ebanque.service.TransactionService;
import io.yoropapers.ebanque.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.math.BigDecimal;
import java.security.Principal;

@Controller
public class TransactionController {

    private TransactionService transactionService;
    private UserService userService;
    private RecipientService recipientService;

    @Autowired
    public TransactionController(TransactionService transactionService, UserService userService, RecipientService recipientService) {
        this.transactionService = transactionService;
        this.userService = userService;
        this.recipientService = recipientService;
    }

    @GetMapping("/betweenAccounts")
    public String betweenAccounts(Model model) {
        model.addAttribute("transferFrom", "");
        model.addAttribute("transferTo", "");
        model.addAttribute("amount", "");
        return "betweenAccounts";
    }

    @PostMapping("/betweenAccounts")
    public String betweenAccountsPost(
            @ModelAttribute("transferFrom") String transferFrom,
            @ModelAttribute("transferTo") String transferTo,
            @ModelAttribute("amount") String amount,
            Principal principal
    ) throws Exception {
        transactionService.betweenAccountsTransfer(transferFrom,transferTo,new BigDecimal(amount),userService.findUserByUsername(principal.getName()).getPrimaryAccount(),userService.findUserByUsername(principal.getName()).getSavingsAccount());
        return "redirect:/dashboard";
    }

    @GetMapping("/toSomeoneElse")
    public String toSomeoneElse(Model model, Principal principal) {
        model.addAttribute("recipientList", recipientService.findAllByUserUsername(userService.findUserByUsername(principal.getName()).getUsername()));
        model.addAttribute("accountType", "");
        return "toSomeoneElse";
    }

    @PostMapping("/toSomeoneElse")
    public String toSomeoneElse(@ModelAttribute("recipientLastName") String recipientLastName,
                                @ModelAttribute("recipientFirstName") String recipientFirstName,
                                Principal principal,@ModelAttribute("amount") String amount,
                                @ModelAttribute("accountType") String accountType) {
        transactionService.toSomeoneElseTransfer(recipientService.findRecipientByLastNameAndFirstName(recipientLastName,recipientFirstName),accountType,new BigDecimal(amount),
                userService.findUserByUsername(principal.getName()).getPrimaryAccount(),userService.findUserByUsername(principal.getName()).getSavingsAccount());
        return "redirect:/dashboard";
    }

    @GetMapping("/deposit")
    public String deposit(Model model, Principal principal) {
        User user = userService.findUserByUsername(principal.getName());
        model.addAttribute("user", user);
        model.addAttribute("accountType", "");
        model.addAttribute("amount", "");
        return "deposit";
    }

    @GetMapping("/withdraw")
    public String withdraw(Model model) {
        model.addAttribute("accountType", "");
        model.addAttribute("amount", "");
        return "withdraw";
    }

    @PostMapping(value="/deposit")
    public String depositPOST(@ModelAttribute("amount") String amount,
                              @ModelAttribute("accountType") String accountType, Principal principal) {
        transactionService.deposit(accountType, new BigDecimal(amount),principal);
        return "redirect:/dashboard";
    }

    @PostMapping(value="/withdraw")
    public String withdrawPOST(@ModelAttribute("amount") String amount,
                               @ModelAttribute("accountType") String accountType, Principal principal) {
        transactionService.withdraw(accountType, new BigDecimal(amount),principal);
        return "redirect:/dashboard";
    }
}
