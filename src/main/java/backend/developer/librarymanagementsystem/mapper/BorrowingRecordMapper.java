package backend.developer.librarymanagementsystem.mapper;

import backend.developer.librarymanagementsystem.model.dto.response.BorrowingRecordResponseDTO;
import backend.developer.librarymanagementsystem.model.entity.BorrowingRecord;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class BorrowingRecordMapper {

    public BorrowingRecord mapToEntity(long bookId,long patronId) {
        return BorrowingRecord.builder()
                .bookId(bookId)
                .patronId(patronId)
                .borrowDate(LocalDate.now())
                .returnDate(null)
                .build();
    }

    public BorrowingRecordResponseDTO mapToDTO(BorrowingRecord entity) {
        BorrowingRecordResponseDTO response = new BorrowingRecordResponseDTO();
        response.setBookId(entity.getBookId());
        response.setPatronId(entity.getPatronId());
        response.setBorrowDate(entity.getBorrowDate());
        response.setReturnDate(entity.getReturnDate());
        return response;
    }
}
