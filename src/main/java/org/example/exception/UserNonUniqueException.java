package org.example.exception;

public class UserNonUniqueException extends RuntimeException{
	@Override
	public String getMessage(){
		return "Такой пользователь уже есть!";
	}
}
