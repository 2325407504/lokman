package com.aripd.member.controller;

import com.aripd.member.domain.Memberlog;
import com.aripd.member.model.MemberlogFilterByIntervalForm;
import com.aripd.member.service.MemberService;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.aripd.member.service.MemberlogService;
import com.aripd.common.dto.datatables.DatatablesCriteria;
import com.aripd.common.dto.datatables.DatatablesResultSet;
import com.aripd.common.dto.datatables.DatatablesParam;
import com.aripd.common.dto.WebResultSet;
import com.aripd.common.dto.ControllerUtils;

@PreAuthorize("hasRole('ROLE_SUPERADMIN') or hasRole('ROLE_ADMIN')")
@Controller
@RequestMapping("/memberlog")
public class MemberlogController {

    private static final String PATH = "/memberlog";
    private static final String LIST_VIEW = PATH + "/list";
    private static final String REPORT_VIEW = PATH + "/report";
    @Resource(name = "memberlogService")
    private MemberlogService memberlogService;
    @Resource(name = "memberService")
    private MemberService memberService;

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public @ResponseBody
    WebResultSet<Memberlog> datatablesAction(@DatatablesParam DatatablesCriteria criteria) {
        DatatablesResultSet<Memberlog> resultset = this.memberlogService.getRecords(criteria);
        return ControllerUtils.getDatatablesResultSet(criteria, resultset);
    }

    @RequestMapping(value = "/list")
    public String listAction(Model model) {
        return LIST_VIEW;
    }

    @RequestMapping(value = "/report", method = RequestMethod.GET)
    public String reportAction(Model model) {
        model.addAttribute("members", memberService.findAll());
        model.addAttribute("memberlogFilterByIntervalForm", new MemberlogFilterByIntervalForm());
        return REPORT_VIEW;
    }

    @RequestMapping(value = "/report", method = RequestMethod.POST)
    public String reportAction(
            final RedirectAttributes redirectAttributes,
            @ModelAttribute("memberlogFilterByIntervalForm") @Valid MemberlogFilterByIntervalForm formData,
            BindingResult result,
            HttpServletResponse response,
            Model model) {

        if (result.hasErrors()) {
            model.addAttribute("members", memberService.findAll());
            return REPORT_VIEW;
        }

        memberlogService.export(response, formData);
        return "redirect:" + REPORT_VIEW;
    }
}
