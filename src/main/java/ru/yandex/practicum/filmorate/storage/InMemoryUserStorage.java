package ru.yandex.practicum.filmorate.storage;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.exception.UserAlreadyExistException;
import ru.yandex.practicum.filmorate.exception.ValidationException;
import ru.yandex.practicum.filmorate.model.User;

import java.time.LocalDate;
import java.util.HashMap;


@Component
@Slf4j
public class InMemoryUserStorage implements UserStorage{
    private int nextId = 1;
    private final HashMap<Integer, User> users = new HashMap<>();

    @Override
    public HashMap<Integer, User> getUsers() {
        return users;
    }

    @Override
    public User getUsersById(int id) {
        return users.get(id);
    }

    @Override
    public User create(User user) {
        if (validator(user)) {
            if (users.get(user.getId()) != null) {
                log.info("Уже есть такой пользователь " + user.toString());
                throw new UserAlreadyExistException();
            }
            user.setId(nextId);
            users.put(user.getId(), user);
            nextId++;
            log.info("Добавлен пользователь " + user.toString());
            return user;
        } else {
            log.error("не прошел валидацию пользователь " + user.toString());
            throw new ValidationException("не прошел валидацию");
        }
    }

    @Override
    public User update(User user) {
        if (validator(user)) {
            if (users.get(user.getId()) != null) {
                users.put(user.getId(), user);
                log.info("Обновлен пользователь " + user.toString());
                return user;
            } else {
                log.error("не найден пользователь " + user.toString());
                throw new UserAlreadyExistException("не найден пользователь");
            }
        } else {
            log.error("не прошел валидацию пользователь " + user.toString());
            throw new ValidationException("не прошел валидацию");
        }
    }

    @Override
    public boolean delete(int id) {
        if(users.isEmpty() || !users.containsKey(id)) {
            return false;
        } else {
            users.remove(id);
            return true;
        }
    }


    private boolean validator(@NonNull User user) {
        if (user.getName() == null || user.getName().isEmpty()) {
            user.setName(user.getLogin());
        }
        return !user.getLogin().isEmpty() && user.getEmail().contains("@") && !user.getEmail().isEmpty()
                && !user.getLogin().contains(" ") && user.getBirthday().isBefore(LocalDate.now());
    }
}
