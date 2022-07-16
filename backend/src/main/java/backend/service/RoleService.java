package backend.service;

import backend.model.Role;
import org.springframework.context.annotation.ComponentScan;

import java.util.List;
@ComponentScan(basePackages = {"backend"})
public interface RoleService {
    Role deleteById(Integer id);
    List<Role> deleteAll();
    Role findById(Integer id);
}
