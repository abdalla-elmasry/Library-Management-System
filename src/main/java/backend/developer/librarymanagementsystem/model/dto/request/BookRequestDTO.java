package backend.developer.librarymanagementsystem.model.dto.request;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BookRequestDTO {

    @NotNull(message = "The title is required.")
    @Size(min = 2, max = 30, message = "The title must be between 2 and 30 characters.")
    private String title;

    @NotNull(message = "The author is required.")
    @Size(min = 2, max = 30, message = "The author must be between 2 and 30 characters.")
    private String author;

    @NotNull(message = "The publication year is required.")
    @Min(value = 1000, message = "The publication year can not be lower than 1000.")
    @Max(value = 2024, message = "The publication year can not be in the future.")
    private int publicationYear;

    @NotNull(message = "The ISBN is required.")
    @Pattern(regexp = "^(?:\\d{3}-?\\d{1,5}-?\\d{1,7}-?\\d{1,7}-?\\d{1})$", message = "Invalid ISBN-13 format.\nMake sure ISBN follows this format '978-xxxxx-xxxxx-x'.")
    private String ISBN;

    @NotNull(message = "The publisher is required.")
    @Size(min = 2, max = 30, message = "The publisher must be between 2 and 30 characters.")
    private String publisher;

    @NotNull(message = "The genre is required.")
    @Size(min = 2, max = 30, message = "The genre must be between 2 and 30 characters.")
    private String genre;
}
