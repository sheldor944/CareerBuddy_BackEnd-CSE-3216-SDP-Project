package com.example.demo.jobmanagement.jobApplication.meeting;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/meeting")
public class MeetingController {

    @Autowired
    MeetingService meetingService;

    @GetMapping("/all")
    public List<MeetingDTO> getAllMeetings() {
        return meetingService.getAllMeetings();
    }

    @GetMapping("/meeting/{meetingId}")
    public MeetingDTO getMeetingById(@PathVariable UUID meetingId) {
        return meetingService.getMeetingById(meetingId);
    }

    @GetMapping("/company/{companyId}")
    public List<MeetingDTO> getMeetingsByCompanyId(@PathVariable UUID companyId) {
        return meetingService.getMeetingsByCompanyId(companyId);
    }

    @GetMapping("/user/{userId}")
    public List<MeetingDTO> getMeetingsByUserId(@PathVariable UUID userId) {
        return meetingService.getMeetingsByUserId(userId);
    }

    @GetMapping("/job/{jobId}")
    public List<MeetingDTO> getMeetingsByJobId(@PathVariable UUID jobId) {
        return meetingService.getMeetingsByJobId(jobId);
    }

    @PostMapping("/create")
    public MeetingDTO createMeeting(@RequestBody MeetingRequest meetingRequest) {
        return meetingService.createMeeting(meetingRequest);
    }

    @DeleteMapping("/delete/{meetingId}")
    public void deleteMeeting(@PathVariable UUID meetingId) {
        meetingService.deleteMeeting(meetingId);
    }

    @PutMapping("/update/{meetingId}")
    public MeetingDTO updateMeeting(@PathVariable UUID meetingId, @RequestBody MeetingRequest meetingRequest) {
        return meetingService.updateMeeting(meetingId, meetingRequest);
    }



}
