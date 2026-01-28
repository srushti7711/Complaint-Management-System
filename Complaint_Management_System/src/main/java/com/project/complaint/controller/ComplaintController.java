package com.project.complaint.controller;

import com.project.complaint.entity.Complaint;
import com.project.complaint.service.ComplaintService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class ComplaintController {

    private final ComplaintService service;

    public ComplaintController(ComplaintService service) {
        this.service = service;
    }

    // LIST + SEARCH  ->  /complaint/complaints
    @GetMapping("/complaints")
    public String list(@RequestParam(required = false) String keyword, Model model) {
        model.addAttribute("complaints", service.searchComplaints(keyword));
        model.addAttribute("keyword", keyword);
        return "complaints-list";
    }

    // NEW FORM  -> /complaint/complaints/new
    @GetMapping("/complaints/new")
    public String showForm(Model model) {
        model.addAttribute("complaint", new Complaint());
        return "complaint-form";
    }

    // EDIT FORM  -> /complaint/complaints/edit/{id}
    @GetMapping("/complaints/edit/{id}")
    public String edit(@PathVariable Long id, Model model) {
        Complaint c = service.getComplaintById(id);
        if (c == null) {
            return "redirect:/complaints";
        }
        model.addAttribute("complaint", c);
        return "complaint-form";
    }

    // VIEW DETAILS  -> /complaint/complaints/view/{id}
    @GetMapping("/complaints/view/{id}")
    public String view(@PathVariable Long id, Model model) {
        Complaint c = service.getComplaintById(id);
        if (c == null) {
            return "redirect:/complaints";
        }
        model.addAttribute("complaint", c);
        return "complaint-view";
    }

    // SAVE / UPDATE  -> POST /complaint/complaints/save
    @PostMapping("/complaints/save")
    public String save(@ModelAttribute Complaint complaint,
                       RedirectAttributes ra) {
        service.saveComplaint(complaint);
        ra.addFlashAttribute("msg", "Complaint saved successfully!");
        return "redirect:/complaints";
    }

    // DELETE  -> /complaint/complaints/delete/{id}
    @GetMapping("/complaints/delete/{id}")
    public String delete(@PathVariable Long id, RedirectAttributes ra) {
        service.deleteComplaint(id);
        ra.addFlashAttribute("msg", "Complaint deleted successfully!");
        return "redirect:/complaints";
    }
}
