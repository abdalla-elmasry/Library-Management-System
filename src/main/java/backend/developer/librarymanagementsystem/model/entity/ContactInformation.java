package backend.developer.librarymanagementsystem.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor

@Embeddable
public class ContactInformation {
    @NotNull(message = "The title is required.")
    @Email(message = "Invalid email.")
    @Column(nullable = false,unique = true)
    private String email;

    @NotNull(message = "The address is required.")
    @Size(min = 2, max = 50, message = "The address must be between 2 and 30 characters.")
    @Column(nullable = false)
    private String address;

    @Pattern(regexp = "^\\+?[0-9. ()-]{7,25}$",message = "Invalid phone number format.")
    @Column(nullable = false,unique = true)
    private String phoneNumber;
}
