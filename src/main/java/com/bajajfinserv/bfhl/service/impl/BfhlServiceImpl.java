package com.bajajfinserv.bfhl.service.impl;

import com.bajajfinserv.bfhl.dto.request.BfhlRequest;
import com.bajajfinserv.bfhl.dto.response.BfhlResponse;
import com.bajajfinserv.bfhl.exception.InvalidPayloadException;
import com.bajajfinserv.bfhl.service.BfhlService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Implementation of BfhlService with Bajaj Finserv Health business rules
 */
@Service
public class BfhlServiceImpl implements BfhlService {

    // Constant configuration details for Rajni Kumawat
    private static final String USER_ID = "rajni_kumawat_17092003"; // format: rajni_kumawat_ddmmyyyy
    private static final String EMAIL = "rajnikumawat230476@acropolis.in";
    private static final String ROLL_NUMBER = "0827CI231106";

    // Patterns for matching different components
    private static final Pattern NUMERIC_PATTERN = Pattern.compile("\\d+");
    private static final Pattern ALPHABETIC_PATTERN = Pattern.compile("[a-zA-Z]+");
    private static final Pattern SPECIAL_CHAR_PATTERN = Pattern.compile("[^a-zA-Z0-9\\s]");

    @Override
    public BfhlResponse processRequest(BfhlRequest request) {
        // Validate request and data
        if (request == null || request.getData() == null) {
            throw new InvalidPayloadException("Request body or 'data' field is null.");
        }

        List<String> inputData = request.getData();
        
        List<String> oddNumbers = new ArrayList<>();
        List<String> evenNumbers = new ArrayList<>();
        List<String> alphabets = new ArrayList<>();
        List<String> specialCharacters = new ArrayList<>();
        int sum = 0;
        
        // List to hold all alphabets in their original case for concat_string creation
        List<String> originalCaseAlphabets = new ArrayList<>();

        for (String item : inputData) {
            if (item == null || item.trim().isEmpty()) {
                continue; // Skip null/empty array values as requested
            }

            // Check if item is pure number (e.g., "334")
            if (item.matches("^\\d+$")) {
                int val = Integer.parseInt(item);
                sum += val;
                if (val % 2 == 0) {
                    evenNumbers.add(item);
                } else {
                    oddNumbers.add(item);
                }
            } 
            // Check if item is pure alphabetic (e.g., "a", "abc")
            else if (item.matches("^[a-zA-Z]+$")) {
                alphabets.add(item.toUpperCase());
                originalCaseAlphabets.add(item);
            } 
            // Check if item is pure special characters (e.g., "$")
            else if (item.matches("^[^a-zA-Z0-9\\s]+$")) {
                specialCharacters.add(item);
            } 
            // Handles mixed strings (e.g., "12a", "a$1")
            else {
                // Extract numbers
                Matcher numMatcher = NUMERIC_PATTERN.matcher(item);
                while (numMatcher.find()) {
                    String numStr = numMatcher.group();
                    int val = Integer.parseInt(numStr);
                    sum += val;
                    if (val % 2 == 0) {
                        evenNumbers.add(numStr);
                    } else {
                        oddNumbers.add(numStr);
                    }
                }

                // Extract alphabets
                Matcher alphaMatcher = ALPHABETIC_PATTERN.matcher(item);
                while (alphaMatcher.find()) {
                    String alphaStr = alphaMatcher.group();
                    alphabets.add(alphaStr.toUpperCase());
                    originalCaseAlphabets.add(alphaStr);
                }

                // Extract special characters
                Matcher specialMatcher = SPECIAL_CHAR_PATTERN.matcher(item);
                while (specialMatcher.find()) {
                    specialCharacters.add(specialMatcher.group());
                }
            }
        }

        // Build concat_string:
        // 1. Concatenate all alphabetical characters (using original case from input)
        // 2. Reverse the order
        // 3. Apply alternating caps (e.g. index 0 Uppercase, index 1 Lowercase, index 2 Uppercase...)
        String concatString = generateConcatString(originalCaseAlphabets);

        return BfhlResponse.builder()
                .isSuccess(true)
                .userId(USER_ID)
                .email(EMAIL)
                .rollNumber(ROLL_NUMBER)
                .oddNumbers(oddNumbers)
                .evenNumbers(evenNumbers)
                .alphabets(alphabets)
                .specialCharacters(specialCharacters)
                .sum(sum)
                .concatString(concatString)
                .build();
    }

    private String generateConcatString(List<String> alphabetsList) {
        if (alphabetsList.isEmpty()) {
            return "";
        }

        // Concatenate all
        StringBuilder combined = new StringBuilder();
        for (String s : alphabetsList) {
            combined.append(s);
        }

        // Reverse
        String reversed = combined.reverse().toString();

        // Apply alternating caps (Index 0: Upper, Index 1: Lower, Index 2: Upper...)
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < reversed.length(); i++) {
            char c = reversed.charAt(i);
            if (i % 2 == 0) {
                result.append(Character.toUpperCase(c));
            } else {
                result.append(Character.toLowerCase(c));
            }
        }

        return result.toString();
    }
}
