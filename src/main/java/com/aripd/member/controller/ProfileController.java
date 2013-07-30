package com.aripd.member.controller;

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

import com.aripd.member.domain.Member;
import com.aripd.member.model.ProfileForm;
import com.aripd.member.service.MemberService;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@PreAuthorize("isFullyAuthenticated()")
@Controller
@RequestMapping("/profile")
public class ProfileController {

    private static final String PATH = "/profile";
    private static final String SHOW_VIEW = PATH + "/show";
    private static final String FORM_VIEW = PATH + "/form";
    @Resource(name = "memberService")
    private MemberService memberService;

    @RequestMapping(value = "/show", method = RequestMethod.GET)
    public String showAction(Principal principal, Model model) {
        Member member = memberService.findOneByUsername(principal.getName());

        model.addAttribute("profileAttribute", member);
        return SHOW_VIEW;
    }

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String editAction(
            Principal principal,
            Model model) {
        Member member = memberService.findOneByUsername(principal.getName());

        ProfileForm profileForm = new ProfileForm();
        profileForm.setEmail(member.getEmail());
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

        Member member = memberService.findOneByUsername(principal.getName());

        member.setEmail(formData.getEmail());
        if (formData.getPassword().length() == 0) {
            member.setPassword(member.getPassword());
        } else {
            member.setPassword(DigestUtils.md5Hex(formData.getPassword()));
        }

        memberService.save(member);
        redirectAttributes.addFlashAttribute("message", "message.completed.save");
        return "redirect:" + SHOW_VIEW;
    }
}
