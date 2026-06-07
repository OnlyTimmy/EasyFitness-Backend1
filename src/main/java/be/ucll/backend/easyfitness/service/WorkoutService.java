package be.ucll.backend.easyfitness.service;

import be.ucll.backend.easyfitness.model.Exercise;
import be.ucll.backend.easyfitness.model.Status;
import be.ucll.backend.easyfitness.model.Workout;
import be.ucll.backend.easyfitness.repository.ExerciseRepository;
import be.ucll.backend.easyfitness.repository.WorkoutRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorkoutService {
    @Autowired
    private WorkoutRepository workoutRepository;
    @Autowired
    private ExerciseRepository exerciseRepository;

    public List<Workout> getWorkouts() {
        return workoutRepository.findAll();
    }

    public String addExerciseToWorkout(Long workoutID, Long exerciseID) {
        if (workoutRepository.findById(workoutID).isEmpty()) {
            throw new WorkoutServiceException("Workout with id " + workoutID + " does not exist");
        }

        if (exerciseRepository.findById(exerciseID).isEmpty()) {
            throw new WorkoutServiceException("Exercise with id " + exerciseID + " does not exist");
        }

        Workout workout = workoutRepository.findById(workoutID).get();
        Exercise exercise = exerciseRepository.findById(exerciseID).get();

        workout.addExercise(exercise);
        workoutRepository.save(workout);

        return "Exercise added to workout";
    }

    public String startWorkout(Long workoutID) {
        if (workoutRepository.findById(workoutID).isEmpty()) {
            throw new WorkoutServiceException("Workout with id " + workoutID + " does not exist");
        }

        Workout workout = workoutRepository.findById(workoutID).get();

        if (workout.getExercises().isEmpty()) {
            throw new WorkoutServiceException("Workout needs at least 1 exercise to start");
        }

        if (workout.getStatus() != Status.UNSTARTED) {
            throw new WorkoutServiceException("Workout already in progress or finished earlier");
        }

        workout.startWorkout();
        workoutRepository.save(workout);
        return "Workout started";
    }

    public String stopWorkout(Long workoutID) {
        if (workoutRepository.findById(workoutID).isEmpty()) {
            throw new WorkoutServiceException("Workout with id " + workoutID + " does not exist");
        }

        Workout workout = workoutRepository.findById(workoutID).get();

        if (workout.getStatus() != Status.BUSY) {
            throw new WorkoutServiceException("Workout not in progress");
        }

        workout.stopWorkout();
        workoutRepository.save(workout);
        return "Workout stopped";
    }
}
