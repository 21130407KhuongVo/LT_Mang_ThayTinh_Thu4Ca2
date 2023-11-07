package SocketNSerializable;

import java.io.Serializable;

public class Message implements Serializable {
    private String title, body;

    public Message(String title, String body) {
        this.title = title;
        this.body = body;
    }

    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }
}
