package com.project.complaint.service;

import java.util.List;

import com.project.complaint.entity.Complaint;

public interface ComplaintService {

    Complaint saveComplaint(Complaint c);

    Complaint getComplaintById(Long id);

    List<Complaint> getAllComplaints();

    List<Complaint> searchComplaints(String keyword);

    void deleteComplaint(Long id);

    long getTotalComplaints();

    long getOpenComplaints();      // New + In Progress

    long getResolvedComplaints();  // Resolved + Closed
}
