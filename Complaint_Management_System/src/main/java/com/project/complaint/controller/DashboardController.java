package com.project.complaint.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.project.complaint.service.ComplaintService;

@Controller
public class DashboardController {

    private final ComplaintService service;

    public DashboardController(ComplaintService service) {
        this.service = service;
    }

    // http://localhost:9127/complaint/
    @GetMapping("/")
    public String dashboard(Model model) {

        model.addAttribute("total", service.getTotalComplaints());
        model.addAttribute("open", service.getOpenComplaints());
        model.addAttribute("resolved", service.getResolvedComplaints());
        model.addAttribute("complaints", service.getAllComplaints());

        return "dashboard";
    }
}
