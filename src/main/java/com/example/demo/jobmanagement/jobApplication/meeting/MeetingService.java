package com.example.demo.jobmanagement.jobApplication.meeting;

import com.example.demo.jobmanagement.companymanagement.Company;
import com.example.demo.jobmanagement.companymanagement.CompanyRepository;
import com.example.demo.jobmanagement.companymanagement.CompanyRequest;
import com.example.demo.jobmanagement.job.Job;
import com.example.demo.jobmanagement.job.JobRepository;
import com.example.demo.jobmanagement.jobApplication.JobApplication;
import com.example.demo.jobmanagement.jobApplication.JobApplicationRepository;
import com.example.demo.jobmanagement.jobApplication.JobApplicationRequest;
import com.example.demo.usermanagement.models.User;
import com.example.demo.usermanagement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class MeetingService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    MeetingRepository meetingRepository;

    @Autowired
    CompanyRepository companyRepository;

    @Autowired
    JobRepository jobRepository;

    @Autowired
    JobApplicationRepository jobApplicationRepository;
    public MeetingDTO createMeeting(MeetingRequest meetingRequest) {
        User user = userRepository.findById(meetingRequest.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found with the id : " + meetingRequest.getUserId()));
        JobApplication jobApplication = jobApplicationRepository.findById(meetingRequest.getJobApplicationId())
                .orElseThrow(() -> new RuntimeException("Job Application not found with the id : " + meetingRequest.getJobApplicationId()));

        Company company = jobApplication.getJob().getCompany();
        // Fixed this
        if(jobApplication.getUser().getId() != company.getUser().getId()) {
            throw new RuntimeException("User is not authorized to create a meeting for this job application");
        }
        List<Meeting> meetings = meetingRepository.findByUserId(user.getId());
        boolean flag = false ;
        LocalDateTime startTime = meetingRequest.getStartTime();
        LocalDateTime endTime = meetingRequest.getEndTime();
        for(Meeting meeting : meetings) {
            LocalDateTime meetingStartTime = meeting.getStartTime();
            LocalDateTime meetingEndTime = meeting.getEndTime();
           if( (meetingStartTime.isBefore(startTime) || meetingStartTime.isEqual(startTime) ) &&
                   (meetingEndTime.isAfter(startTime) || meetingEndTime.isEqual(startTime) || meetingEndTime.isAfter(endTime) || meetingEndTime.isEqual(endTime)) ) {
               flag = true;
               break ;
           }
           else if ( (meetingStartTime.isAfter(startTime) || meetingStartTime.isEqual(startTime) ) &&
                   (meetingStartTime.isBefore(endTime) || meetingEndTime.isAfter(endTime) || meetingEndTime.isEqual(endTime)) ) {
               flag = true ;
               break ;
           }
        }
        if(flag) {
            Map<String, Object> response = new HashMap<>();
            response.put("status", "error");
            response.put("message", "User already has a meeting scheduled at this time");

//            return ResponseEntity
//                    .status(HttpStatus.CONFLICT) // 409 Conflict
//                    .body(response);

            throw new RuntimeException("User already has a meeting scheduled at this time");
        }
        Meeting meeting = new Meeting(meetingRequest, user, jobApplication);
        meetingRepository.save(meeting);
        return new MeetingDTO(meeting);
    }

    public List<MeetingDTO> getAllMeetings() {
        List<Meeting> meetings = meetingRepository.findAll();
        return meetings.stream().map(MeetingDTO::new).toList();
    }

    public MeetingDTO getMeetingById(UUID meetingId) {
        Meeting meeting = meetingRepository.findById(meetingId)
                .orElseThrow(() -> new RuntimeException("Meeting not found with the id : " + meetingId));
        return new MeetingDTO(meeting);
    }

    public List<MeetingDTO> getMeetingsByCompanyId(UUID companyId) {
        List<Job> jobs = jobRepository.findByCompanyId(companyId);

        List<Meeting> meetings = new ArrayList<>();

        for( Job job : jobs) {
            List<JobApplication> jobApplications = jobApplicationRepository.findByJobId(job.getId());
            for(JobApplication jobApplication : jobApplications) {
                meetings.addAll(meetingRepository.findByJobApplicationId(jobApplication.getId()));

            }
        }

        return meetings.stream().map(MeetingDTO::new).toList();
    }

    public List<MeetingDTO> getMeetingsByUserId(UUID userId) {
        List<Meeting> meetings = meetingRepository.findByUserId(userId);
        return meetings.stream().map(MeetingDTO::new).toList();
    }

    public List<MeetingDTO> getMeetingsByJobId(UUID jobId) {
        List<JobApplication> jobApplications = jobApplicationRepository.findByJobId(jobId);
        List<Meeting> meetings = new ArrayList<>();
        for(JobApplication jobApplication : jobApplications) {
            meetings.addAll(meetingRepository.findByJobApplicationId(jobApplication.getId()));
        }
        return meetings.stream().map(MeetingDTO::new).toList();
    }

    public void deleteMeeting(UUID meetingId) {
        Meeting meeting = meetingRepository.findById(meetingId)
                .orElseThrow(() -> new RuntimeException("Meeting not found with the id : " + meetingId));
        meetingRepository.delete(meeting);
    }

    public MeetingDTO updateMeeting(UUID meetingId, MeetingRequest meetingRequest) {
        Meeting current_meeting = meetingRepository.findById(meetingId)
                .orElseThrow(() -> new RuntimeException("Meeting not found with the id : " + meetingId));

        boolean flag = false ;
        LocalDateTime startTime = meetingRequest.getStartTime();
        LocalDateTime endTime = meetingRequest.getEndTime();
        List<Meeting> meetings = meetingRepository.findByUserId(meetingRequest.getUserId());

        for(Meeting meeting : meetings) {
            LocalDateTime meetingStartTime = meeting.getStartTime();
            LocalDateTime meetingEndTime = meeting.getEndTime();
            if( (meetingStartTime.isBefore(startTime) || meetingStartTime.isEqual(startTime) ) &&
                    (meetingEndTime.isAfter(startTime) || meetingEndTime.isEqual(startTime) || meetingEndTime.isAfter(endTime) || meetingEndTime.isEqual(endTime)) ) {
                flag = true;
                break ;
            }
            else if ( (meetingStartTime.isAfter(startTime) || meetingStartTime.isEqual(startTime) ) &&
                    (meetingStartTime.isBefore(endTime) || meetingEndTime.isAfter(endTime) || meetingEndTime.isEqual(endTime)) ) {
                flag = true ;
                break ;
            }
        }
        if(flag) {
            Map<String, Object> response = new HashMap<>();
            response.put("status", "error");
            response.put("message", "User already has a meeting scheduled at this time");

//            return ResponseEntity
//                    .status(HttpStatus.CONFLICT) // 409 Conflict
//                    .body(response);

            throw new RuntimeException("User already has a meeting scheduled at this time");
        }

        current_meeting.setStartTime(meetingRequest.getStartTime());
        current_meeting.setEndTime(meetingRequest.getEndTime());
        meetingRepository.save(current_meeting);
        return new MeetingDTO(current_meeting);
    }
}
