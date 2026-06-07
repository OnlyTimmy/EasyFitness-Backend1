package be.ucll.backend.easyfitness.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "WORKOUTS")
public class Workout {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;
    @Column(name = "NAME")
    private String name;
    @Column(name = "DIFFICULTY")
    private int difficulty;
    @Enumerated(EnumType.STRING)
    @Column(name = "STATUS")
    private Status status;
    @Column(name = "CREATION_DATE")
    private LocalDate creationDate;
    @ManyToMany
    @JoinTable(
            name = "WORKOUT_EXERCISES",
            joinColumns = @JoinColumn(name = "WORKOUT_ID"),
            inverseJoinColumns = @JoinColumn(name = "EXERCISE_ID")
    )
    private List<Exercise> exercises = new ArrayList<>();

    protected Workout() {
    }

    public Workout(String name, int difficulty) {
        setName(name);
        setDifficulty(difficulty);
        setStatus(Status.UNSTARTED);
        setCreationDate(LocalDate.now());
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    private void setName(String name) {
        this.name = name;
    }

    public int getDifficulty() {
        return difficulty;
    }

    private void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }

    public Status getStatus() {
        return status;
    }

    private void setStatus(Status status) {
        this.status = status;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    private void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public List<Exercise> getExercises() {
        return exercises;
    }

    public void addExercise(Exercise exercise) {
        this.exercises.add(exercise);
    }

    public void startWorkout() {
        setStatus(Status.BUSY);
    }
}