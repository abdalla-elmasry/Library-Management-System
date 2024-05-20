package backend.developer.librarymanagementsystem.service;

import backend.developer.librarymanagementsystem.mapper.BookMapper;
import backend.developer.librarymanagementsystem.model.dto.request.BookRequestDTO;
import backend.developer.librarymanagementsystem.model.dto.response.BookResponseDTO;
import backend.developer.librarymanagementsystem.model.entity.Book;
import backend.developer.librarymanagementsystem.repository.BookRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class BookServiceTest {
    @InjectMocks
    private BookService service;

    @Mock
    private BookRepository repository;

    @Mock
    private BookMapper mapper;

    @Test
    public void testCreateBook() {
        // Prepare test data
        BookRequestDTO request = new BookRequestDTO();
        request.setTitle("Book Title");
        request.setAuthor("Book Author");
        request.setPublicationYear(2018);
        request.setISBN("978-3-16-148410-0");
        request.setPublisher("publisher");
        request.setGenre("genre");

        // Mock the behavior of the mapper mapToEntity() method
        Book book = new Book();
        Book.builder()
                .title(request.getTitle())
                .author(request.getAuthor())
                .publicationYear(request.getPublicationYear())
                .ISBN(request.getISBN())
                .publisher(request.getPublisher())
                .genre(request.getGenre())
                .build();
        when(mapper.mapToEntity(any(BookRequestDTO.class))).thenReturn(book);

        // Mock the behavior of the repository save() method
        book.setBookId(1L);
        book.setTitle(request.getTitle());
        book.setAuthor(request.getAuthor());
        book.setPublicationYear(request.getPublicationYear());
        book.setISBN(request.getISBN());
        book.setPublisher(request.getPublisher());
        book.setGenre(request.getGenre());
        when(repository.save(any(Book.class))).thenReturn(book);


        // Mock the behavior of the mapper mapToDTO() method
        BookResponseDTO saved = new BookResponseDTO();
        saved.setBookId(book.getBookId());
        saved.setTitle(book.getTitle());
        saved.setAuthor(book.getAuthor());
        saved.setPublicationYear(book.getPublicationYear());
        saved.setISBN(book.getISBN());
        saved.setPublisher(book.getPublisher());
        saved.setGenre(book.getGenre());
        when(mapper.mapToDTO(any(Book.class))).thenReturn(saved);


        // Call the service method
        BookResponseDTO response = service.createBook(request);

        // Verify the results
        assertNotNull(response);
        assertEquals(saved.getBookId(), response.getBookId());
        assertEquals(saved.getTitle(), response.getTitle());
        assertEquals(saved.getAuthor(), response.getAuthor());
        assertEquals(saved.getPublicationYear(), response.getPublicationYear());
        assertEquals(saved.getISBN(), response.getISBN());
        assertEquals(saved.getPublisher(), response.getPublisher());
        assertEquals(saved.getGenre(), response.getGenre());
    }

    @Test
    public void testFindBookById() {
        // Prepare test data
        Book book = new Book();
        book.setBookId(1L);
        book.setTitle("Book Title");
        book.setAuthor("Book Author");
        book.setPublicationYear(2019);
        book.setISBN("978-3-16-148410-0");
        book.setPublisher("Book publisher");
        book.setGenre("Book genre");

        // Mock the behavior of the findById() method
        when(repository.findById(book.getBookId())).thenReturn(Optional.of(book));

        // Mock the behavior of the mapper mapToDTO() method
        BookResponseDTO saved = new BookResponseDTO();
        saved.setBookId(book.getBookId());
        saved.setTitle(book.getTitle());
        saved.setAuthor(book.getAuthor());
        saved.setPublicationYear(book.getPublicationYear());
        saved.setISBN(book.getISBN());
        saved.setPublisher(book.getPublisher());
        saved.setGenre(book.getGenre());
        when(mapper.mapToDTO(any(Book.class))).thenReturn(saved);

        // Call the service method
        BookResponseDTO response = service.findBookById(book.getBookId());

        assertNotNull(response);
        assertEquals(saved.getBookId(), response.getBookId());
        assertEquals(saved.getTitle(), response.getTitle());
        assertEquals(saved.getAuthor(), response.getAuthor());
        assertEquals(saved.getPublicationYear(), response.getPublicationYear());
        assertEquals(saved.getISBN(), response.getISBN());
        assertEquals(saved.getPublisher(), response.getPublisher());
        assertEquals(saved.getGenre(), response.getGenre());

    }

    @Test
    public void testFindAllBooks() {
        // Prepare test data
        Book book1 = new Book();
        book1.setBookId(1L);
        book1.setTitle("Book Title");
        book1.setAuthor("Book Author");
        book1.setPublicationYear(2016);
        book1.setISBN("978-3-16-148410-0");
        book1.setPublisher("Book publisher");
        book1.setGenre("Book genre");

        Book book2 = new Book();
        book2.setBookId(1L);
        book2.setTitle("Book Title2");
        book2.setAuthor("Book Author2");
        book2.setPublicationYear(2012);
        book2.setISBN("978-3-16-148410-0");
        book2.setPublisher("Book publisher2");
        book2.setGenre("Book genre2");

        List<Book> books = Arrays.asList(book1, book2);
        Page<Book> bookPage = new PageImpl<>(books);

        // Mock the behavior of the repository.findAll() method
        Pageable pageable = Pageable.unpaged();
        when(repository.findAll(pageable)).thenReturn(bookPage);

        // Call the service method
        Page<Book> responsePage = service.findAllBooks(pageable);

        // Verify the result
        assertNotNull(responsePage);
        assertEquals(2, responsePage.getTotalElements());

        // Additional checks on the first book
        Book firstBook = responsePage.getContent().get(0);
        assertEquals(book1.getBookId(), firstBook.getBookId());
        assertEquals(book1.getTitle(), firstBook.getTitle());
        assertEquals(book1.getAuthor(), firstBook.getAuthor());
        assertEquals(book1.getPublicationYear(), firstBook.getPublicationYear());
        assertEquals(book1.getISBN(), firstBook.getISBN());
        assertEquals(book1.getPublisher(), firstBook.getPublisher());
        assertEquals(book1.getGenre(), firstBook.getGenre());

        // Additional checks on the second book
        Book secondBook = responsePage.getContent().get(1);
        assertEquals(book2.getBookId(), secondBook.getBookId());
        assertEquals(book2.getTitle(), secondBook.getTitle());
        assertEquals(book2.getAuthor(), secondBook.getAuthor());
        assertEquals(book2.getPublicationYear(), secondBook.getPublicationYear());
        assertEquals(book2.getISBN(), secondBook.getISBN());
        assertEquals(book2.getPublisher(), secondBook.getPublisher());
        assertEquals(book2.getGenre(), secondBook.getGenre());
    }

    @Test
    public void testUpdateBookById() {
        // Prepare test data
        BookRequestDTO request = new BookRequestDTO();
        request.setTitle("Book Title");
        request.setAuthor("Updated Author");
        request.setPublicationYear(2015);
        request.setISBN("978-3-16-148410-0");
        request.setPublisher("Updated publisher");
        request.setGenre("Updated genre");

        Book existingBook = new Book();
        existingBook.setTitle("Book Title");
        existingBook.setAuthor("Book Author");
        existingBook.setPublicationYear(2013);
        existingBook.setISBN("978-3-16-148410-0");
        existingBook.setPublisher("Book publisher");
        existingBook.setGenre("Book genre");

        // Mock the behavior of the repository.findById() and repository.save() methods
        when(repository.findById(existingBook.getBookId())).thenReturn(Optional.of(existingBook));
        when(repository.save(any(Book.class))).thenReturn(existingBook);

        // Call the service method
        service.updateBookById(existingBook.getBookId(), request);

        // Verify the result
        assertEquals(request.getTitle(), existingBook.getTitle());
        assertEquals(request.getAuthor(), existingBook.getAuthor());
        assertEquals(request.getPublicationYear(), existingBook.getPublicationYear());
        assertEquals(request.getISBN(), existingBook.getISBN());
        assertEquals(request.getPublisher(), existingBook.getPublisher());
        assertEquals(request.getGenre(), existingBook.getGenre());

    }

    @Test
    public void testDeleteBookById() {
        // Prepare test data
        Book book = new Book();
        book.setBookId(1L);
        book.setTitle("Delete Title");
        book.setAuthor("Delete Author");
        book.setPublicationYear(2017);
        book.setISBN("978-3-16-148410-0");
        book.setPublisher("Book publisher");
        book.setGenre("Book genre");

        // Mock the behavior of the findById() method
        when(repository.findById(book.getBookId())).thenReturn(Optional.of(book));

        // Call the service method
        service.deleteBookById(book.getBookId());

        // Verify the result is deleted
        verify(repository).delete(book);
    }
}
