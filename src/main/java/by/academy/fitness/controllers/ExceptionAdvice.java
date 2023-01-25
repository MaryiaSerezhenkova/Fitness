package by.academy.fitness.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.hibernate.ObjectNotFoundException;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.JdbcUpdateAffectedIncorrectNumberOfRowsException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import by.academy.fitness.exceptions.MultipleErrorResponse;
import by.academy.fitness.exceptions.ServiceException;
import by.academy.fitness.exceptions.SingleError;
import by.academy.fitness.exceptions.SingleErrorResponse;

@RestControllerAdvice
public class ExceptionAdvice {

	@ExceptionHandler(ServiceException.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public ResponseEntity<SingleErrorResponse> serviceException(ServiceException ex) {
		SingleErrorResponse response = new SingleErrorResponse();
		response.setLogref("error");
		response.setMessage(ex.getMessage());
		ex.printStackTrace();
		return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(IllegalArgumentException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ResponseEntity<SingleErrorResponse> illegalArgument(IllegalArgumentException ex) {
		SingleErrorResponse response = new SingleErrorResponse();
		response.setLogref("error");
		response.setMessage(ex.getMessage());
		ex.printStackTrace();
		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(EntityNotFoundException.class)
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	@ResponseBody
	public ResponseEntity<SingleErrorResponse>  handleCustomEntityNotFoundException(EntityNotFoundException ex) {
		SingleErrorResponse response = new SingleErrorResponse();
		response.setLogref("error");
		response.setMessage(ex.getMessage());
		ex.printStackTrace();
		return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(DataAccessException.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public ResponseEntity<SingleErrorResponse> bugNotAdded(DataAccessException ex) {
		SingleErrorResponse response = new SingleErrorResponse();
		response.setLogref("error");
		response.setMessage(ex.getMessage());
		ex.printStackTrace();
		return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(ObjectNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ResponseEntity<SingleErrorResponse> notFound(ObjectNotFoundException ex) {
		SingleErrorResponse response = new SingleErrorResponse();
		response.setLogref("error");
		response.setMessage(ex.getMessage());
		ex.printStackTrace();
		return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(JdbcUpdateAffectedIncorrectNumberOfRowsException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ResponseEntity<SingleErrorResponse>  notChanged(JdbcUpdateAffectedIncorrectNumberOfRowsException ex) {
		SingleErrorResponse response = new SingleErrorResponse();
		response.setLogref("error");
		response.setMessage(ex.getMessage());
		ex.printStackTrace();
		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(Throwable.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public ResponseEntity<SingleErrorResponse>  handleAll(Throwable ex) {
		SingleErrorResponse response = new SingleErrorResponse();
		response.setLogref("error");
		response.setMessage(ex.getMessage());
		ex.printStackTrace();
		return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	 protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
	        MultipleErrorResponse response = new MultipleErrorResponse();
	        response.setLogref("structured_error");
	        List<SingleError> list = new ArrayList<>();
	        for (FieldError fieldError : ex.getBindingResult().getFieldErrors()) {
	            SingleError singleError = new SingleError();
	            singleError.setField(fieldError.getField());
	            singleError.setMessage(fieldError.getDefaultMessage());
	            list.add(singleError);
	        }
	        response.setErrors(list);
	        ex.printStackTrace();
	        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	    }
}
