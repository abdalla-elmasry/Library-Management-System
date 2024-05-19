package backend.developer.librarymanagementsystem.mapper;

import backend.developer.librarymanagementsystem.model.dto.request.PatronRequestDTO;
import backend.developer.librarymanagementsystem.model.dto.response.PatronResponseDTO;
import backend.developer.librarymanagementsystem.model.entity.Patron;
import org.springframework.stereotype.Component;

@Component
public class PatronMapper {
    public Patron mapToEntity(PatronRequestDTO request) {
        return Patron.builder()
                .name(request.getName())
                .contactInformation(request.getContactInformation())
                .build();
    }

    public PatronResponseDTO mapToDTO(Patron patron) {
        PatronResponseDTO response = new PatronResponseDTO();
        response.setPatronId(patron.getPatronId());
        response.setName(patron.getName());
        response.setContactInformation(patron.getContactInformation());
        return response;
    }
}
