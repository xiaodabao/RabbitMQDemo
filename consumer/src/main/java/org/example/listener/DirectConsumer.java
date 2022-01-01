package org.example.listener;

import org.springframework.stereotype.Component;

@Component
public class DirectConsumer {

    public void directReceiveMessage(String message) {
        System.out.println("Direct Queue Receive message: " + message);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        throw new RuntimeException("");
    }
}
