package backend.developer.librarymanagementsystem.model.dto.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@Setter
@ToString
public class BorrowingRecordRequestDTO {
    private LocalDate borrowDate;
    private LocalDate returnDate;
}
