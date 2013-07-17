package com.aripd.project.lgk.controller;

import javax.annotation.Resource;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.aripd.project.lgk.domain.Proc;
import com.aripd.project.lgk.service.ProcService;

@PreAuthorize("hasRole('ROLE_SUPERADMIN') or hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
@Controller
@RequestMapping("/userproc")
public class UserProcController {

    @Resource(name = "procService")
    private ProcService procService;

    @RequestMapping(value = "/list")
    public String listAction(Model model) {
        model.addAttribute("userprocs", procService.findByActive(true));
        return "/userproc/list";
    }

    @RequestMapping(value = "/show/{id}", method = RequestMethod.GET)
    public String showAction(
            final RedirectAttributes redirectAttributes,
            @PathVariable Long id,
            Model model) {

        Proc proc = procService.findOne(id);

        if (proc instanceof Proc == false) {
            redirectAttributes.addFlashAttribute("message", "message.record.not.access");
            return "redirect:/userproc/list";
        }

        model.addAttribute("userprocAttribute", proc);
        return "/userproc/show";
    }
}
