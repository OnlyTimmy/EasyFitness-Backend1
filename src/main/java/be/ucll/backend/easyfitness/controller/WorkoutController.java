package be.ucll.backend.easyfitness.controller;

import be.ucll.backend.easyfitness.model.Workout;
import be.ucll.backend.easyfitness.service.WorkoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
