package ru.yandex.practicum.filmorate.controller;


import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.exception.FilmAlreadyExistException;
import ru.yandex.practicum.filmorate.exception.ValidationException;
import ru.yandex.practicum.filmorate.model.Film;

import java.time.LocalDate;
import java.util.Collection;
import java.util.HashMap;

@RestController
@Slf4j
public class FilmController {
    private final LocalDate firstMovieDate = LocalDate.parse("1895-12-28");
    private int nextId = 1;
    private final HashMap<Integer, Film> films = new HashMap<>();

    @GetMapping("/films")
    public Collection<Film> getFilms() {
        return films.values();
    }

    @PostMapping(value = "/films")
    public Film create(@RequestBody Film film) {
        if (validator(film)) {
            if (films.get(film.getId()) != null){
                log.info("Уже есть такой фильм " + film.toString());
                throw new FilmAlreadyExistException();
            }
            film.setId(nextId);
            films.put(film.getId(), film);
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
            if (films.get(film.getId()) != null) {
                films.put(film.getId(), film);
                log.info("Обновлен кинчик " + film.toString());
                return film;
            } else {
                log.error("не найден фильм " + film.toString());
                throw new FilmAlreadyExistException("не найден фильм");
            }
        } else {
            log.error("не прошел валидацию фильм  " + film.toString());
            throw new ValidationException("не прошел валидацию");
        }
    }

    private boolean validator(@NonNull Film film) {
        return !film.getName().isEmpty() && film.getDescription().length() < 201 && film.getReleaseDate().isAfter(firstMovieDate) &&
                film.getDuration() > 0;
    }
}
