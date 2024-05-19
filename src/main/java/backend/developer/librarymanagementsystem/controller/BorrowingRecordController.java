package backend.developer.librarymanagementsystem.controller;

import backend.developer.librarymanagementsystem.model.dto.response.BorrowingRecordResponseDTO;
import backend.developer.librarymanagementsystem.service.BorrowingRecordService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/v1")
public class BorrowingRecordController {
    private final BorrowingRecordService service;

    public BorrowingRecordController(BorrowingRecordService service) {
        this.service = service;
    }

    @PostMapping(path = "/borrow/{bookId}/patron/{patronId}")
    @ResponseStatus(HttpStatus.CREATED)
    public BorrowingRecordResponseDTO allowPatronToBorrowBook(@PathVariable long bookId,@PathVariable long patronId) {
        return service.allowPatronToBorrowBook(bookId, patronId);
    }

    @PutMapping(path = "/return/{bookId}/patron/{patronId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateReturnedBookByPatron(@PathVariable long bookId, @PathVariable long patronId) {
        service.updateReturnedBookByPatron(bookId, patronId);
    }
}
