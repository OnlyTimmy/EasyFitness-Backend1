package be.ucll.backend.easyfitness.service;

import be.ucll.backend.easyfitness.model.Workout;
import be.ucll.backend.easyfitness.repository.WorkoutRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorkoutService {
    @Autowired
    private WorkoutRepository workoutRepository;

    public List<Workout> getWorkouts() {
        return workoutRepository.findAll();
    }
}
