package com.gallery.websoket.enums;

public enum MessageTargetStatus {
    SENT("sent"),
    FAILED("failed"),
    PENDING("pending");

    private final String status;

    MessageTargetStatus(String status) {
        this.status = status;
    }

    public String getType() {
        return status;
    }
}
