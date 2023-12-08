package app.appointment.service.files.application;

import app.appointment.service.files.domain.model.FileResponse;
import app.appointment.service.files.domain.port.FileRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class GetAllFile {
    private final FileRepository fileRepository;
    public List<FileResponse> execute(String username, String role) {
        List<FileResponse>  response = new ArrayList<FileResponse>();

        if(role.equals("MEDICAL")) {
            response.addAll(fileRepository.findByUsernameMedical(username));
        } else if (role.equals("PATIENT")) {
            response.addAll(fileRepository.findByUsernamePatient(username));
        }
        return response;
    }
}
