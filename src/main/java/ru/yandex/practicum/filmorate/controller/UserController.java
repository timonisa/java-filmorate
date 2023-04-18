package ru.yandex.practicum.filmorate.controller;


import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.exception.UserAlreadyExistException;
import ru.yandex.practicum.filmorate.exception.ValidationException;
import ru.yandex.practicum.filmorate.model.User;

import java.time.LocalDate;
import java.util.Collection;
import java.util.HashMap;

@RestController
@Slf4j
public class UserController {


    @GetMapping("/users")
    public Collection<User> getUsers() {
        return null;
    }

    @PostMapping(value = "/users")
    public User create(@RequestBody User user) {
        return user;
    }

    @PutMapping(value = "/users")
    public User update(@RequestBody User user) {
        return user;
    }
}
