package ru.yandex.practicum.filmorate.service;

import lombok.NonNull;
import org.apache.logging.log4j.util.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.exception.ValidationException;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.storage.InMemoryFilmStorage;

import java.util.*;

@Service
public class FilmService {
    InMemoryFilmStorage filmStorage = new InMemoryFilmStorage();///чет не пойму как создать один экземпляр класса хранения


    public HashMap<Integer, Film> getFilms(String id) {
        if(id == null){
            return filmStorage.getFilms();
        }else{
            HashMap<Integer, Film> films = new HashMap<>();
            films.put(Integer.parseInt(id), filmStorage.getById(Integer.parseInt(id)));
            return films;
        }
    }
    public Film create(Film film) {
        return filmStorage.create(film);
    }

    public Film update(Film film) throws ValidationException {
        return filmStorage.update(film);
    }

    public boolean delete(String id) {
        return filmStorage.delete(Integer.parseInt(id));
    }
    
    

    public void addLike(int filmId, int userId){
       Film film = filmStorage.getById(filmId);
       Set<Long> likes = film.getLikes();
       likes.add((long) userId);
       film.setLikes(likes);
    }

    public void removeLike(int filmId, int userId){
        Film film = filmStorage.getById(filmId);
        Set<Long> likes = film.getLikes();
        likes.remove((long) userId);
        film.setLikes(likes);
    }

    public Collection<Film> popular(int count){
        if(count == Integer.parseInt(null)) {
            count = 10;
        }
        List<Film> films = (List<Film>) filmStorage.getFilms();
        Set<Film> topFilms = null;
        Comparator<Film> comparator = Comparator.comparing(Film::likesSize);
        films.sort(comparator);
        for (int i = 0; i < count; i++) {
            Film film = films.get(i);
            topFilms.add(film);
        }
        return topFilms;
    }
}
