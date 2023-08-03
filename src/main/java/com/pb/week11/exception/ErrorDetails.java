package com.pb.week11.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;
import java.util.Date;
import java.util.List;

@Getter
@Setter
public class ErrorDetails {
    private int status;
    private String error;
    private List<String> messages;
    private Instant instant;

    public ErrorDetails(int status, String error, List<String> messageList) {
        this.status = status;
        this.error = error;
        this.messages = messageList;
        this.instant = Instant.now();
    }
}
