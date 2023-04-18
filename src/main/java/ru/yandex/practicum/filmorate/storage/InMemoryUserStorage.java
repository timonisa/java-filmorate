package ru.yandex.practicum.filmorate.storage;

import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.model.User;


@Component
public class InMemoryUserStorage implements UserStorage{
    @Override
    public User create(User user) {
        return null;
    }

    @Override
    public User update(User user) {
        return null;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }
}
