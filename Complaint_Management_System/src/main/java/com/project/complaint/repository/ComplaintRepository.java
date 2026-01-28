package com.project.complaint.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.project.complaint.entity.Complaint;

public interface ComplaintRepository extends JpaRepository<Complaint, Long> {

    @Query("SELECT c FROM Complaint c WHERE " +
           "LOWER(c.title) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
           "LOWER(c.category) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
           "LOWER(c.status) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
           "LOWER(c.priority) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
           "LOWER(c.department) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
           "LOWER(c.studentName) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
           "LOWER(c.email) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<Complaint> search(@Param("keyword") String keyword);

    long countByStatus(String status);
}
