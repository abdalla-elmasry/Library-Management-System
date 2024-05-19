package backend.developer.librarymanagementsystem.model.dto.request;

import backend.developer.librarymanagementsystem.model.entity.ContactInformation;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PatronRequestDTO {
    @NotNull(message = "The title is required.")
    @Size(min = 2, max = 30, message = "The title must be between 2 and 30 characters.")
    private String name;

    private ContactInformation contactInformation;
}
