package ru.yandex.practicum.filmorate;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.bind.annotation.RestController;
import ru.yandex.practicum.filmorate.controller.FilmController;
import ru.yandex.practicum.filmorate.controller.UserController;
import ru.yandex.practicum.filmorate.exception.ValidationException;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.model.User;
import java.util.HashMap;


import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@RestController
class FilmorateApplicationTests {

	UserController uc = new UserController();
	FilmController fc = new FilmController();

/*
	@Test
	void createNewUser() {
		User user = User.builder()
				.id(1)
				.name("Филя")
				.email("zhena@zhizni.net")
				.login("Filya")
				.birthday(LocalDate.parse("1946-08-20"))
				.build();
		uc.create(user);
		assertEquals("{1=User(friends=null, id=1, email=zhena@zhizni.net, login=Filya, name=Филя, birthday=1946-08-20)}", String.valueOf(uc.getUsers(null)));
	}

	@Test
	void createUserWithoutDog() {
		User user = User.builder()
				.id(2)
				.name("Филя")
				.email("zhenahoroshayazhizni.net")
				.login("Filya")
				.birthday(LocalDate.parse("1946-08-20"))
				.build();
		Throwable exception = assertThrows(ValidationException.class, () -> uc.create(user));
		assertEquals("не прошел валидацию", exception.getMessage());
	}

	@Test
	void createUserWithBlanksInLogin() {
		User user = User.builder()
				.id(2)
				.name("Филя")
				.email("zhena@zhizni.net")
				.login("Fi lya")
				.birthday(LocalDate.parse("1946-08-20"))
				.build();
		Throwable exception = assertThrows(ValidationException.class, () -> uc.create(user));
		assertEquals("не прошел валидацию", exception.getMessage());
	}

	@Test
	void createNewFilm() {
		Film film = Film.builder()
				.id(1)
				.name("Филя")
				.description("Сутулая собака")
				.duration(9879)
				.releaseDate(LocalDate.parse("1895-12-29"))
				.build();
		fc.create(film);
		assertEquals("{1=Film(likes=null, id=1, name=Филя, description=Сутулая собака, releaseDate=1895-12-29, duration=9879)}", String.valueOf(fc.getFilms(null)));
	}

	@Test
	void createFilmBeforeFirst() {
		Film film = Film.builder()
				.id(1)
				.name("Филя")
				.description("Сутулая собака")
				.duration(9879)
				.releaseDate(LocalDate.parse("1895-12-28"))
				.build();
		Throwable exception = assertThrows(ValidationException.class, () -> fc.create(film));
		assertEquals("не прошел валидацию", exception.getMessage());
	}

	@Test
	void createFilmWithoutName() {
		Film film = Film.builder()
				.id(1)
				.name("")
				.description("Сутулая собака")
				.duration(9879)
				.releaseDate(LocalDate.parse("1995-12-28"))
				.build();
		Throwable exception = assertThrows(ValidationException.class, () -> fc.create(film));
		assertEquals("не прошел валидацию", exception.getMessage());
	}
*/
}
