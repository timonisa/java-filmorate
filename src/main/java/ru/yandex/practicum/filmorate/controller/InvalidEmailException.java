package ru.yandex.practicum.filmorate.controller;

public class InvalidEmailException extends RuntimeException {
    public InvalidEmailException() {
    }


    public InvalidEmailException(String message) {
        super(message);
    }

}