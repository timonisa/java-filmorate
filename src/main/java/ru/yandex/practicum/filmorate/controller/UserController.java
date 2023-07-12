package ru.yandex.practicum.filmorate.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.service.UserService;
import ru.yandex.practicum.filmorate.storage.InMemoryUserStorage;

import java.util.Collection;
import java.util.HashMap;
import java.util.Set;

@RestController
@Slf4j
public class UserController {

    UserService userService = new UserService();

    @GetMapping("/users")
    public Collection<User> getUsers() {
        return userService.getUsers(null);
    }

    @GetMapping("/users/{id}")
    public Collection<User> getUsersById(@PathVariable String id) {
        if(id == null){
            return userService.getUsers(null);
        }else{
            return userService.getUsers(id);
        }
    }

    @GetMapping("/users/{id}/friends")
    public Set<User> getFriends(@PathVariable String id) {
        return userService.getFriends(id);
    }

    @GetMapping("/users/{id}/friends/common/{otherId}")
    public Set<User> commonFriends(@PathVariable String id, @PathVariable String otherId) {
        return userService.commonFriends(id, otherId); /// тут надо сравнить
    }


    @PostMapping(value = "/users")
    public User create(@RequestBody User user) {
        return userService.create(user);
    }

    @PutMapping(value = "/users")
    public User update(@RequestBody User user) {
        return userService.update(user);
    }

    @DeleteMapping(value = "/users/{id}")
    public boolean delete(@PathVariable String id) {
        return userService.delete(id); /// тут надо удалить в друзья к айди
    }

    @PutMapping(value = "/users/{id}/friends/{friendId}")
    public void addFriend (@PathVariable String id, @PathVariable String friendId) {
        return; /// тут надо добавить в друзья к айди
    }

    @DeleteMapping(value = "/users/{id}/friends/{friendId}")
    public void deleteFriend (@PathVariable String id, @PathVariable String friendId) {
        return; /// тут надо удалить в друзья к айди
    }
}
