package backend.developer.librarymanagementsystem.service;

import backend.developer.librarymanagementsystem.exception.AlreadyExistsException;
import backend.developer.librarymanagementsystem.exception.NotFoundException;
import backend.developer.librarymanagementsystem.mapper.BookMapper;
import backend.developer.librarymanagementsystem.model.dto.request.BookRequestDTO;
import backend.developer.librarymanagementsystem.model.dto.response.BookResponseDTO;
import backend.developer.librarymanagementsystem.model.entity.Book;
import backend.developer.librarymanagementsystem.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository repository;
    private final BookMapper mapper;

    @Transactional(propagation = Propagation.REQUIRED)
    public BookResponseDTO createBook(BookRequestDTO request) {
        validateBookTitleNonExistence(request);
        validateBookIsbnNonExistence(request);

        Book book = mapper.mapToEntity(request);
        Book saved = repository.save(book);
        return mapper.mapToDTO(saved);
    }

    @Cacheable("book")
    @Transactional(readOnly = true)
    public BookResponseDTO findBookById(long id) {
        Book book = validateBookExistence(id);
        return mapper.mapToDTO(book);
    }

    @Cacheable("books")
    @Transactional(readOnly = true)
    public Page<Book> findAllBooks(@PageableDefault(size = 10) Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void updateBookById(long id, BookRequestDTO request) {
        Book book = validateBookExistence(id);

        validateBookTitleNonExistence(request);
        validateBookIsbnNonExistence(request);

        book.setTitle(request.getTitle());
        book.setAuthor(request.getAuthor());
        book.setPublicationYear(request.getPublicationYear());
        book.setISBN(request.getISBN());
        book.setPublisher(request.getPublisher());
        book.setGenre(request.getGenre());

        repository.save(book);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteBookById(long id) {
        Book book = validateBookExistence(id);
        repository.delete(book);
    }

    private Book validateBookExistence(long id) {
        return repository.findById(id)
                .orElseThrow(() ->
                        new NotFoundException(String.format("The book with ID: [%s] does not exist.", id)));
    }
    private void validateBookIsbnNonExistence(BookRequestDTO request) {
        if (repository.existsByISBN(request.getISBN())) {
            throw new AlreadyExistsException(String.format("The book with ISBN: [%s] already exists.", request.getISBN()));
        }
    }
    private void validateBookTitleNonExistence(BookRequestDTO request) {
        if (repository.existsByTitle(request.getTitle())) {
            throw new AlreadyExistsException(String.format("The book with title: [%s] already exists.", request.getTitle()));
        }
    }
}
