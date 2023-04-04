package ru.yandex.practicum.filmorate.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.model.User;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
@Slf4j
public class UserController {
    int nextId = 1;
    private final List<User> users = new ArrayList<>();

    @GetMapping("/users")
    public List<User> getUsers() {
        return users;
    }

    @PostMapping(value = "/users")
    public User create(@RequestBody User user) {
        if (validator(user)) {
            for (int i = 0; i < users.size(); i++) {
                boolean isExist = users.get(i).equals(user);
                if (isExist) throw new UserAlreadyExistException();
                log.info("Уже есть такой пользователь " + user.toString());
            }
            user.setId(nextId);
            users.add(user);
            nextId++;
            log.info("Добавлен пользователь " + user.toString());
            return user;
        } else {
            log.error("не прошел валидацию пользователь " + user.toString());
            throw new ValidationException("не прошел валидацию");
        }
    }

    @PutMapping(value = "/users")
    public User update(@RequestBody User user) {
        if (validator(user)) {
                if (users.get(user.getId() - 1) != null) {
                    users.set(user.getId() - 1, user);
                    log.info("Обновлен пользователь " + user.toString());
                    return user;
                } else {
                    log.error("не найден пользователь " + user.toString());
                    throw new ValidationException("не найден пользователь");
                }

        } else {
            log.error("не прошел валидацию пользователь " + user.toString());
            throw new ValidationException("не прошел валидацию");
        }
    }

    public boolean validator(User user) {
        if (user.getName() == null || user.getName().isEmpty()) {
            user.setName(user.getLogin());
        }
        return !user.getLogin().isEmpty() && !user.getLogin().contains(" ") && user.getBirthday().isBefore(LocalDate.now());
    }
}
