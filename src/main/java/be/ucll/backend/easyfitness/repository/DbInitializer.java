package be.ucll.backend.easyfitness.repository;

import be.ucll.backend.easyfitness.model.Exercise;
import be.ucll.backend.easyfitness.model.Workout;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DbInitializer {

    private ExerciseRepository exerciseRepository;
    private WorkoutRepository workoutRepository;

    @Autowired
    public DbInitializer(ExerciseRepository exerciseRepository, WorkoutRepository workoutRepository) {
        this.exerciseRepository = exerciseRepository;
        this.workoutRepository = workoutRepository;
    }

    @PostConstruct
    public void initialize() {
        // DO NOT TOUCH FOLLOWING THREE EXERCISES
        exerciseRepository.save(new Exercise(3,8,15.5,"ez-bar bicep curls"));
        exerciseRepository.save(new Exercise(3,12,30,"military press"));
        exerciseRepository.save(new Exercise(1,8,67.8,"leg press"));

        workoutRepository.save(new Workout("Push day", 2));
    }
}
