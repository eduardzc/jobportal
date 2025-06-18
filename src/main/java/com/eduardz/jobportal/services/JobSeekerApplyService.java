package com.eduardz.jobportal.services;

import com.eduardz.jobportal.entity.JobPostActivity;
import com.eduardz.jobportal.entity.JobSeekerApply;
import com.eduardz.jobportal.entity.JobSeekerProfile;
import com.eduardz.jobportal.repository.JobSeekerApplyRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobSeekerApplyService {

    private JobSeekerApplyRepository jobSeekerApplyRepository;

    public JobSeekerApplyService(JobSeekerApplyRepository jobSeekerApplyRepository) {
        this.jobSeekerApplyRepository = jobSeekerApplyRepository;
    }

    public List<JobSeekerApply> getCandidatesJobs(JobSeekerProfile userAccountId){
        return jobSeekerApplyRepository.findByUserId(userAccountId);
    }

    public List<JobSeekerApply> getJobCandidates(JobPostActivity job){
        return jobSeekerApplyRepository.findByJob(job);
    }

    public void addNew(JobSeekerApply jobSeekerApply) {

        jobSeekerApplyRepository.save(jobSeekerApply);

    }
}
