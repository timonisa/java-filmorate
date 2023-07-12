package ru.yandex.practicum.filmorate.storage;

import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.model.User;

import java.util.HashMap;

public interface UserStorage {
    HashMap<Integer, User> getUsers();

    User getUsersById(int id);

    User create(User user);

    User update (User user);

    boolean delete(int id);

}
