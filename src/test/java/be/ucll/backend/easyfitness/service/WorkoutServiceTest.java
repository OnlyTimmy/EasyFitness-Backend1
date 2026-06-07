package be.ucll.backend.easyfitness.service;

import be.ucll.backend.easyfitness.model.Exercise;
import be.ucll.backend.easyfitness.model.Status;
import be.ucll.backend.easyfitness.model.Workout;
import be.ucll.backend.easyfitness.repository.ExerciseRepository;
import be.ucll.backend.easyfitness.repository.WorkoutRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class WorkoutServiceTest {

    @Mock
    private WorkoutRepository workoutRepository;

    @Mock
    private ExerciseRepository exerciseRepository;

    @InjectMocks
    private WorkoutService workoutService;

    private Workout testWorkout;
    private Exercise testExercise;

    @BeforeEach
    void setUp() {
        testWorkout = new Workout("Push day", 2);
        testExercise = new Exercise(3,12,30,"military press");
    }


    @Test
    void testGetWorkouts() {
        List<Workout> mockWorkouts = new ArrayList<>();
        mockWorkouts.add(testWorkout);
        when(workoutRepository.findAll()).thenReturn(mockWorkouts);

        List<Workout> result = workoutService.getWorkouts();

        assertEquals(1, result.size());
        assertEquals(testWorkout, result.getFirst());
        verify(workoutRepository, times(1)).findAll();
    }

    @Test
    void testAddExerciseToWorkoutSuccess() {
        when(workoutRepository.findById(1L)).thenReturn(Optional.of(testWorkout));
        when(exerciseRepository.findById(1L)).thenReturn(Optional.of(testExercise));

        String result = workoutService.addExerciseToWorkout(1L, 1L);

        assertEquals("Exercise added to workout", result);
        assertTrue(testWorkout.getExercises().contains(testExercise));
        verify(workoutRepository, times(1)).save(testWorkout);
        verify(exerciseRepository, atLeastOnce()).findById(1L);
    }

    @Test
    void testAddExerciseToWorkoutWorkoutNotFound() {
        when(workoutRepository.findById(7L)).thenReturn(Optional.empty());

        WorkoutServiceException exception = assertThrows(
                WorkoutServiceException.class,
                () -> workoutService.addExerciseToWorkout(7L, 1L)
        );

        assertEquals("Workout with id 7 does not exist", exception.getMessage());
        verify(exerciseRepository, never()).findById(anyLong());
        verify(workoutRepository, never()).save(any());
    }

    @Test
    void testAddExerciseToWorkoutExerciseNotFound() {
        when(workoutRepository.findById(1L)).thenReturn(Optional.of(testWorkout));
        when(exerciseRepository.findById(7L)).thenReturn(Optional.empty());

        WorkoutServiceException exception = assertThrows(
                WorkoutServiceException.class,
                () -> workoutService.addExerciseToWorkout(1L, 7L)
        );

        assertEquals("Exercise with id 7 does not exist", exception.getMessage());
        verify(workoutRepository, never()).save(any());
    }

    @Test
    void testStartWorkoutSuccess() {
        testWorkout.addExercise(testExercise);
        when(workoutRepository.findById(1L)).thenReturn(Optional.of(testWorkout));

        String result = workoutService.startWorkout(1L);

        assertEquals("Workout started", result);
        assertEquals(Status.BUSY, testWorkout.getStatus());
        verify(workoutRepository, times(1)).save(testWorkout);
    }

    @Test
    void testStartWorkoutNotFound() {
        when(workoutRepository.findById(7L)).thenReturn(Optional.empty());

        WorkoutServiceException exception = assertThrows(
                WorkoutServiceException.class,
                () -> workoutService.startWorkout(7L)
        );

        assertEquals("Workout with id 7 does not exist", exception.getMessage());
        verify(workoutRepository, never()).save(any());
    }

    @Test
    void testStartWorkoutNoExercises() {
        when(workoutRepository.findById(1L)).thenReturn(Optional.of(testWorkout));

        WorkoutServiceException exception = assertThrows(
                WorkoutServiceException.class,
                () -> workoutService.startWorkout(1L)
        );

        assertEquals("Workout needs at least 1 exercise to start", exception.getMessage());
        verify(workoutRepository, never()).save(any());
    }

    @Test
    void testStartWorkoutAlreadyBusy() {
        testWorkout.addExercise(testExercise);
        testWorkout.startWorkout();
        when(workoutRepository.findById(1L)).thenReturn(Optional.of(testWorkout));

        WorkoutServiceException exception = assertThrows(
                WorkoutServiceException.class,
                () -> workoutService.startWorkout(1L)
        );

        assertEquals("Workout already in progress or finished earlier", exception.getMessage());
        verify(workoutRepository, never()).save(any());
    }

    @Test
    void testStartWorkoutAlreadyFinished() {
        testWorkout.addExercise(testExercise);
        testWorkout.startWorkout();
        testWorkout.stopWorkout();
        when(workoutRepository.findById(1L)).thenReturn(Optional.of(testWorkout));

        // Act & Assert
        WorkoutServiceException exception = assertThrows(
                WorkoutServiceException.class,
                () -> workoutService.startWorkout(1L)
        );

        assertEquals("Workout already in progress or finished earlier", exception.getMessage());
        verify(workoutRepository, never()).save(any());
    }


    @Test
    void testStopWorkoutSuccess() {
        testWorkout.addExercise(testExercise);
        testWorkout.startWorkout();
        when(workoutRepository.findById(1L)).thenReturn(Optional.of(testWorkout));

        String result = workoutService.stopWorkout(1L);

        // Assert
        assertEquals("Workout stopped", result);
        assertEquals(Status.FINISHED, testWorkout.getStatus());
        verify(workoutRepository, times(1)).save(testWorkout);
    }

    @Test
    void testStopWorkoutNotFound() {
        when(workoutRepository.findById(7L)).thenReturn(Optional.empty());

        WorkoutServiceException exception = assertThrows(
                WorkoutServiceException.class,
                () -> workoutService.stopWorkout(7L)
        );

        assertEquals("Workout with id 7 does not exist", exception.getMessage());
        verify(workoutRepository, never()).save(any());
    }

    @Test
    void testStopWorkoutNotInProgress() {
        when(workoutRepository.findById(1L)).thenReturn(Optional.of(testWorkout));

        WorkoutServiceException exception = assertThrows(
                WorkoutServiceException.class,
                () -> workoutService.stopWorkout(1L)
        );

        assertEquals("Workout not in progress", exception.getMessage());
        verify(workoutRepository, never()).save(any());
    }

    @Test
    void testStopWorkoutAlreadyFinished() {
        testWorkout.addExercise(testExercise);
        testWorkout.startWorkout();
        testWorkout.stopWorkout();
        when(workoutRepository.findById(1L)).thenReturn(Optional.of(testWorkout));

        WorkoutServiceException exception = assertThrows(
                WorkoutServiceException.class,
                () -> workoutService.stopWorkout(1L)
        );

        assertEquals("Workout not in progress", exception.getMessage());
        verify(workoutRepository, never()).save(any());
    }
}


