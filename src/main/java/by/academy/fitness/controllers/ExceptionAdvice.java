package by.academy.fitness.controllers;


import javax.persistence.EntityNotFoundException;

import org.hibernate.ObjectNotFoundException;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.JdbcUpdateAffectedIncorrectNumberOfRowsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import by.academy.fitness.domain.dto.ErrorInfoDTO;
import by.academy.fitness.exceptions.ServiceException;

@RestControllerAdvice
public class ExceptionAdvice {
	
	@ExceptionHandler(ServiceException.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public ErrorInfoDTO serviceException(ServiceException ex) {
		return new ErrorInfoDTO("Internal Server Error", 500);
	}
	@ExceptionHandler(IllegalArgumentException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ErrorInfoDTO illegalArgument(IllegalArgumentException ex) {
		return new ErrorInfoDTO("exception.illegal.argument");
	}

	

	@ExceptionHandler(EntityNotFoundException.class)
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	@ResponseBody
	public ErrorInfoDTO handleCustomEntityNotFoundException(EntityNotFoundException ex) {
	
		return new ErrorInfoDTO("EntityNotFoundException");
	}
	@ExceptionHandler(DataAccessException.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public ErrorInfoDTO bugNotAdded(DataAccessException ex) {
		return new ErrorInfoDTO(ex.getMessage());
	}

	@ExceptionHandler(ObjectNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ErrorInfoDTO notFound(ObjectNotFoundException ex) {
		return new ErrorInfoDTO("exception.not.found");
	}

	@ExceptionHandler(JdbcUpdateAffectedIncorrectNumberOfRowsException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ErrorInfoDTO notChanged(JdbcUpdateAffectedIncorrectNumberOfRowsException ex) {
		return new ErrorInfoDTO("exception.not.changed");
	}
	@ExceptionHandler(Throwable.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public ErrorInfoDTO handleAll(Throwable ex) {
		ex.printStackTrace();
		return new ErrorInfoDTO(ex.getMessage());
	}
}
