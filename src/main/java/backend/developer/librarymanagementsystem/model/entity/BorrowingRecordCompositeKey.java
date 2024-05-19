package backend.developer.librarymanagementsystem.model.entity;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.io.Serializable;
@NoArgsConstructor
@AllArgsConstructor

public class BorrowingRecordCompositeKey implements Serializable {
    private long bookId;
    private long patronId;
}