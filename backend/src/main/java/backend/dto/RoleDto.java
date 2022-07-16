package backend.dto;

import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class RoleDto {
    private String role;
    private List<String> roles;

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }
}
