package ru.yandex.practicum.filmorate.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.model.Film;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
@Slf4j
public class FilmController {
    private final LocalDate firstMovieDate = LocalDate.parse("1895-12-28");
    int nextId = 1;
    private final List<Film> films = new ArrayList<>();


    @GetMapping("/films")
    public List<Film> getFilms() {
        return films;
    }

    @PostMapping(value = "/films")
    public Film create(@RequestBody Film film) {
        if (validator(film)) {
            for (int i = 0; i < films.size(); i++) {
                boolean isExist = films.get(i).equals(film);
                if (isExist) throw new UserAlreadyExistException();
                ;
                log.info("Уже есть такой фильм " + film.toString());
            }
            film.setId(nextId);
            films.add(film);
            nextId++;
            log.info("Добавлен кинчик " + film.toString());
            return film;
        } else {
            log.error("не прошел валидацию фильм  " + film.toString());
            throw new ValidationException("не прошел валидацию");
        }
    }

    @PutMapping(value = "/films")
    public Film update(@RequestBody Film film) throws ValidationException {
        if (validator(film)) {
            if (films.get(film.getId() - 1) != null) {
                films.set(film.getId() - 1, film);
                log.info("Обновлен кинчик " + film.toString());
                return film;
            } else {
                log.error("не найден фильм " + film.toString());
                throw new ValidationException("не найден фильм");
            }
        } else {
            log.error("не прошел валидацию фильм  " + film.toString());
            throw new ValidationException("не прошел валидацию");
        }
    }

    public boolean validator(Film film) {
        return !film.getName().isEmpty() && film.getDescription().length() < 201 && film.getReleaseDate().isAfter(firstMovieDate) &&
                film.getDuration() > 0;
    }
}
