package backend.service.impl;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Service;
import backend.model.Role;
import backend.repository.RoleRepository;
import backend.service.RoleService;

import java.util.List;
import java.util.Optional;

@Service
@ComponentScan(basePackages = {"backend"})
@EnableJpaRepositories(basePackages = { "backend.repository" })
@EntityScan(basePackages = { "backend.model" })
public class RoleServiceImpl implements RoleService {
    RoleRepository roleRepository;
    RoleServiceImpl(RoleRepository roleRepository){
        this.roleRepository = roleRepository;
    }
    @Override
    public Role deleteById(Integer id) {
        Optional<Role> optionalRole = roleRepository.findById(id);
        if (optionalRole.isPresent()){
            Role role = optionalRole.get();
            roleRepository.deleteById(id);
            return role;
        }
        return null;
    }

    @Override
    public List<Role> deleteAll() {
        List<Role> roleList = roleRepository.findAll();
        if (!(roleList.isEmpty()))
        {
            roleRepository.deleteAll();
            return roleList;
        }
        return null;
    }

    @Override
    public Role findById(Integer id) {
        Optional<Role> optionalRole = roleRepository.findById(id);
        if (optionalRole.isPresent()){
            Role role = optionalRole.get();
            return role;
        }
        return null;
    }
}
