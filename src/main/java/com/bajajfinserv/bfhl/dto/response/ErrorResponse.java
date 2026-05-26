package com.bajajfinserv.bfhl.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Error Response DTO for exception handling
 */
public class ErrorResponse {

    @JsonProperty("is_success")
    private boolean isSuccess;

    @JsonProperty("error_code")
    private int errorCode;

    @JsonProperty("error_message")
    private String errorMessage;

    public ErrorResponse() {
    }

    public ErrorResponse(boolean isSuccess, int errorCode, String errorMessage) {
        this.isSuccess = isSuccess;
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean success) {
        isSuccess = success;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
