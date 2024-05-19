package backend.developer.librarymanagementsystem.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "books")

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Book {

    @Id
    @Column(updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long bookId;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String author;

    @Column(nullable = false)
    private int publicationYear;

    @Column(nullable = false, unique = true)
    private String ISBN;

    @Column(nullable = false)
    private String publisher;

    @Column(nullable = false)
    private String genre;

    @OneToMany(mappedBy = "bookId")
    private List<BorrowingRecord> borrowedBooks;
}
