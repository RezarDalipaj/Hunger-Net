package backend.service;

import backend.model.dto.AdminDto;

public interface AdminService {
    AdminDto save(AdminDto adminDto) throws Exception;
}
