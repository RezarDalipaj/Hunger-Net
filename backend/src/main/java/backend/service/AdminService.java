package backend.service;

import backend.dto.AdminDto;

public interface AdminService {
    AdminDto save(AdminDto adminDto) throws Exception;
}
