package backend.developer.librarymanagementsystem.service;

import backend.developer.librarymanagementsystem.mapper.BorrowingRecordMapper;
import backend.developer.librarymanagementsystem.model.dto.response.BorrowingRecordResponseDTO;
import backend.developer.librarymanagementsystem.model.entity.Book;
import backend.developer.librarymanagementsystem.model.entity.BorrowingRecord;
import backend.developer.librarymanagementsystem.model.entity.ContactInformation;
import backend.developer.librarymanagementsystem.model.entity.Patron;
import backend.developer.librarymanagementsystem.repository.BorrowingRecordRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class BorrowingRecordServiceTest {

    @Mock
    private BorrowingRecordRepository repository;

    @Mock
    private BorrowingRecordMapper mapper;

    @InjectMocks
    private BorrowingRecordService service;


    @Test
    public void allowPatronToBorrowBook() {
        // Prepare test data
        long bookId = 1L;
        long patronId = 1L;

        // Mock the behavior of the mapper mapToEntity() method
        BorrowingRecord borrowingRecord = new BorrowingRecord();
        BorrowingRecord.builder()
                .bookId(bookId)
                .patronId(patronId)
                .borrowDate(LocalDate.now())
                .returnDate(null)
                .build();
        when(mapper.mapToEntity(eq(bookId), eq(patronId))).thenReturn(borrowingRecord);

        // Mock the behavior of the repository save() method
        borrowingRecord.setBookId(bookId);
        borrowingRecord.setPatronId(patronId);
        borrowingRecord.setBorrowDate(LocalDate.now());
        borrowingRecord.setReturnDate(null);
        when(repository.save(any(BorrowingRecord.class))).thenReturn(borrowingRecord);

        // Mock the behavior of the mapper mapToDTO method
        BorrowingRecordResponseDTO saved = new BorrowingRecordResponseDTO();
        saved.setBookId(borrowingRecord.getBookId());
        saved.setPatronId(borrowingRecord.getPatronId());
        saved.setBorrowDate(borrowingRecord.getBorrowDate());
        saved.setReturnDate(borrowingRecord.getReturnDate());
        when(mapper.mapToDTO(any(BorrowingRecord.class))).thenReturn(saved);

        // Call the service method
        BorrowingRecordResponseDTO response = service.allowPatronToBorrowBook(saved.getBookId(), saved.getPatronId());

        // Verify the results
        assertNotNull(response);
        assertEquals(borrowingRecord.getBookId(), response.getBookId());
        assertEquals(borrowingRecord.getPatronId(), response.getPatronId());
        assertEquals(LocalDate.now(), response.getBorrowDate());
        assertNull(response.getReturnDate());
    }

    @Test
    public void updateReturnedBookByPatron() {
        // Prepare test data
        Book book = new Book();
        book.setBookId(1L);
        book.setTitle("Book Title");
        book.setAuthor("Book Author");
        book.setPublicationYear(2019);
        book.setISBN("978-3-16-148410-0");
        book.setPublisher("Book publisher");
        book.setGenre("Book genre");

        Patron patron = new Patron();
        patron.setPatronId(1L);
        patron.setName("Patron name");
        patron.setContactInformation(new ContactInformation("email@yahoo.com", "123 Main St, Apt 4B, Springfield, IL 62704", "+20 (100) 333-7374"));

        // Save the data in the record repository
        BorrowingRecord borrowingRecord = new BorrowingRecord();
        borrowingRecord.setBookId(book.getBookId());
        borrowingRecord.setPatronId(patron.getPatronId());
        borrowingRecord.setBorrowDate(LocalDate.now());
        borrowingRecord.setReturnDate(null);
        repository.save(borrowingRecord);

        // Mock the behavior of the repository.findById() and repository.save() methods
        BorrowingRecord updated = new BorrowingRecord();
        updated.setBookId(1L);
        updated.setPatronId(1L);
        when(repository.findByBookIdAndPatronId(book.getBookId(), patron.getPatronId())).thenReturn(Optional.of(updated));
        when(repository.save(any(BorrowingRecord.class))).thenReturn(updated);

        // Call the service method
        service.updateReturnedBookByPatron(book.getBookId(), patron.getPatronId());

        // Verify the result
        assertEquals(book.getBookId(), updated.getBookId());
        assertEquals(patron.getPatronId(), updated.getPatronId());
        assertEquals(LocalDate.now(), updated.getReturnDate());
    }
}
