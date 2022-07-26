package backend.repository;

import backend.model.Status;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@ComponentScan(basePackages = {"backend"})
public interface StatusRepository extends JpaRepository<Status, Integer> {
    Status findStatusByStatus(String status);
}
