package be.ucll.backend.easyfitness.model;

import jakarta.persistence.*;

@Entity
@Table(name = "EXERCISES")
public class Exercise {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;
    @Column(name = "SETS_AMOUNT")
    private int sets = 3;
    @Column(name = "REPS")
    private int reps = 8;
    @Column(name = "WEIGHT")
    private double weight;
    @Column(name = "NAME")
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
        if (reps < 1 || reps > 15) {
            throw new IllegalArgumentException("Het aantal herhalingen moet tussen 1 en 15 liggen, grenswaarden inbegrepen");
        }

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
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("De naam van een oefening moet ingevuld zijn");
        }

        this.name = name;
    }
}
