package ru.yandex.practicum.filmorate.controller;

public class ValidationException extends RuntimeException{
    public ValidationException(){
    }

    public ValidationException(String message){
        super(message);
    }

}

