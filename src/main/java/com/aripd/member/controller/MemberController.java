package com.aripd.member.controller;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aripd.member.domain.Member;
import com.aripd.member.domain.Role;
import com.aripd.member.service.MemberService;
import com.aripd.member.service.RoleService;
import com.aripd.common.dto.datatables.DatatablesCriteria;
import com.aripd.common.dto.datatables.DatatablesResultSet;
import com.aripd.common.dto.datatables.DatatablesParam;
import com.aripd.common.dto.WebResultSet;
import com.aripd.common.dto.ControllerUtils;
import java.util.List;
import org.springframework.beans.propertyeditors.CustomCollectionEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@PreAuthorize("hasRole('ROLE_SUPERADMIN')")
@Controller
@RequestMapping("/member")
public class MemberController {

    private static final String PATH = "/member";
    private static final String LIST_VIEW = PATH + "/list";
    private static final String SHOW_VIEW = PATH + "/show";
    private static final String FORM_VIEW = PATH + "/form";
    @Resource(name = "memberService")
    private MemberService memberService;
    @Resource(name = "roleService")
    private RoleService roleService;

    /**
     * TODO Bunun yerine başka ne yapabiliriz?
     *
     * @param binder
     * @throws Exception
     */
    @InitBinder
    protected void initBinder(WebDataBinder binder) throws Exception {
        binder.registerCustomEditor(List.class, "roles", new CustomCollectionEditor(List.class) {
            @Override
            protected Object convertElement(Object element) {
                if (element instanceof Role) {
                    return element;
                }
                if (element instanceof String) {
                    return roleService.findOne(Long.valueOf(element.toString()));
                }
                return null;
            }
        });
    }

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public @ResponseBody
    WebResultSet<Member> datatablesAction(@DatatablesParam DatatablesCriteria criteria) {
        DatatablesResultSet<Member> resultset = this.memberService.getRecords(criteria);
        return ControllerUtils.getDatatablesResultSet(criteria, resultset);
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String listAction(Model model) {
        return LIST_VIEW;
    }

    @RequestMapping(value = "/show/{id}", method = RequestMethod.GET)
    public String showAction(@PathVariable Long id, Model model) {
        model.addAttribute("memberAttribute", memberService.findOne(id));
        return SHOW_VIEW;
    }

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String newAction(Model model) {
        model.addAttribute("roles", roleService.findAll());
        model.addAttribute("memberAttribute", new Member());
        return FORM_VIEW;
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String editAction(@PathVariable Long id, Model model) {
        model.addAttribute("roles", roleService.findAll());
        model.addAttribute("memberAttribute", memberService.findOne(id));
        return FORM_VIEW;
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveAction(
            final RedirectAttributes redirectAttributes,
            @ModelAttribute("memberAttribute") @Valid Member formData,
            BindingResult result,
            Model model) {

        if (result.hasErrors()) {
            model.addAttribute("roles", roleService.findAll());
            return FORM_VIEW;
        }

        if (formData.getId() != null) {
            Member member = memberService.findOne(formData.getId());
            if (formData.getPassword().length() == 0) {
                formData.setPassword(member.getPassword());
            } else {
                formData.setPassword(DigestUtils.md5Hex(formData.getPassword()));
            }
        } else {
            formData.setPassword(DigestUtils.md5Hex(formData.getPassword()));
        }

        Member a = memberService.save(formData);
        redirectAttributes.addFlashAttribute("message", "message.completed.save");
        return "redirect:/member/show/" + a.getId();
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public String delete(@RequestParam(value = "id", required = true) Long id) {
        memberService.delete(id);
        return "redirect:" + LIST_VIEW;
    }
}
