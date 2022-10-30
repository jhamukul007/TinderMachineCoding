package com.tinder.models;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Match {
    private Integer id;
    private User sourceUser;
    private User matchedUser;
    private Set<ChatMessage> chatsMessages;

    public Match(Integer id, User sourceUser, User matchedUser) {
        this.id = id;
        this.sourceUser = sourceUser;
        this.matchedUser = matchedUser;
        this.chatsMessages = new HashSet<>();
    }

    public void sendChatMessage(ChatMessage message){
        this.chatsMessages.add(message);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getSourceUser() {
        return sourceUser;
    }

    public void setSourceUser(User sourceUser) {
        this.sourceUser = sourceUser;
    }

    public User getMatchedUser() {
        return matchedUser;
    }

    public void setMatchedUser(User matchedUser) {
        this.matchedUser = matchedUser;
    }

    public Set<ChatMessage> getChatsMessages() {
        return chatsMessages;
    }

    public void setChatsMessages(Set<ChatMessage> chatsMessages) {
        this.chatsMessages = chatsMessages;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Match match = (Match) o;
        return Objects.equals(sourceUser, match.sourceUser) && Objects.equals(matchedUser, match.matchedUser);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sourceUser, matchedUser);
    }

}
