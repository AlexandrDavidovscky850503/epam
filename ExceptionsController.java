package App.web;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ExceptionsController extends ResponseEntityExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Object> notFoundMessage(){
        return new ResponseEntity<>("Ticket not found." ,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DateException.class)
    public ResponseEntity<Object> wrongDateMessage(){
        return new ResponseEntity<>("Wrong date." , HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(AlreadyExistException.class)
    public ResponseEntity<Object> wrongDeleteRequest(){
        return new ResponseEntity<>("The ticket with this ID already exists" , HttpStatus.CONFLICT);
    }
    @ExceptionHandler(AsyncAlreadyExistException.class)
    public ResponseEntity<Object> asyncWrongDeleteRequest(){
        return new ResponseEntity<>("The ticket with this ID already exists" , HttpStatus.CONFLICT);
    }
    @ExceptionHandler(DatabaseException.class)
    public ResponseEntity<Object> databaseExceptionMessage(){
        return new ResponseEntity<>("Database exception." , HttpStatus.BAD_REQUEST);
    }
}
