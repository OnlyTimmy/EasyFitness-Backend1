package be.ucll.backend.easyfitness.model;

import jakarta.persistence.*;

@Entity
public class Exercise {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int sets;
    private int reps;
    private double weight;
    private String name;

    protected Exercise() {
    }

    public Exercise(int sets, int reps, double weight, String name) {
        setSets(sets);
        setReps(reps);
        setWeight(weight);
        setName(name);
    }

    public Exercise(double weight, String name) {
        this(3,8, weight, name);
    }

    public Long getId() {
        return id;
    }

    public int getSets() {
        return sets;
    }

    public void setSets(int sets) {
        this.sets = sets;
    }

    public int getReps() {
        return reps;
    }

    public void setReps(int reps) {
        this.reps = reps;
    }

    public double getWeight() {
        return weight;
    }

    private void setWeight(double weight) {
        this.weight = weight;
    }

    public String getName() {
        return name;
    }

    private void setName(String name) {
        this.name = name;
    }
}
