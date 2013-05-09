package com.aripd.common.controller;

import java.io.File;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Value;
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

	@Value("${path.directory.export}")
	String pathDirectoryExport;

	@Resource(name = "backupService")
	private BackupService backupService;

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String listAction(Model model) {
		File[] files = backupService.findAll(pathDirectoryExport, "sql");
		model.addAttribute("files", files);
		return "backup/list";
	}

	@RequestMapping(value = "/take", method = RequestMethod.GET)
	public String takeAction() {
		backupService.database();
		return "redirect:/backup/list";
	}

	@RequestMapping(value = "/{file}/delete", method = RequestMethod.GET)
	public String removeAction(
			final RedirectAttributes redirectAttributes,
			@PathVariable String file, 
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