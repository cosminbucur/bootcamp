```java
package com.sda.project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class JobController {

    @GetMapping("/jobs/add")
    public String showAddForm() {
        return "job/job-add";
    }

    @GetMapping("/jobs")
    public String showJobsPage() {
        return "job/jobs";
    }

    @GetMapping("/jobs/{id}")
    public String showJobInfoPage(@PathVariable Long id) {
        return "job/job-info";
    }

    @GetMapping("/jobs/{id}/edit")
    public String showEditForm(@PathVariable Long id) {
        return "job/job-edit";
    }

    @PostMapping("/jobs/{id}/edit")
    public String edit(@PathVariable Long id) {
        return "redirect:/jobs";
    }

    @GetMapping("/jobs/{id}/delete")
    public String delete(@PathVariable Long id) {
        return "redirect:/jobs";
    }
}
```