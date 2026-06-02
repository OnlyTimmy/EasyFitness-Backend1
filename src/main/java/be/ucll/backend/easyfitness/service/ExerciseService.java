package be.ucll.backend.easyfitness.service;

import be.ucll.backend.easyfitness.model.Exercise;
import be.ucll.backend.easyfitness.repository.ExerciseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExerciseService {

    @Autowired
    private ExerciseRepository exerciseRepository;

    public List<Exercise> getExercises() {
        return exerciseRepository.findAll();
    }

    public List<Exercise> getExercisesWithMinimumReps(int reps) {
        return exerciseRepository.findByRepsGreaterThanEqual(reps);
    }

}
