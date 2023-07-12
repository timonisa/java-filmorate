package ru.yandex.practicum.filmorate.storage;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.exception.FilmAlreadyExistException;
import ru.yandex.practicum.filmorate.exception.ValidationException;
import ru.yandex.practicum.filmorate.model.Film;

import java.time.LocalDate;
import java.util.HashMap;

@Component
@Slf4j
public class InMemoryFilmStorage implements FilmStorage{
    private int nextId = 1;
    private final LocalDate firstMovieDate = LocalDate.parse("1895-12-28");
    private final HashMap<Integer, Film> films = new HashMap<>();

    @Override
    public Film getById(int id) {
        return films.get(id);
    }

    @Override
    public HashMap<Integer, Film> getFilms() {
        return films;
    }

    @Override
    public Film create(Film film) {
        if (validator(film)) {
            if (films.get(film.getId()) != null) {
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

    @Override
    public Film update(Film film) {
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

    @Override
    public boolean delete(int id) {
        if(films.isEmpty() || !films.containsKey(id)) {
            return false;
        } else {
            films.remove(id);
            return true;
        }
    }

    private boolean validator(@NonNull Film film) {
        return !film.getName().isEmpty() && film.getDescription().length() < 201 && film.getReleaseDate().isAfter(firstMovieDate) &&
                film.getDuration() > 0;
    }
}
