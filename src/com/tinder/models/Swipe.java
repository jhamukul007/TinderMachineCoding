package com.tinder.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Swipe {

    private Map<Integer, User> swipedUser;

    public Swipe(User user)  {
        if(user == null)
            this.swipedUser = new HashMap<>();
        addSwipe(user);
    }

    public void addSwipe(User user){
        swipedUser.put(user.getUserId(), user);
    }

    public User getUser(Integer userId) {
        return swipedUser.get(userId);
    }

    public void removeFromSwap(User user){
        swipedUser.remove(user.getUserId());
    }

    public boolean isSwipedUser(Integer userId){
        return swipedUser.containsKey(userId);
    }

}
