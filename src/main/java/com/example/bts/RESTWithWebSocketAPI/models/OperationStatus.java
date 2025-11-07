package com.example.bts.RESTWithWebSocketAPI.models;

public class OperationStatus {
    boolean successful = false;

    public OperationStatus(boolean successful) {
        this.successful = successful;
    }

    public boolean isSuccessful() {
        return successful;
    }

    public void setSuccessful(boolean successful) {
        this.successful = successful;
    }
}