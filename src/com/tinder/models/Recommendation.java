package com.tinder.models;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Recommendation {
    private Map<Integer, User> recommendedUser;

    public Recommendation(){
        this.recommendedUser = new HashMap<>();
    }

    public void addUserToRecommendation(User user){
        this.recommendedUser.put(user.getUserId(), user);
    }

    public void removeUserFromRecommendation(User user){
        this.recommendedUser.remove(user.getUserId());
    }

    public Map<Integer, User> getRecommendedUser() {
        return this.recommendedUser;
    }

    public void addUserToRecommendationList(List<User> recommendedUsers){
        recommendedUsers.forEach(user -> recommendedUser.put(user.getUserId(), user));
    }

}
