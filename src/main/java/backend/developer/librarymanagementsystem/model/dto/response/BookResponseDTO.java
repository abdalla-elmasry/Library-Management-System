package backend.developer.librarymanagementsystem.model.dto.response;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BookResponseDTO {
    private long bookId;
    private String title;
    private String author;
    private int publicationYear;
    private String ISBN;
    private String publisher;
    private String genre;
}
