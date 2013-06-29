package com.aripd.common.controller;

import javax.annotation.Resource;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.aripd.common.service.BackupService;

@PreAuthorize("hasRole('ROLE_SUPERADMIN')")
@Controller
@RequestMapping("/backup")
public class BackupController {

    @Resource(name = "backupService")
    private BackupService backupService;

    @RequestMapping(value = "/list")
    public String listAction(Model model) {
        model.addAttribute("files", backupService.findAll());
        return "backup/list";
    }

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String newAction(
            final RedirectAttributes redirectAttributes,
            Model model) {
        backupService.backup();
        redirectAttributes.addFlashAttribute("message", "Başarı ile yedek alındı.");
        return "redirect:/backup/list";
    }

    @RequestMapping(value = "/{file}/delete", method = RequestMethod.GET)
    public String deleteAction(
            final RedirectAttributes redirectAttributes,
            @PathVariable(value = "file") String file,
            Model model) {
        backupService.delete(file);
        redirectAttributes.addFlashAttribute("message", "Başarı ile silindi.");
        return "redirect:/backup/list";
    }

    @RequestMapping(value = "/{file}/restore", method = RequestMethod.GET)
    public String restoreAction(
            final RedirectAttributes redirectAttributes,
            @PathVariable String file,
            Model model) {
        backupService.restore(file);
        redirectAttributes.addFlashAttribute("message", "Sisteme veriler geri yüklendi.");
        return "redirect:/backup/list";
    }
}