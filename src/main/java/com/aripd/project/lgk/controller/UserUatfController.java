package com.aripd.project.lgk.controller;

import javax.annotation.Resource;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.aripd.common.dto.datatables.DatatablesCriteria;
import com.aripd.common.dto.datatables.DatatablesResultSet;
import com.aripd.common.dto.datatables.DatatablesParam;
import com.aripd.common.dto.WebResultSet;
import com.aripd.common.dto.ControllerUtils;
import com.aripd.project.lgk.domain.Forwarding;
import com.aripd.project.lgk.domain.Uatf;
import com.aripd.project.lgk.service.ForwardingService;
import com.aripd.project.lgk.service.UatfService;
import javax.validation.Valid;

@PreAuthorize("hasRole('ROLE_USER')")
@Controller
@RequestMapping("/useruatf")
public class UserUatfController {

    @Resource(name = "forwardingService")
    private ForwardingService forwardingService;
    @Resource(name = "uatfService")
    private UatfService uatfService;

    @RequestMapping(value = "/get/{forwarding_id}", method = RequestMethod.GET)
    public @ResponseBody
    WebResultSet<Uatf> datatablesAction(@PathVariable Long forwarding_id, @DatatablesParam DatatablesCriteria criteria) {
        return ControllerUtils.getDatatablesResultSet(criteria, this.uatfService.getRecords(forwarding_id, criteria));
    }

    @RequestMapping(value = "/save/{forwarding_id}", method = RequestMethod.POST)
    public String saveAction(
            final RedirectAttributes redirectAttributes,
            @PathVariable Long forwarding_id,
            @ModelAttribute("useruatfAttribute") @Valid Uatf formData,
            BindingResult result,
            Model model) {

        Forwarding forwarding = forwardingService.findOne(forwarding_id);
        if (forwarding.isSubmitted()) {
            redirectAttributes.addFlashAttribute("message", "Bu kaydı artık düzenleyemezsiniz");
            return "redirect:/userforwarding/show/" + forwarding.getId();
        }

        if (result.hasErrors()) {
            redirectAttributes.addFlashAttribute("message", "Bütün alanları doldurmalısınız");
            return "redirect:/userforwarding/edit/" + forwarding_id;
        }

        formData.setForwarding(forwarding);

        uatfService.save(formData);
        return "redirect:/userforwarding/edit/" + forwarding_id;
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    public String delete(
            final RedirectAttributes redirectAttributes,
            @PathVariable Long id) {

        Uatf uatf = uatfService.findOne(id);

        if (uatf.getForwarding().isSubmitted()) {
            redirectAttributes.addFlashAttribute("message", "Bu kaydı artık düzenleyemezsiniz");
            return "redirect:/userforwarding/show/" + uatf.getForwarding().getId();
        }

        uatfService.delete(uatf);
        return "redirect:/userforwarding/edit/" + uatf.getForwarding().getId();
    }
}
