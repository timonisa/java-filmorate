package ru.yandex.practicum.filmorate.controller;

public class UserAlreadyExistException extends RuntimeException {
    public UserAlreadyExistException() {
    }

    public UserAlreadyExistException(String message) {
        super(message);
    }

}
