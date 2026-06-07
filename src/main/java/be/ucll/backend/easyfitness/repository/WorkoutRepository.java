package be.ucll.backend.easyfitness.repository;

import be.ucll.backend.easyfitness.model.Workout;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository

public interface WorkoutRepository extends JpaRepository<Workout, Long> {
}
