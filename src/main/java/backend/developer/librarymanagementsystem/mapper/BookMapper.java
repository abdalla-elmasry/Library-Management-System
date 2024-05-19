package backend.developer.librarymanagementsystem.mapper;

import backend.developer.librarymanagementsystem.model.dto.request.BookRequestDTO;
import backend.developer.librarymanagementsystem.model.dto.response.BookResponseDTO;
import backend.developer.librarymanagementsystem.model.entity.Book;
import org.springframework.stereotype.Component;

@Component
public class BookMapper {
    public Book mapToEntity(BookRequestDTO request) {
        return Book.builder()
                .title(request.getTitle())
                .author(request.getAuthor())
                .publicationYear(request.getPublicationYear())
                .ISBN(request.getISBN())
                .publisher(request.getPublisher())
                .genre(request.getGenre())
                .build();
    }

    public BookResponseDTO mapToDTO(Book book) {
        BookResponseDTO response = new BookResponseDTO();
        response.setBookId(book.getBookId());
        response.setTitle(book.getTitle());
        response.setAuthor(book.getAuthor());
        response.setPublicationYear(book.getPublicationYear());
        response.setISBN(book.getISBN());
        response.setPublisher(book.getPublisher());
        response.setGenre(book.getGenre());
        return response;
    }
}
