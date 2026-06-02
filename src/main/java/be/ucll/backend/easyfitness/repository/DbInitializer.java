package be.ucll.backend.easyfitness.repository;

import be.ucll.backend.easyfitness.model.Exercise;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DbInitializer {

    private ExerciseRepository exerciseRepository;

    @Autowired
    public DbInitializer(ExerciseRepository exerciseRepository) {
        this.exerciseRepository = exerciseRepository;
    }

    @PostConstruct
    public void initialize() {
        // DO NOT TOUCH FOLLOWING THREE EXERCISES
        exerciseRepository.save(new Exercise(3,8,15.5,"ez-bar bicep curls"));
        exerciseRepository.save(new Exercise(3,12,30,"military press"));
        exerciseRepository.save(new Exercise(1,8,67.8,"leg press"));
    }
}
