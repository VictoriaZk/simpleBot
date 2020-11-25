package com.task.model;

public enum Message {
    APOLOGIZE_MESSAGE,
    HELLO_MESSAGE;

    public String getApologizeMessage() {
        return "We are sorry, but now we haven't any information about this city. We try to add information later!";
    }

    public String getHelloMessage() {
        return "hello! Nice to meet you on this chat. Let's start! Choose the city and i'll give you information about it.";
    }
}
