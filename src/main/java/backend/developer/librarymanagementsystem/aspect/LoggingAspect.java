package backend.developer.librarymanagementsystem.aspect;

import backend.developer.librarymanagementsystem.model.dto.request.BorrowingRecordRequestDTO;
import backend.developer.librarymanagementsystem.model.dto.response.BookResponseDTO;
import backend.developer.librarymanagementsystem.model.dto.response.BorrowingRecordResponseDTO;
import backend.developer.librarymanagementsystem.model.dto.response.PatronResponseDTO;
import lombok.Getter;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Getter

public class LoggingAspect {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    private long startTime;

    //POINTCUTS
    //BOOK
    @Pointcut("execution(* backend.developer.librarymanagementsystem.service.BookService.createBook(..))")
    public void createBookPointcut() {
    }

    @Pointcut(value = "execution(* backend.developer.librarymanagementsystem.service.BookService.updateBookById(..))")
    public void updateBookByIdPointcut() {
    }

    @Pointcut(value = "execution(* backend.developer.librarymanagementsystem.service.BookService.deleteBookById(..))")
    public void deleteBookByIdPointcut() {
    }

    //PATRON
    @Pointcut(value = "execution(* backend.developer.librarymanagementsystem.service.PatronService.createParton(..)) and args(request)")
    public void createPatronPointcut() {
    }

    @Pointcut(value = "execution(* backend.developer.librarymanagementsystem.service.PatronService.updatePatronById(..)) and args(id, request)")
    public void updatePatronByIdPointcut() {
    }

    @Pointcut(value = "execution(* backend.developer.librarymanagementsystem.service.PatronService.deletePatronById(..)) and args(id)")
    public void deletePatronByIdPointcut() {
    }

    //BORROWING_RECORD
    @Pointcut(value = "execution(* backend.developer.librarymanagementsystem.service.PatronService.allowPatronToBorrowBook(..)) and args(bookId, patronId, request)")
    public void allowPatronToBorrowBookPointcut() {
    }

    @Pointcut(value = "execution(* backend.developer.librarymanagementsystem.service.PatronService.updateReturnedBookByPatron(..)) and args(id, request)")
    public void updateReturnedBookByPatronPointcut() {
    }

    //BEFORE
    //BOOK
    @Before("createBookPointcut()")
    public void beforeCreateBook(JoinPoint joinPoint) {
        startTime = System.currentTimeMillis();
        Object[] args = joinPoint.getArgs();
        logger.info("Initiating creation of a new book... \n   ->  request details: " + args[0]);
    }

    @Before("updateBookByIdPointcut()")
    public void logBeforeBookUpdate(JoinPoint joinPoint) {
        startTime = System.currentTimeMillis();
        Object[] args = joinPoint.getArgs();
        logger.info("Initiating update for book with ID: " + args[0] + " ... \n   ->  request details: " + args[1]);
    }

    @Before("deleteBookByIdPointcut()")
    public void logBeforeBookDelete(JoinPoint joinPoint) {
        startTime = System.currentTimeMillis();
        Object[] args = joinPoint.getArgs();
        logger.info("Initiating deletion of book with ID: " + args[0] + " ...");
    }

    //PATRON
    @Before("createPatronPointcut()")
    public void beforeCreatePatron(JoinPoint joinPoint) {
        startTime = System.currentTimeMillis();
        Object[] args = joinPoint.getArgs();
        logger.info("Initiating creation of a new patron... \n   ->  request details: " + args[0]);
    }

    @Before("updatePatronByIdPointcut()")
    public void logBeforePatronUpdate(JoinPoint joinPoint) {
        startTime = System.currentTimeMillis();
        Object[] args = joinPoint.getArgs();
        logger.info("Initiating update for patron with ID: " + args[0] + " ... \n   ->  request details: " + args[1]);
    }

    @Before("deletePatronByIdPointcut()")
    public void logBeforePatronDelete(JoinPoint joinPoint) {
        startTime = System.currentTimeMillis();
        Object[] args = joinPoint.getArgs();
        logger.info("Initiating deletion of patron with ID: " + args[0] + " ...");
    }

    //BORROWING_RECORD
    @Before("allowPatronToBorrowBookPointcut()")
    public void beforeCreateBorrowingRecord(JoinPoint joinPoint) {
        startTime = System.currentTimeMillis();
        Object[] args = joinPoint.getArgs();
        logger.info("Initiating creation of a new borrowing record with ID: " + args[0] + " ... \n   ->   borrowing record details:  borrowed book ID: " + args[1] + " , borrowing patron ID: " + args[2] + " , borrowing date:" + ((BorrowingRecordRequestDTO) args[3]).getBorrowDate() + " , due date:" + ((BorrowingRecordRequestDTO) args[3]).getBorrowDate().plusDays(30));
    }

    @Before("updateReturnedBookByPatronPointcut()")
    public void logBeforeBorrowingRecordUpdate(JoinPoint joinPoint) {
        startTime = System.currentTimeMillis();
        Object[] args = joinPoint.getArgs();
        logger.info("Initiating returning update for borrowing record with ID: " + args[0] + " , borrowed book ID: " + args[1] + " , patron ID: " + args[2] + " ... \n   ->   returning date:" + ((BorrowingRecordRequestDTO) args[3]).getReturnDate());
    }

