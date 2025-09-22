// 代码生成时间: 2025-09-22 20:47:56
package com.example.scheduler

import grails.async.Promises
import groovy.util.logging.Slf4j
import org.grails.scheduling.Scheduled
import org.quartz.Job
import org.quartz.JobBuilder
import org.quartz.JobDetail
import org.quartz.TriggerBuilder
import org.quartz.Scheduler
import org.quartz.SchedulerException
import org.quartz.impl.StdSchedulerFactory

/**
 * ScheduledTaskService class that handles scheduling and execution of tasks.
 */
@Slf4j
@Scheduled
class ScheduledTaskService {

    // Reference to the Quartz scheduler
    private Scheduler scheduler

    /**
     * Initializes the scheduler and starts it.
     */
    void init() {
        try {
            // Get a scheduler instance from the factory
            scheduler = new StdSchedulerFactory().getScheduler()
            // Start the scheduler
            scheduler.start()
            log.info 'Scheduler started.'
        } catch (SchedulerException e) {
            log.error 'Failed to start the scheduler.', e
        }
    }

    /**
     * Schedules a task with the given closure, jobName, and trigger.
     *
     * @param task The closure representing the task to execute.
     * @param jobName The name of the job.
     * @param trigger The trigger for scheduling the job.
     */
    void scheduleTask(Closure task, String jobName, Trigger trigger) {
        try {
            // Create a job detail for the task
            JobDetail jobDetail = JobBuilder.newJob(TaskJob.class)
                    .withIdentity(jobName)
                    .build()
            // Schedule the job with the given trigger
            scheduler.scheduleJob(jobDetail, trigger)
            log.info "Task scheduled: jobName = ${jobName}, trigger = ${trigger}"
        } catch (SchedulerException e) {
            log.error "Failed to schedule task: jobName = ${jobName}", e
        }
    }

    /**
     * Stops the scheduler and all running jobs.
     */
    void stop() {
        if (scheduler) {
            try {
                // Pause all jobs before stopping the scheduler
                scheduler.pauseAll()
                // Stop the scheduler
                scheduler.shutdown(true)
                log.info 'Scheduler stopped.'
            } catch (SchedulerException e) {
                log.error 'Failed to stop the scheduler.', e
            }
        }
    }

    /**
     * Quartz Job class that executes the given task closure.
     */
    static class TaskJob implements Job {
        @Override
        void execute(JobExecutionContext context) {
            // Retrieve the task closure from the job data map
            Closure task = context.jobDetail.jobDataMap.task as Closure
            try {
                // Execute the task closure
                task()
            } catch (Exception e) {
                log.error 'Task execution failed.', e
            }
        }
    }
}

// Usage example for scheduling a task
// Define a task closure
Closure myTask = { log.info 'Task executed.' }

// Create a trigger for the task
Trigger trigger = TriggerBuilder.newTrigger()
        .withSchedule(SimpleScheduleBuilder.simpleSchedule()
                .withIntervalInSeconds(40)
                .repeatForever())
        .build()

// Initialize the scheduler service and schedule the task
ScheduledTaskService schedulerService = new ScheduledTaskService()
schedulerService.init()
schedulerService.scheduleTask(myTask, 'MyScheduledTask', trigger)
