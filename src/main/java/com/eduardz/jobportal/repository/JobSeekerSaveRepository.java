package com.eduardz.jobportal.repository;

import com.eduardz.jobportal.entity.JobPostActivity;
import com.eduardz.jobportal.entity.JobSeekerProfile;
import com.eduardz.jobportal.entity.JobSeekerSave;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobSeekerSaveRepository extends JpaRepository<JobSeekerSave, Integer> {

    public List<JobSeekerSave> findByUserId(JobSeekerProfile userAccountId);

    public List<JobSeekerSave> findByJob(JobPostActivity job);

}
