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
import com.aripd.account.model.ProfileForm;
import com.aripd.account.service.AccountService;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@PreAuthorize("isFullyAuthenticated()")
@Controller
@RequestMapping("/profile")
public class ProfileController {

    private static final String PATH = "/profile";
    private static final String SHOW_VIEW = PATH + "/show";
    private static final String FORM_VIEW = PATH + "/form";
    @Resource(name = "accountService")
    private AccountService accountService;

    @RequestMapping(value = "/show", method = RequestMethod.GET)
    public String showAction(Principal principal, Model model) {
        Account account = accountService.findOneByUsername(principal.getName());

        model.addAttribute("profileAttribute", account);
        return SHOW_VIEW;
    }

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String editAction(
            Principal principal,
            Model model) {
        Account account = accountService.findOneByUsername(principal.getName());

        ProfileForm profileForm = new ProfileForm();
        profileForm.setEmail(account.getEmail());
        profileForm.setPassword(null);

        model.addAttribute("profileAttribute", profileForm);
        return FORM_VIEW;
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveAction(
            final RedirectAttributes redirectAttributes,
            Principal principal,
            @ModelAttribute("profileAttribute") @Valid ProfileForm formData,
            BindingResult result) {

        if (result.hasErrors()) {
            return FORM_VIEW;
        }

        Account account = accountService.findOneByUsername(principal.getName());

        account.setEmail(formData.getEmail());
        if (formData.getPassword().length() == 0) {
            account.setPassword(account.getPassword());
        } else {
            account.setPassword(DigestUtils.md5Hex(formData.getPassword()));
        }

        accountService.save(account);
        redirectAttributes.addFlashAttribute("message", "message.completed.save");
        return "redirect:" + SHOW_VIEW;
    }
}
