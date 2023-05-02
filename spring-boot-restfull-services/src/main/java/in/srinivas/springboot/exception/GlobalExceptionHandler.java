package in.srinivas.springboot.exception;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler{
	
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ErrorResponse> handleResourceNotFoundException(ResourceNotFoundException exception,
																		 WebRequest webRequest){
		ErrorResponse errorResponse = new ErrorResponse(
				LocalDateTime.now(),
				exception.getMessage(),
				webRequest.getDescription(false),
				"user not found");
		
		return new ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(UserAlreadyExistsException.class)
	public ResponseEntity<ErrorResponse> handleUserAlreadyExistsException(UserAlreadyExistsException exception,
																		 WebRequest webRequest){
		ErrorResponse errorResponse = new ErrorResponse(
				LocalDateTime.now(),
				exception.getMessage(),
				webRequest.getDescription(false),
				"User already registered");
		
		return new ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<Object> handleGlobalException(Exception exception, WebRequest webRequest){
		ErrorResponse errorResponse = new ErrorResponse(
				LocalDateTime.now(),
				exception.getMessage(),
				webRequest.getDescription(false),
				"INTERNAL SERVER ERROR");
		
		return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
																	HttpHeaders headers, 
																	HttpStatus status, 
																	WebRequest request) {
		
		List<ObjectError> errorsList = ex.getBindingResult().getAllErrors();
		Map<String, String> errors = new HashMap<>();
		
		errorsList.forEach((error)->{
			String fieldName = ((FieldError) error).getField();
			String message = error.getDefaultMessage();
			errors.put(fieldName, message);
		});
	
		return new ResponseEntity<Object>(errors, HttpStatus.BAD_REQUEST);
	}

}
