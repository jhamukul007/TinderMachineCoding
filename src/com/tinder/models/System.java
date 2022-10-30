package com.tinder.models;

import com.tinder.console.Print;
import com.tinder.exceptions.UserAlreadyExistsException;
import com.tinder.utils.RandomGenerator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class System {

    private List<User> users;
    private Map<Integer, Swipe> rightSwipedByUserId;
    private Map<Integer, Swipe> leftSwipedByUserId;
    private Map<Integer, Recommendation> userRecommendations;
    private final Print log;
    private Map<Integer, List<Match>> matchesByUserId;

    public System(Print log) {
        this.log = log;
        this.users = new ArrayList<>();
        this.rightSwipedByUserId = new HashMap<>();
        this.leftSwipedByUserId = new HashMap<>();
        this.userRecommendations = new HashMap<>();
        this.matchesByUserId = new HashMap<>();
    }

    public void createUser(User user) {
        if(users.contains(user))
            throw new UserAlreadyExistsException("User already exist on email address");
        users.add(user);
        log.print("user added, now adding recommendation to user");
        createRecommendationsForUser(user);
    }

    void createRecommendationsForUser(User user){
        List<User> userInRadius = users.stream().filter(user1 -> userInRange(user, user1))
                .collect(Collectors.toList());
        userInRadius.stream().filter(user1 -> {
            Swipe rightedSwiped = rightSwipedByUserId.get(user.getUserId());
            Swipe leftSwapped = leftSwipedByUserId.get(user.getUserId());

            if(!rightedSwiped.isSwipedUser(user1.getUserId()) && !leftSwapped.isSwipedUser(user1.getUserId()))
                return true;
            return false;
        }).collect(Collectors.toList());

        Recommendation recommendation = new Recommendation();
        recommendation.addUserToRecommendationList(userInRadius);
        log.print("recommendation added to user " + user.getUserId());
    }

    public void leftSwipe(User byUser, User user){
        Swipe alreadyLeftSwiped = leftSwipedByUserId.get(byUser.getUserId());
        alreadyLeftSwiped.addSwipe(user);
    }

    public void rightSwipe(User byUser, User user){
        Swipe alreadyRightSwiped = leftSwipedByUserId.get(byUser.getUserId());
        alreadyRightSwiped.addSwipe(user);

        Swipe toUserRightSwipe = leftSwipedByUserId.get(user.getUserId());
        if(toUserRightSwipe.isSwipedUser(byUser.getUserId())){
            log.print("It's match between users");
        }
    }

    private void createMatch(User byUser, User user){
        log.print("Creating match for user 1 !!!");
        Match match = new Match(RandomGenerator.getRandom(), byUser, user);
        List<Match> allMatches = matchesByUserId.getOrDefault(byUser.getUserId(), new ArrayList<Match>());
        allMatches.add(match);
        matchesByUserId.put(byUser.getUserId(), allMatches);

        log.print("Creating match to user2 !!!");
        Match match1 = new Match(RandomGenerator.getRandom(), user, byUser);
        List<Match> allMatches1 = matchesByUserId.getOrDefault(user.getUserId(), new ArrayList<Match>());
        allMatches1.add(match1);
        matchesByUserId.put(user.getUserId(), allMatches1);
    }

    public void unMatch(User byUser, User user){
        List<Match> allMatches = matchesByUserId.get(byUser.getUserId());
        Match matchedUser = allMatches.stream().filter(match -> Objects.deepEquals(match.getMatchedUser().getUserId(), user.getUserId()))
                .findFirst().get();
        int index = allMatches.indexOf(matchedUser);
        allMatches.remove(index);
        matchesByUserId.put(byUser.getUserId(), allMatches);


        List<Match> allMatches1 = matchesByUserId.get(user.getUserId());
        Match matchedUser1 = allMatches1.stream().filter(match -> Objects.deepEquals(match.getMatchedUser().getUserId(), byUser.getUserId()))
                .findFirst().get();
        int index1 = allMatches1.indexOf(matchedUser1);
        allMatches1.remove(index1);
        matchesByUserId.put(user.getUserId(), allMatches1);
        log.print("Unmatched users !!!");
    }

    public void chat(User fromUser, User toUser, ChatMessage chatMessage){
        List<Match> allMatches = matchesByUserId.get(fromUser.getUserId());
        Match matchedUser = allMatches.stream().filter(match -> Objects.deepEquals(match.getMatchedUser().getUserId(), toUser.getUserId()))
                .findFirst().get();
        matchedUser.sendChatMessage(chatMessage);
        allMatches.add(matchedUser);
        matchesByUserId.put(fromUser.getUserId(), allMatches);


        List<Match> allMatches1 = matchesByUserId.get(toUser.getUserId());
        Match matchedUser1 = allMatches.stream().filter(match -> Objects.deepEquals(match.getMatchedUser().getUserId(), fromUser.getUserId()))
                .findFirst().get();
        matchedUser1.sendChatMessage(chatMessage);
        allMatches1.add(matchedUser1);
        matchesByUserId.put(toUser.getUserId(), allMatches1);

        log.print("Sent chat successfully !!!");
    }

    boolean userInRange(User ofUser, User toUser) {
        return Math.abs(ofUser.getLocation() - toUser.getLocation()) <= ofUser.getRadius();
    }

}
