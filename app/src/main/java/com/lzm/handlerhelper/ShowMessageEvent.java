package com.lzm.handlerhelper;

/**
 * Created by lzm on 2017/8/25.
 */
public class ShowMessageEvent {

    private String message;

    public ShowMessageEvent(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
