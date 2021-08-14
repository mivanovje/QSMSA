package mgivanov.qsmsa.repository;

import mgivanov.qsmsa.entity.Greeting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GreetingRepository extends CrudRepository<Greeting, Long> {
}
