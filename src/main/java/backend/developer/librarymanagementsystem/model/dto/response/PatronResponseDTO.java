package backend.developer.librarymanagementsystem.model.dto.response;

import backend.developer.librarymanagementsystem.model.entity.ContactInformation;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PatronResponseDTO {
    private long patronId;
    private String name;
    private String lastName;
    private ContactInformation contactInformation;
}
