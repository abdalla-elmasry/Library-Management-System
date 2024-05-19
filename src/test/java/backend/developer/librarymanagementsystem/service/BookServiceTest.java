//package backend.developer.librarymanagementsystem.service;
//
//import backend.developer.librarymanagementsystem.model.dto.request.BookRequestDTO;
//import backend.developer.librarymanagementsystem.model.entity.Book;
//import backend.developer.librarymanagementsystem.repository.BookRepository;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.MockitoJUnitRunner;
//
//import java.util.Arrays;
//import java.util.List;
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertNotNull;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.verify;
//import static org.mockito.Mockito.when;
//
//@RunWith(MockitoJUnitRunner.class)
//public class BookServiceTest {
//    @InjectMocks
//    private BookService service;
//
//    @Mock
//    private BookRepository repository;
//
//
//    //prepare the data
//    //mock the behaviour of the save repository method
//    //call the service method
//    //verify the results
//
//    @Test
//    public void testCreateBook(){
//        //prepare the data
//        BookRequestDTO request = new BookRequestDTO();
//        request.setTitle("Book Title");
//        request.setAuthor("Book Author");
//        request.setPublicationYear(2018);
//        request.setISBN(1L);
//
//        //mock the behaviour of the save repository method
//
//        Book book = new Book();
//        book.setId(1L);
//        book.setTitle(request.getTitle());
//        book.setAuthor(request.getAuthor());
//        book.setPublicationYear(request.getPublicationYear());
//        book.setIsbn(request.getISBN());
//        when(repository.save(any(Book.class))).thenReturn(book);
//
//        //call the service method
//        Book response = service.createBook(request);
//
//        //verify the results
//        assertNotNull(response);
//        assertEquals(book.getId(),response.getId());
//        assertEquals(book.getTitle(),response.getTitle());
//        assertEquals(book.getAuthor(),response.getAuthor());
//        assertEquals(book.getPublicationYear(),response.getPublicationYear());
//        assertEquals(book.getIsbn(),response.getIsbn());
//    }
//
//    @Test
//    public void testFindBookById()
//    {
//        Book book = new Book();
//        book.setId(1L);
//        book.setTitle("Book Title");
//        book.setAuthor("Book Author");
//        book.setPublicationYear(2019);
//        book.setIsbn(1L);
//
//        when(repository.findById(book.getId())).thenReturn(Optional.of(book));
//
//        Book response = service.findBookById(book.getId());
//
//        assertNotNull(response);
//        assertEquals(book.getId(),response.getId());
//        assertEquals(book.getTitle(),response.getTitle());
//        assertEquals(book.getAuthor(),response.getAuthor());
//        assertEquals(book.getPublicationYear(),response.getPublicationYear());
//        assertEquals(book.getIsbn(),response.getIsbn());
//
//    }
//
//    @Test
//    public void testFindAllBooks()
//    {
//        Book book1 = new Book();
//        book1.setId(1L);
//        book1.setTitle("Book Title");
//        book1.setAuthor("Book Author");
//        book1.setPublicationYear(2016);
//        book1.setIsbn(1L);
//
//        Book book2 = new Book();
//        book2.setId(1L);
//        book2.setTitle("Book Title2");
//        book2.setAuthor("Book Author2");
//        book2.setPublicationYear(2012);
//        book2.setIsbn(1L);
//
//        List<Book> books = Arrays.asList(book1,book2);
//        when(repository.findAll()).thenReturn(books);
//
//        List<Book> response = service.findAllBooks();
//
//        assertNotNull(response);
//        assertEquals(book1.getId(),response.get(0).getId());
//        assertEquals(book1.getTitle(),response.get(0).getTitle());
//        assertEquals(book1.getAuthor(),response.get(0).getAuthor());
//        assertEquals(book1.getPublicationYear(),response.get(0).getPublicationYear());
//        assertEquals(book1.getIsbn(),response.get(0).getIsbn());
//    }
//    @Test
//    public void testUpdateBookById(){
//        //prepare the data
//        BookRequestDTO request = new BookRequestDTO();
//        request.setTitle("Book Title");
//        request.setAuthor("Book Author");
//        request.setPublicationYear(2015);
//        request.setISBN(1L);
//
//        //mock the behaviour of the save repository method
//
//        Book book = new Book();
//        book.setTitle("Updated Title");
//        book.setAuthor("Updated Author");
//        book.setPublicationYear(2013);
//        book.setIsbn(2L);
//
//        when(repository.findById(book.getId())).thenReturn(Optional.of(book));
//        when(repository.save(any(Book.class))).thenReturn(book);
//
//        service.updateBookById(book.getId(),request);
////        assertNotNull(book);
//        assertEquals(request.getTitle(),book.getTitle());
//        assertEquals(request.getAuthor(),book.getAuthor());
//        assertEquals(request.getPublicationYear(),book.getPublicationYear());
//        assertEquals(request.getISBN(),book.getIsbn());
//
//    }
//    @Test
//    public void testDeleteBookById(){
//        Book book = new Book();
//        book.setId(1L);
//        book.setTitle("Delete Title");
//        book.setAuthor("Delete Author");
//        book.setPublicationYear(2017);
//        book.setIsbn(2L);
//        when(repository.findById(book.getId())).thenReturn(Optional.of(book));
//        service.deleteBookById(book.getId());
//        verify(repository).delete(book);
//
//
//    }
//}
