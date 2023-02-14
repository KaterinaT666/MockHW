import org.assertj.core.api.Assertions;
import org.example.model.User;
import org.example.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;
public class UserRepositoryTest {

	private UserRepository userRepository;
	User user1 = new User("Login1", "pass1");
	User user2 = new User("Login2", "pass2");
	User user3 = new User("Login3", "pass3");

	@BeforeEach
	public void setUp() {
		userRepository = new UserRepository();
	}

	@Test
	public void getNullUserList() {
		Collection<User> expected = userRepository.getAllUsers();
		Assertions.assertThat(expected).hasSize(0);
	}


	@Test
	public void getAllUsersList() {
		userRepository.addUser(user1);
		userRepository.addUser(user2);
		userRepository.addUser(user3);
		Collection<User> expected = userRepository.getAllUsers();
		Collection<User> actual = new ArrayList<>();
		actual.add(user1);
		actual.add(user2);
		actual.add(user3);
		assertEquals(expected, actual);
	}

	@Test
	public void testUserLogin() {
		userRepository.addUser(user1);
		Optional<User> user1 = userRepository.findUserByLogin("Login1");
		assertTrue(user1.isPresent());
	}

	@Test
	public void testNonUserLogin() {
		userRepository.addUser(user3);
		Optional<User> user3 = userRepository.findUserByLogin("Login4");
		assertFalse(user3.isPresent());
	}

	@Test
	public void testUserLoginAndPassword() {
		userRepository.addUser(user3);
		Optional<User> user3 = userRepository.findUserByLoginAndPassword("Login3", "pass3");
		assertTrue(user3.isPresent());
	}
	@Test
	public void testEqualsPasswordDifferentLogin() {
		userRepository.addUser(user2);
		Optional<User> user2 = userRepository.findUserByLoginAndPassword("Login1", "pass2");
		assertTrue(user2.isPresent());
	}
	@Test
	public void testEqualsLoginDifferentPassword() {
		userRepository.addUser(user1);
		Optional<User> user1 = userRepository.findUserByLoginAndPassword("Login1", "pass2");
		assertTrue(user1.isPresent());
	}
}
