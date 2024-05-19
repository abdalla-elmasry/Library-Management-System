package backend.developer.librarymanagementsystem.model.dto.response;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@Setter
@ToString
public class BorrowingRecordResponseDTO {
    private long borrowingId;
    private long bookId;
    private long patronId;
    private LocalDate borrowDate;
    private LocalDate returnDate;
}
