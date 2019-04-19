package com.voitovich.NVisionTask;


import com.voitovich.NVisionTask.data.Job;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
public class MainController {

    @Autowired
    private JobService jobService;

    @PostMapping("/jobs")
    @ResponseBody
    public Map<String, Long> setJobs(@RequestBody ArrayList<Job> jobs) {
        return jobService.saveJobs(jobs);
    }

    @GetMapping("/statistics")
    @ResponseBody
    public List<Job> stats(@RequestParam(required = false) String user,
                           @RequestParam(required = false) String device,
                           @RequestParam(required = false) String type,
                           @RequestParam(required = false) Date timeFrom,
                           @RequestParam(required = false) Date timeTo) {
        return jobService.search(user, device, type, timeFrom, timeTo);
    }
}
