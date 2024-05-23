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
    @NotNull(message = "The email is required.")
    @Email(message = "Invalid email.")
    @Column(nullable = false, unique = true)
    private String email;

    @NotNull(message = "The address is required.")
    @Size(min = 2, max = 50, message = "The address must be between 2 and 30 characters.")
    @Column(nullable = false)
    private String address;

    @NotNull(message = "The phone number is required.")
    @Size(min = 7, max = 25, message = "Phone number must be between 7 and 25 characters")
    @Pattern(regexp = "^\\+20 \\(1[0-9]{2}\\) \\d{3}-\\d{4}$", message = "Invalid phone number format. \nMake sure it follows this format +20 (1XX) XXX-XXXX")
    @Column(nullable = false, unique = true)
    private String phoneNumber;
}
