package org.example.service;

import org.example.exception.UserNonUniqueException;
import org.example.model.User;
import org.example.repository.UserRepository;

import java.util.Collection;
import java.util.stream.Collectors;

public class UserService {

	private final UserRepository userRepository;

	public UserService(UserRepository userRepository) {

		this.userRepository = userRepository;
	}

	public Object getAllLogins() {
		try {
			Collection<User> users = this.userRepository
					.getAllUsers();
			if (users == null) {
				return 0;
			}
			return users
					.stream()
					.map(User::getLogin)
					.collect(Collectors.toList());
		} catch (RuntimeException e) {
			return 0;
		}
	}

	public void addUser(String login, String password){
		User user = new User(login, password);
		if (login == null || login.isEmpty() || login.isBlank()
				&& password == null || password.isEmpty() || password.isBlank()){
			throw new IllegalArgumentException("Пользователь должен быть идентифицирован!");
		}
		boolean userExist = this.userRepository
				.getAllUsers()
				.stream()
				.anyMatch(u-> u.equals(user));
		if (userExist){
			throw new UserNonUniqueException();
		}
		this.userRepository.addUser(user);
	}
}
