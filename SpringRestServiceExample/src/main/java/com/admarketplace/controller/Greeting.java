
package com.admarketplace.controller;
/*
 *  As you see in steps below, Spring uses the Jackson JSON library to automatically marshal instances of type Greeting into JSON.
 * */
public class Greeting {

    private final long id;
    private final String content;

    public Greeting(long id, String content) {
        this.id = id;
        this.content = content;
    }

    public long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }
}