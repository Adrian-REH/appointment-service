package app.appointment.service.auth.domain.port;


import app.appointment.service.auth.infrastructure.adapter.driver.entity.Role;

public interface RoleService {
    Role findByName(String name);
}
