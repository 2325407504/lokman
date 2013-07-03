package com.aripd.account.controller;

import java.security.Principal;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.aripd.account.domain.Account;
import com.aripd.account.service.AccountService;

@PreAuthorize("isFullyAuthenticated()")
@Controller
@RequestMapping("/profile")
public class ProfileController {

    @Resource(name = "accountService")
    private AccountService accountService;

    @RequestMapping(value = "/show", method = RequestMethod.GET)
    public String showAction(Principal principal, Model model) {
        Account account = accountService.findOneByUsername(principal.getName());

        model.addAttribute("profileAttribute", account);
        return "profile/show";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String editAction(
            Principal principal,
            Model model) {
        Account account = accountService.findOneByUsername(principal.getName());

        model.addAttribute("profileAttribute", account);
        return "profile/form";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveAction(
            Principal principal,
            @ModelAttribute("profileAttribute") @Valid Account formData,
            BindingResult result) {

        if (result.hasErrors()) {
            return "/profile/form";
        }

        Account account = accountService.findOneByUsername(principal.getName());

        formData.getClient().setId(account.getClient().getId());
        account.setClient(formData.getClient());
        account.setUsername(formData.getUsername());
        account.setEmail(formData.getEmail());
        if (formData.getPassword().length() == 0) {
            account.setPassword(account.getPassword());
        } else {
            account.setPassword(DigestUtils.md5Hex(formData.getPassword()));
        }

        accountService.save(account);
        return "redirect:/profile/show";
    }
}
