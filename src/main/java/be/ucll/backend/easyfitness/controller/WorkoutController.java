package be.ucll.backend.easyfitness.controller;

import be.ucll.backend.easyfitness.model.Workout;
import be.ucll.backend.easyfitness.service.WorkoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/v1/workout")
public class WorkoutController {
    @Autowired
    private WorkoutService workoutService;

    @GetMapping
    public List<Workout> getWorkouts() {
        return workoutService.getWorkouts();
    }

    @PostMapping(value = "/{workoutID}", produces = "text/plain")
    public String addExerciseToWorkout(@PathVariable Long workoutID, @RequestParam Long exerciseID) {
        return workoutService.addExerciseToWorkout(workoutID, exerciseID);
    }

    @PutMapping(value = "/start/{workoutID}", produces = "text/plain")
    public String startWorkout(@PathVariable Long workoutID) {
        return workoutService.startWorkout(workoutID);
    }

    @PutMapping(value = "/stop/{workoutID}", produces = "text/plain")
    public String stopWorkout(@PathVariable Long workoutID) {
        return workoutService.stopWorkout(workoutID);
    }
}
