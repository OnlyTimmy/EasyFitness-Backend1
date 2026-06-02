package be.ucll.backend.easyfitness.controller;

import be.ucll.backend.easyfitness.model.Exercise;
import be.ucll.backend.easyfitness.service.ExerciseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/exercise")
public class ExerciseController {

    @Autowired
    private ExerciseService exerciseService;

    @GetMapping
    public List<Exercise> getExercises() {
        return exerciseService.getExercises();
    }

    @GetMapping("/{reps}")
    public List<Exercise> getExercisesWithMinimumReps(@PathVariable int reps) {
        return exerciseService.getExercisesWithMinimumReps(reps);
    }

}
