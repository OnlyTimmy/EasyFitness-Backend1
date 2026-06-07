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

    public List<Exercise> getExercisesByNameAndSet(String name, int sets) {
        return exerciseRepository.findByNameContainingIgnoreCaseAndSetsLessThan(name, sets);
    }

    public List<Exercise> getExercisesWithMinimumReps(int reps) {
        return exerciseRepository.findByRepsGreaterThanEqual(reps);
    }

    public Exercise addExercise(Exercise exercise) {
        return exerciseRepository.save(exercise);
    }

    public String deleteExercise(Long id) {
        if (exerciseRepository.findById(id).isEmpty()) {
            throw new ExerciseServiceException("Exercise with id " + id + " does not exist");
        }

        Exercise exercise = exerciseRepository.findById(id).get();
        exerciseRepository.delete(exercise);
        return "Exercise deleted";
    }

}
