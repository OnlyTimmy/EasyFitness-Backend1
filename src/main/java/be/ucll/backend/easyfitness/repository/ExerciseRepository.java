package be.ucll.backend.easyfitness.repository;

import be.ucll.backend.easyfitness.model.Exercise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExerciseRepository extends JpaRepository<Exercise, Long> {
    List<Exercise> findByRepsGreaterThanEqual(int reps);
    List<Exercise> findByNameContainingIgnoreCaseAndSetsLessThan(String name, int sets);
}
