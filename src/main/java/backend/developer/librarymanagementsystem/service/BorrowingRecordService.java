package backend.developer.librarymanagementsystem.service;

import backend.developer.librarymanagementsystem.exception.AlreadyExistsException;
import backend.developer.librarymanagementsystem.exception.NotFoundException;
import backend.developer.librarymanagementsystem.mapper.BorrowingRecordMapper;
import backend.developer.librarymanagementsystem.model.dto.response.BorrowingRecordResponseDTO;
import backend.developer.librarymanagementsystem.model.entity.BorrowingRecord;
import backend.developer.librarymanagementsystem.repository.BorrowingRecordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class BorrowingRecordService {
    private final BorrowingRecordRepository repository;
    private final BorrowingRecordMapper mapper;

    @Transactional(propagation = Propagation.REQUIRED)
    public BorrowingRecordResponseDTO allowPatronToBorrowBook(long bookId, long patronId) {
        validateRecordBookIdNonExistence(bookId);

        BorrowingRecord borrowingRecord = mapper.mapToEntity();
        BorrowingRecord saved = repository.save(borrowingRecord);
        return mapper.mapToDTO(saved);
    }


    @Transactional(propagation = Propagation.REQUIRED)
    public void updateReturnedBookByPatron(long bookId, long patronId) {
        BorrowingRecord borrowingRecord = validateRecordExistence(bookId, patronId);

        borrowingRecord.setReturnDate(LocalDate.now());
        repository.save(borrowingRecord);
    }

    private BorrowingRecord validateRecordExistence(long bookId, long patronId) {
        return repository.findByBookIdAndPatronId(bookId, patronId)
                .orElseThrow(() ->
                        new NotFoundException(String.format("The borrowing record with Book Id: [%s], and Parton Id: [%s] doesn't exist.", bookId, patronId)));
    }
    private void validateRecordBookIdNonExistence(long bookId) {
        if (repository.existsByBookId(bookId)) {
            throw new AlreadyExistsException(String.format("The borrowing record with Book Id: [%s] already exists.", bookId));
        }
    }
}
