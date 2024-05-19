package backend.developer.librarymanagementsystem.repository;

import backend.developer.librarymanagementsystem.model.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
//    @Cacheable(value = "books",key = "#id")
//    Book findById(long id);

    //    @Cacheable(value = "value=booksList",key = "#pageNo")
//    Iterable<T> findAll();
    boolean existsByTitle(String title);
    boolean existsByISBN(String isbn);
}
