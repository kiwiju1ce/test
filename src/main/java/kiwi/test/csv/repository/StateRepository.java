package kiwi.test.csv.repository;

import kiwi.test.csv.entity.State;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StateRepository extends JpaRepository<State, Integer> {
}
