package ru.yandex.practicum.filmorate.controller;


import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.exception.FilmAlreadyExistException;
import ru.yandex.practicum.filmorate.exception.ValidationException;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.service.FilmService;
import ru.yandex.practicum.filmorate.storage.FilmStorage;
import ru.yandex.practicum.filmorate.storage.InMemoryFilmStorage;

import java.time.LocalDate;
import java.util.Collection;
import java.util.HashMap;

@RestController
@Slf4j
public class FilmController {

    FilmService filmService = new FilmService();

    @GetMapping("/films/{id}")
    public HashMap<Integer, Film> getFilms(@PathVariable String id) {
        if(id == null){
            return filmService.getFilms(null);
        }else{
            return filmService.getFilms(id);
        }
    }

    @GetMapping("/films/popular?count={count}")
    public Collection<Film> getTopFilms(@PathVariable String count) {
            return filmService.popular(Integer.parseInt(count)); ///необходимо перебрать фильмы по лайкам
    }

    @PostMapping(value = "/films")
    public Film create(@RequestBody Film film) {
        return filmService.create(film);
    }

    @PutMapping(value = "/films")
    public Film update(@RequestBody Film film) throws ValidationException {
        return filmService.update(film);
    }

    @DeleteMapping(value = "/films/{id}")
    public boolean delete (@PathVariable String id) {
        return filmService.delete(id);
    }

    @PutMapping(value = "/films/{id}/like/{userId}")
    public void like(@PathVariable String id, @PathVariable String userId) throws ValidationException {
        filmService.addLike(Integer.parseInt(id), Integer.parseInt(userId)); ///тут лайки ставим
    }

    @DeleteMapping(value = "/films/{id}/like/{userId}")
    public void disLike (@PathVariable String id, @PathVariable String userId) {
        filmService.removeLike(Integer.parseInt(id), Integer.parseInt(userId)); /// тут надо удалить лайк
    }
}