    //EXCEPTIONS
    //BOOK
    @AfterThrowing(value = "createBookPointcut()", throwing = "exception")
    public void logBookCreationException(JoinPoint joinPoint, Exception exception) {
        Object[] args = joinPoint.getArgs();
        logger.error("Error occurred while creating a book with request: " + args[0] + " : " + exception.getMessage());
    }

    @AfterThrowing(value = "updateBookByIdPointcut()", throwing = "exception")
    public void logBookUpdateException(JoinPoint joinPoint, Exception exception) {
        Object[] args = joinPoint.getArgs();
        logger.error("Error occurred while updating book with ID: " + args[0] + " , request: " + args[0] + " : " + exception.getMessage());
    }

    @AfterThrowing(value = "deleteBookByIdPointcut()", throwing = "exception")
    public void logBookDeleteException(JoinPoint joinPoint, Exception exception) {
        Object[] args = joinPoint.getArgs();
        logger.error("Error occurred while deleting book with ID: " + args[0] + " : " + exception.getMessage());
    }

    //PATRON
    @AfterThrowing(value = "createPatronPointcut()", throwing = "exception")
    public void logPatronCreationException(JoinPoint joinPoint, Exception exception) {
        Object[] args = joinPoint.getArgs();
        logger.error("Error occurred while creating a patron with request: " + args[0] + " : " + exception.getMessage());
    }

    @AfterThrowing(value = "updatePatronByIdPointcut()", throwing = "exception")
    public void logPatronUpdateException(JoinPoint joinPoint, Exception exception) {
        Object[] args = joinPoint.getArgs();
        logger.error("Error occurred while updating patron with ID: " + args[0] + "\n   ->  request: " + args[1] + " : " + exception.getMessage());
    }

    @AfterThrowing(value = "deletePatronByIdPointcut()", throwing = "exception")
    public void logPatronDeleteException(JoinPoint joinPoint, Exception exception) {
        Object[] args = joinPoint.getArgs();
        logger.error("Error occurred while deleting patron with ID: " + args[0] + "\n   ->  exception details : " + exception.getMessage());
    }

    //BORROW_RECORD
    @AfterThrowing(value = "allowPatronToBorrowBookPointcut()", throwing = "exception")
    public void logBorrowingRecordCreationException(JoinPoint joinPoint, Exception exception) {
        Object[] args = joinPoint.getArgs();
        logger.error("Error occurred while creating a borrowing record with bookId: " + args[0] + " and patronId: " + args[1]);
    }

    @AfterThrowing(value = "updateReturnedBookByPatronPointcut()", throwing = "exception")
    public void logBorrowingRecordUpdateException(JoinPoint joinPoint, Exception exception) {
        Object[] args = joinPoint.getArgs();
        logger.error("Error occurred while updating borrowing record with ID: " + args[0] + ", request: [returnDate: " + ((BorrowingRecordRequestDTO) args[3]).getReturnDate() + " ] : " + exception.getMessage());
    }

    //AFTER
    //BOOK
    @AfterReturning(pointcut = "createBookPointcut()", returning = "result")
    public void logSuccessfulBookCreation(BookResponseDTO result) {
        long endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime;
        logger.info("Book created successfully in " + executionTime + " ms \n   ->  book details: " + result);
    }

    @AfterReturning(pointcut = "updateBookByIdPointcut()")
    public void logSuccessfulBookUpdate() {
        long endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime;
        logger.info("Book updated successfully in " + executionTime + " ms ");
    }

    @AfterReturning(pointcut = "deleteBookByIdPointcut()")
    public void logSuccessfulBookDelete() {
        long endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime;
        logger.info("Book deleted successfully in " + executionTime + " ms ");
    }

    //PATRON
    @AfterReturning(pointcut = "createPatronPointcut()", returning = "result")
    public void logSuccessfulPatronCreation(PatronResponseDTO result) {
        long endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime;
        logger.info("Patron created successfully in " + executionTime + " ms \n   ->  patron details: " + result);
    }

    @AfterReturning(pointcut = "updatePatronByIdPointcut()")
    public void logSuccessfulPatronUpdate() {
        long endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime;
        logger.info("Patron updated successfully in " + executionTime + " ms ");
    }

    @AfterReturning(pointcut = "deletePatronByIdPointcut()")
    public void logSuccessfulPatronDelete() {
        long endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime;
        logger.info("Patron deleted successfully in " + executionTime + " ms ");
    }

    //BORROWING_RECORD
    @AfterReturning(pointcut = "allowPatronToBorrowBookPointcut()", returning = "result")
    public void logSuccessfulBorrowingRecordCreation(BorrowingRecordResponseDTO result) {
        long endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime;
        logger.info("Borrowing record for patron to borrow a book created successfully in " + executionTime + " ms \n   ->  BorrowingRecord details: " + result);
    }

    @AfterReturning(pointcut = "updateReturnedBookByPatronPointcut()")
    public void logSuccessfulBorrowingRecordUpdate() {
        long endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime;
        logger.info("The returning of borrowed book updated successfully" + executionTime + " ms ");
    }
}