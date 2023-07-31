package br.com.amarques.login.dto.view;

import java.time.LocalDateTime;

public class ErrorView {
    public final LocalDateTime timestamp;
    public final Integer status;
    public final String error;
    public final String message;
    public final String path;

    public ErrorView(Integer status, String error, String message, String path) {
        this.timestamp = LocalDateTime.now();
        this.status = status;
        this.error = error;
        this.message = message;
        this.path = path;
    }
}
