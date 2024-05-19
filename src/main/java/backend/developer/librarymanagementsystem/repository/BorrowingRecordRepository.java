package backend.developer.librarymanagementsystem.repository;

import backend.developer.librarymanagementsystem.model.entity.BorrowingRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BorrowingRecordRepository extends JpaRepository<BorrowingRecord, Long> {

    Optional<BorrowingRecord> findByBookIdAndPatronId(long bookId, long patronId);
    boolean existsByBookId(long bookId);
}
