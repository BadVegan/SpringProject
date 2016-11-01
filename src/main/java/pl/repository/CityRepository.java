package pl.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.model.City;

/**
 * Created by davit on 30.10.2016.
 */
public interface CityRepository extends JpaRepository<City, Long> {
}
