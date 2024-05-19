package backend.developer.librarymanagementsystem.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "borrowing_records")

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@IdClass(BorrowingRecordCompositeKey.class)
public class BorrowingRecord {

    @Id
    @Column(name = "book_id", nullable = false, unique = true)
    private long bookId;

    @Id
    @Column(name = "patron_id", nullable = false)
    private long patronId;

    @Column(nullable = false)
    private LocalDate borrowDate;

    @Column
    private LocalDate returnDate;
}
