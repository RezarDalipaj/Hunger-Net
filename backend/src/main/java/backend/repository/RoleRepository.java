package backend.repository;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import backend.model.Role;
@Repository
@ComponentScan(basePackages = {"backend"})
public interface RoleRepository extends JpaRepository<Role, Integer> {
    Role findRoleByRole(String role);
    void deleteById(Integer id);
    @Override
    void deleteAll();
}
