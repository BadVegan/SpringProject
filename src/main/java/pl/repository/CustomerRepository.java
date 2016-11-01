package pl.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.model.Customer;

/**
 * Created by davit on 30.10.2016.
 */
public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
