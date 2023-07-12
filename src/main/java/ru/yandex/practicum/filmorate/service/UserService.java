package ru.yandex.practicum.filmorate.service;

import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.storage.InMemoryUserStorage;

import java.util.*;

@Service
public class UserService {
    InMemoryUserStorage userStorage = new InMemoryUserStorage(); ///чет не пойму как создать один экземпляр класса хранения


    public Collection<User> getUsers(String id) {
        Set<User> usersSet = null;
        if (id == null) {
            HashMap<Integer, User> users;
            users = userStorage.getUsers();
            for (int i = 0; i < users.size(); i++) {
                assert false;
                usersSet.add(users.get(i));
            }
        } else if(userStorage.getUsers().containsKey(Integer.parseInt(id))){
            usersSet.add(userStorage.getUsersById(Integer.parseInt(id)));
        }
        return usersSet;
    }

    public Set<User> getFriends(String id) {
        Set<User> friends = null;
        List<Long> friendsId = (List<Long>) userStorage.getUsers().get(Integer.parseInt(id)).getFriends();
        for (int i = 0; i < friendsId.size(); i++) {
            int friendId = Math.toIntExact(friendsId.get(i));
            friends.add(userStorage.getUsersById(friendId));
        }
        return friends;
    }

    public User create(User user) {
        return userStorage.create(user);
    }

    public User update(User user) {
        return userStorage.update(user);
    }

    public boolean delete(String id) {
        return userStorage.delete(Integer.parseInt(id));
    }


    public void addFriend(int id, int friendId){
        User user = userStorage.getUsersById(id);
        Set<Long> friends = user.getFriends();
        friends.add((long) friendId);
        user.setFriends(friends);

        User friend = userStorage.getUsersById(friendId);
        Set<Long> friendFriends = friend.getFriends();
        friendFriends.add((long) id);
        friend.setFriends(friendFriends);
    }

    public void removeFriend(int id, int friendId){
        User user = userStorage.getUsersById(id);
        Set<Long> friends = user.getFriends();
        friends.remove((long) friendId);
        user.setFriends(friends);

        User friend = userStorage.getUsersById(friendId);
        Set<Long> friendFriends = friend.getFriends();
        friendFriends.remove((long) id);
        friend.setFriends(friendFriends);
    }

    public Set<User> commonFriends(String id, String friendId){
        User user = userStorage.getUsersById(Integer.parseInt(id));
        Set<Long> userFriends = user.getFriends();
        HashMap<Long, Long> userFriendsHashMap = null;
        userFriendsHashMap.putAll((Map<? extends Long, ? extends Long>) userFriends);
        User friend = userStorage.getUsersById(Integer.parseInt(friendId));
        Set<Long> friendFriends = friend.getFriends();
//        HashMap<Long, Long> friendFriendsHashMap = null;
//        friendFriendsHashMap.putAll((Map<? extends Long, ? extends Long>) friendFriends);
        Set<User> commonFriends = null;
        Long userFriendId = null;
        for (int i = 1; i <= userFriendsHashMap.size(); i++) {
            userFriendId = userFriendsHashMap.get(i);
            if(friendFriends.contains(userFriendId)){
                commonFriends.add(userStorage.getUsersById(Math.toIntExact(userFriendId)));
            }
        }///тут говно какое то?
        return commonFriends;
    }
}
