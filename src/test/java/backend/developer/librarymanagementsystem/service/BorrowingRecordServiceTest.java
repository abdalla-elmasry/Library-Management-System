//package backend.developer.librarymanagementsystem.service;
//
//import backend.developer.librarymanagementsystem.model.dto.request.BorrowingRecordRequestDTO;
//import backend.developer.librarymanagementsystem.model.entity.Book;
//import backend.developer.librarymanagementsystem.model.entity.BorrowingRecord;
//import backend.developer.librarymanagementsystem.model.entity.Patron;
//import backend.developer.librarymanagementsystem.repository.BookRepository;
//import backend.developer.librarymanagementsystem.repository.BorrowingRecordRepository;
//import backend.developer.librarymanagementsystem.repository.PatronRepository;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.*;
//import org.mockito.junit.MockitoJUnitRunner;
//
//import java.time.LocalDate;
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertNotNull;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.when;
//
//@RunWith(MockitoJUnitRunner.class)
//public class BorrowingRecordServiceTest {
//    @Mock
//    private BorrowingRecordRepository repository;
//
//    @Mock//spy
//    private BookRepository bookRepository;
//
//    @Mock//spy
//    private PatronRepository patronRepository;
//
//
//    @InjectMocks
//    private BorrowingRecordService service;
//
//
//    @Test
//    public void allowPatronToBorrowBook(){
//        BorrowingRecordRequestDTO request = new BorrowingRecordRequestDTO();
//        request.setBorrowingDate(LocalDate.of(2013,4,5));
//        request.setReturningDate(LocalDate.of(2015,4,3));
//
//        Book book = new Book();
//        book.setId(1L);
//        book.setTitle("Title Test");
//        book.setAuthor("Author Test");
//        book.setPublicationYear(2016);
//        book.setIsbn(3L);
//
//        Patron patron = new Patron();
//        patron.setId(1L);
//        patron.setName("Name");
//        patron.setContactInformation("+20(100) 333-7347");
//
//
//        BorrowingRecord borrowingRecord = new BorrowingRecord();
//        borrowingRecord.setBorrowingId(1L);
//        borrowingRecord.setBook(book);
//        borrowingRecord.setPatron(patron);
//        borrowingRecord.setBorrowingDate(request.getBorrowingDate());
//        borrowingRecord.setDueDate(request.getBorrowingDate().plusDays(30));
//        borrowingRecord.setReturningDate(null);
//
//        when(bookRepository.findById(book.getId())).thenReturn(Optional.of(book));
//        when(patronRepository.findById(patron.getId())).thenReturn(Optional.of(patron));
//
//        when(repository.save(any(BorrowingRecord.class))).thenReturn(borrowingRecord);
//
//        //inject
//
//        BorrowingRecord response = service.allowPatronToBorrowBook(book.getId(),patron.getId(),request);
//
//        assertNotNull(response);
//        assertEquals(borrowingRecord.getBorrowingId(),response.getBorrowingId());
//        assertEquals(borrowingRecord.getBook(),response.getBook());
//        assertEquals(borrowingRecord.getPatron(),response.getPatron());
//        assertEquals(borrowingRecord.getBorrowingDate(),response.getBorrowingDate());
//        assertEquals(borrowingRecord.getDueDate(),response.getDueDate());
//        assertEquals(borrowingRecord.getReturningDate(),response.getReturningDate());
//
//    }
//
//    @Test
//    public void updateReturnedBookByPatron(){
//        BorrowingRecordRequestDTO request = new BorrowingRecordRequestDTO();
//        request.setBorrowingDate(LocalDate.of(1900,1,1));
//        request.setReturningDate(LocalDate.of(1900,1,1));
//
//        Book book = new Book();
//        book.setTitle("Updated Title");
//        book.setAuthor("Updated Author");
//        book.setPublicationYear(2013);
//        book.setIsbn(2L);
//
//        Patron patron = new Patron();
//        patron.setId(1L);
//        patron.setName("Name Updated");
//        patron.setContactInformation("+20(100) 333-7374");
//
//        BorrowingRecord borrowingRecord = new BorrowingRecord();
//        borrowingRecord.setBorrowingId(1L);
//        borrowingRecord.setBook(book);
//        borrowingRecord.setPatron(patron);
//        borrowingRecord.setBorrowingDate(request.getBorrowingDate());//why the request? because we do not change this data in the update method
//        borrowingRecord.setDueDate(request.getBorrowingDate().plusDays(30));
//        borrowingRecord.setReturningDate(LocalDate.of(2018,9,9));
//
//        when(repository.findById(borrowingRecord.getBorrowingId())).thenReturn(Optional.of(borrowingRecord));
//        when(repository.save(any(BorrowingRecord.class))).thenReturn(borrowingRecord);
//
//        service.updateReturnedBookByPatron(borrowingRecord.getBorrowingId(), book.getId(), patron.getId(), request);
//        assertNotNull(borrowingRecord);
//        assertEquals(request.getBorrowingDate(),borrowingRecord.getBorrowingDate());
//        assertEquals(request.getReturningDate(),borrowingRecord.getReturningDate());
//
//    }
//}
