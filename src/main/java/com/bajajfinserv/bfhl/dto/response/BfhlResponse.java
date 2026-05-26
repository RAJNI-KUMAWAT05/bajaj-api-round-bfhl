package com.bajajfinserv.bfhl.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Response DTO for /bfhl endpoint
 */
public class BfhlResponse {

    @JsonProperty("is_success")
    private boolean isSuccess;

    @JsonProperty("user_id")
    private String userId;

    @JsonProperty("email")
    private String email;

    @JsonProperty("roll_number")
    private String rollNumber;

    @JsonProperty("odd_numbers")
    private List<String> oddNumbers;

    @JsonProperty("even_numbers")
    private List<String> evenNumbers;

    @JsonProperty("alphabets")
    private List<String> alphabets;

    @JsonProperty("special_characters")
    private List<String> specialCharacters;

    @JsonProperty("sum")
    private int sum;

    @JsonProperty("concat_string")
    private String concatString;

    public BfhlResponse() {
    }

    // Builder pattern for clean construction
    public static BfhlResponseBuilder builder() {
        return new BfhlResponseBuilder();
    }

    // Getters and Setters
    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean success) {
        isSuccess = success;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRollNumber() {
        return rollNumber;
    }

    public void setRollNumber(String rollNumber) {
        this.rollNumber = rollNumber;
    }

    public List<String> getOddNumbers() {
        return oddNumbers;
    }

    public void setOddNumbers(List<String> oddNumbers) {
        this.oddNumbers = oddNumbers;
    }

    public List<String> getEvenNumbers() {
        return evenNumbers;
    }

    public void setEvenNumbers(List<String> evenNumbers) {
        this.evenNumbers = evenNumbers;
    }

    public List<String> getAlphabets() {
        return alphabets;
    }

    public void setAlphabets(List<String> alphabets) {
        this.alphabets = alphabets;
    }

    public List<String> getSpecialCharacters() {
        return specialCharacters;
    }

    public void setSpecialCharacters(List<String> specialCharacters) {
        this.specialCharacters = specialCharacters;
    }

    public int getSum() {
        return sum;
    }

    public void setSum(int sum) {
        this.sum = sum;
    }

    public String getConcatString() {
        return concatString;
    }

    public void setConcatString(String concatString) {
        this.concatString = concatString;
    }

    /**
     * Builder for BfhlResponse
     */
    public static class BfhlResponseBuilder {
        private final BfhlResponse response;

        public BfhlResponseBuilder() {
            response = new BfhlResponse();
        }

        public BfhlResponseBuilder isSuccess(boolean isSuccess) {
            response.setSuccess(isSuccess);
            return this;
        }

        public BfhlResponseBuilder userId(String userId) {
            response.setUserId(userId);
            return this;
        }

        public BfhlResponseBuilder email(String email) {
            response.setEmail(email);
            return this;
        }

        public BfhlResponseBuilder rollNumber(String rollNumber) {
            response.setRollNumber(rollNumber);
            return this;
        }

        public BfhlResponseBuilder oddNumbers(List<String> oddNumbers) {
            response.setOddNumbers(oddNumbers);
            return this;
        }

        public BfhlResponseBuilder evenNumbers(List<String> evenNumbers) {
            response.setEvenNumbers(evenNumbers);
            return this;
        }

        public BfhlResponseBuilder alphabets(List<String> alphabets) {
            response.setAlphabets(alphabets);
            return this;
        }

        public BfhlResponseBuilder specialCharacters(List<String> specialCharacters) {
            response.setSpecialCharacters(specialCharacters);
            return this;
        }

        public BfhlResponseBuilder sum(int sum) {
            response.setSum(sum);
            return this;
        }

        public BfhlResponseBuilder concatString(String concatString) {
            response.setConcatString(concatString);
            return this;
        }

        public BfhlResponse build() {
            return response;
        }
    }
}
