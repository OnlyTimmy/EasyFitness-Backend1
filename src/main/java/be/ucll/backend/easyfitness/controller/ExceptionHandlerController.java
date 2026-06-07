package be.ucll.backend.easyfitness.controller;

import be.ucll.backend.easyfitness.service.ExerciseServiceException;
import be.ucll.backend.easyfitness.service.WorkoutServiceException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

@RestControllerAdvice
public class ExceptionHandlerController {
    @ExceptionHandler(ExerciseServiceException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> handleExerciseServiceException(ExerciseServiceException exception) {
        return Map.of("Exercise Service", exception.getMessage());
    }

    @ExceptionHandler(WorkoutServiceException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> handleWorkoutServiceException(WorkoutServiceException exception) {
        return Map.of("Workout Service", exception.getMessage());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> handleIllegalArgumentException(IllegalArgumentException exception) {
        return Map.of("Exercise Model", exception.getMessage());
    }
}
