package com.eduardz.jobportal.repository;

import com.eduardz.jobportal.entity.JobPostActivity;
import com.eduardz.jobportal.entity.JobSeekerApply;
import com.eduardz.jobportal.entity.JobSeekerProfile;
import org.springframework.boot.autoconfigure.batch.BatchProperties;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobSeekerApplyRepository extends JpaRepository<JobSeekerApply,Integer> {

    List<JobSeekerApply> findByUserId(JobSeekerProfile userId);

    List<JobSeekerApply> findByJob(JobPostActivity job);

}
