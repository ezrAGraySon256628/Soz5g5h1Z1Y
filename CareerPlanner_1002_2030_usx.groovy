// 代码生成时间: 2025-10-02 20:30:50
package com.example

import grails.transaction.Transactional

/**
 * CareerPlanner service class
 * Handles career planning functionalities
 */
@Transactional
class CareerPlannerService {

    /**
     * Adds a new career goal to the database
     *
     * @param careerGoal The career goal object to be added
     * @return The saved career goal object
     */
    def addCareerGoal(CareerGoal careerGoal) {
        try {
            careerGoal.validate()
            if (!careerGoal.hasErrors()) {
                careerGoal.save(flush: true)
                return careerGoal
            } else {
                throw new RuntimeException("Validation errors: ${careerGoal.errors}")
            }
        } catch (Exception e) {
            log.error("Failed to add career goal", e)
            throw new RuntimeException("Failed to add career goal: ${e.message}")
        }
    }

    /**
     * Updates an existing career goal in the database
     *
     * @param careerGoalId The ID of the career goal to be updated
     * @param updatedFields A map containing the fields to be updated and their new values
     * @return The updated career goal object
     */
    def updateCareerGoal(Long careerGoalId, Map updatedFields) {
        try {
            def careerGoal = CareerGoal.get(careerGoalId)
            if (!careerGoal) {
                throw new RuntimeException("Career goal with ID $careerGoalId not found")
            }
            updatedFields.each { field, value ->
                careerGoal."$field" = value
            }
            careerGoal.validate()
            if (!careerGoal.hasErrors()) {
                careerGoal.save(flush: true)
                return careerGoal
            } else {
                throw new RuntimeException("Validation errors: ${careerGoal.errors}")
            }
        } catch (Exception e) {
            log.error("Failed to update career goal", e)
            throw new RuntimeException("Failed to update career goal: ${e.message}")
        }
    }

    /**
     * Deletes a career goal from the database
     *
     * @param careerGoalId The ID of the career goal to be deleted
     * @return A success message
     */
    def deleteCareerGoal(Long careerGoalId) {
        try {
            def careerGoal = CareerGoal.get(careerGoalId)
            if (!careerGoal) {
                throw new RuntimeException("Career goal with ID $careerGoalId not found")
            }
            careerGoal.delete(flush: true)
            return "Career goal deleted successfully"
        } catch (Exception e) {
            log.error("Failed to delete career goal", e)
            throw new RuntimeException("Failed to delete career goal: ${e.message}")
        }
    }
}

/**
 * Domain class representing a career goal
 */
class CareerGoal {
    String title
    String description
    Date targetDate
    Boolean achieved = false

    static constraints = {
        title(blank: false, nullable: false)
        description(blank: false, nullable: false)
        targetDate(nullable: true)
    }
}
