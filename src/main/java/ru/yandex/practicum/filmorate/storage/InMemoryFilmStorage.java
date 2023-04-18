package ru.yandex.practicum.filmorate.storage;

import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.model.Film;

import java.util.HashMap;

@Component
public class InMemoryFilmStorage implements FilmStorage{

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
        return null;
    }

    @Override
    public Film update(Film film) {
        return null;
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
}
