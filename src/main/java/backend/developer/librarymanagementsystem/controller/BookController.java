package backend.developer.librarymanagementsystem.controller;

import backend.developer.librarymanagementsystem.model.dto.request.BookRequestDTO;
import backend.developer.librarymanagementsystem.model.dto.response.BookResponseDTO;
import backend.developer.librarymanagementsystem.model.entity.Book;
import backend.developer.librarymanagementsystem.service.BookService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/v1")
public class BookController {

    private final BookService service;

    public BookController(BookService service) {
        this.service = service;
    }

    @PostMapping(path = "/books")
    @ResponseStatus(HttpStatus.CREATED)
    public BookResponseDTO createBook(@Valid @RequestBody BookRequestDTO request) {
        return service.createBook(request);
    }

    @GetMapping(path = "/books/{id}")
    public BookResponseDTO findBookById(@PathVariable long id) {
        return service.findBookById(id);
    }

    @GetMapping(path = "/books")
    public Page<Book> findAllBooks(Pageable pageable) {
        return service.findAllBooks(pageable);
    }

    @PutMapping(path = "/books/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateBookById(@PathVariable long id, @Valid @RequestBody BookRequestDTO request) {
        service.updateBookById(id, request);
    }

    @DeleteMapping(path = "/books/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBookById(@PathVariable long id) {
        service.deleteBookById(id);
    }
}
