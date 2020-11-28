package com.task.model;

public enum Message {
    APOLOGIZE_MESSAGE,
    HELLO_MESSAGE;

    public String getApologizeMessage() {
        return "Приносим наши извинения, на данный момент в нашей базе нет информации по вашему городуБ но в ближайшее время мы его обязательно добавим. До скорых встреч!";
    }

    public String getHelloMessage() {
        return ",здравствуйте! Рады привестствовать вас в нашем приложении, давайте начнем: введите город, в который планируете поехать.";
    }
}
