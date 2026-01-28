package com.project.complaint.service.impl;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.project.complaint.entity.Complaint;
import com.project.complaint.repository.ComplaintRepository;
import com.project.complaint.service.ComplaintService;

@Service
public class ComplaintServiceImpl implements ComplaintService {

    private final ComplaintRepository repo;

    public ComplaintServiceImpl(ComplaintRepository repo) {
        this.repo = repo;
    }

    @Override
    public Complaint saveComplaint(Complaint c) {

        // जर new complaint असेल तर createdDate auto-set करा
        if (c.getId() == null && c.getCreatedDate() == null) {
            c.setCreatedDate(LocalDate.now());
        }

        // जर status Resolved / Closed असेल आणि resolvedDate null असेल तर आजची date सेट करा
        if (c.getStatus() != null) {
            String s = c.getStatus().toLowerCase();
            if ((s.contains("resolved") || s.contains("closed")) && c.getResolvedDate() == null) {
                c.setResolvedDate(LocalDate.now());
            }
        }

        return repo.save(c);
    }

    @Override
    public Complaint getComplaintById(Long id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    public List<Complaint> getAllComplaints() {
        return repo.findAll();
    }

    @Override
    public List<Complaint> searchComplaints(String keyword) {
        if (keyword == null || keyword.trim().isEmpty()) {
            return repo.findAll();
        }
        return repo.search(keyword.trim());
    }

    @Override
    public void deleteComplaint(Long id) {
        repo.deleteById(id);
    }

    @Override
    public long getTotalComplaints() {
        return repo.count();
    }

    @Override
    public long getOpenComplaints() {
        long newCnt = repo.countByStatus("New");
        long inProgress = repo.countByStatus("In Progress");
        return newCnt + inProgress;
    }

    @Override
    public long getResolvedComplaints() {
        long resolved = repo.countByStatus("Resolved");
        long closed = repo.countByStatus("Closed");
        return resolved + closed;
    }
}
