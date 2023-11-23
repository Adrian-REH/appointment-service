package app.appointment.service.auth.infrastructure.adapter;

import app.appointment.service.auth.domain.port.RoleService;
import app.appointment.service.auth.infrastructure.adapter.driver.RoleRepository;
import app.appointment.service.auth.infrastructure.adapter.driver.entity.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value = "roleService")
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public Role findByName(String id) {
        return roleRepository.findRoleById(id);
    }
}
