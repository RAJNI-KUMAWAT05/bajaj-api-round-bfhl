package com.bajajfinserv.bfhl.service;

import com.bajajfinserv.bfhl.dto.request.BfhlRequest;
import com.bajajfinserv.bfhl.dto.response.BfhlResponse;
import com.bajajfinserv.bfhl.exception.InvalidPayloadException;
import com.bajajfinserv.bfhl.service.impl.BfhlServiceImpl;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

public class BfhlServiceTest {

    private final BfhlService bfhlService = new BfhlServiceImpl();

    @Test
    public void testStandardSampleInput() {
        BfhlRequest request = new BfhlRequest(Arrays.asList("a", "1", "334", "4", "R", "$"));
        BfhlResponse response = bfhlService.processRequest(request);

        assertTrue(response.isSuccess());
        assertEquals("rajni_kumawat_17092003", response.getUserId());
        assertEquals("rajnikumawat230476@acropolis.in", response.getEmail());
        assertEquals("0827CI231106", response.getRollNumber());

        // Numbers
        assertEquals(Arrays.asList("1"), response.getOddNumbers());
        assertEquals(Arrays.asList("334", "4"), response.getEvenNumbers());
        assertEquals(339, response.getSum());

        // Alphabets (Uppercase)
        assertEquals(Arrays.asList("A", "R"), response.getAlphabets());

        // Special characters
        assertEquals(Arrays.asList("$"), response.getSpecialCharacters());

        // Concat string rules:
        // Concatenation: "aR"
        // Reverse: "Ra"
        // Alternating caps starting index 0 upper, index 1 lower: 'R' (upper) -> 'R', 'a' (lower) -> 'a' -> "Ra"
        assertEquals("Ra", response.getConcatString());
    }

    @Test
    public void testConcatStringAlternatingCaps() {
        BfhlRequest request = new BfhlRequest(Arrays.asList("a", "b", "c", "d"));
        BfhlResponse response = bfhlService.processRequest(request);

        // Concatenation: "abcd"
        // Reverse: "dcba"
        // Alternating: Index 0 'd'->D, Index 1 'c'->c, Index 2 'b'->B, Index 3 'a'->a -> "DcBa"
        assertEquals("DcBa", response.getConcatString());
    }

    @Test
    public void testMixedStringsAndMultiCharacterElements() {
        BfhlRequest request = new BfhlRequest(Arrays.asList("12a", "a$1", "XYZ"));
        BfhlResponse response = bfhlService.processRequest(request);

        // "12a" -> numbers "12", alphabets "a" -> "A"
        // "a$1" -> alphabets "a" -> "A", special "$", number "1"
        // "XYZ" -> alphabets "XYZ" -> "X", "Y", "Z" -> "XYZ" in upper
        // Odd numbers: "1"
        // Even numbers: "12"
        // Sum: 12 + 1 = 13
        // Alphabets: "A", "A", "XYZ" -> uppercase. Wait, matcher extracts "a" (A), "a" (A), "XYZ" (XYZ).
        // Let's verify details
        assertEquals(Arrays.asList("1"), response.getOddNumbers());
        assertEquals(Arrays.asList("12"), response.getEvenNumbers());
        assertEquals(13, response.getSum());
        
        // Alphabets list should contain "A", "A", "XYZ"
        assertEquals(Arrays.asList("A", "A", "XYZ"), response.getAlphabets());
        assertEquals(Arrays.asList("$"), response.getSpecialCharacters());

        // Concat string:
        // originalCaseAlphabets: "a", "a", "XYZ"
        // Concatenation: "aaXYZ"
        // Reverse: "ZYXaa"
        // Index 0: Z (Upper) -> Z
        // Index 1: Y (Lower) -> y
        // Index 2: X (Upper) -> X
        // Index 3: a (Lower) -> a
        // Index 4: a (Upper) -> A
        // Result: "ZyXaA"
        assertEquals("ZyXaA", response.getConcatString());
    }

    @Test
    public void testEmptyAndNullPayload() {
        // Test null request
        assertThrows(InvalidPayloadException.class, () -> bfhlService.processRequest(null));

        // Test empty data list
        BfhlRequest request = new BfhlRequest(Collections.emptyList());
        BfhlResponse response = bfhlService.processRequest(request);
        assertTrue(response.isSuccess());
        assertTrue(response.getOddNumbers().isEmpty());
        assertTrue(response.getEvenNumbers().isEmpty());
        assertTrue(response.getAlphabets().isEmpty());
        assertTrue(response.getSpecialCharacters().isEmpty());
        assertEquals(0, response.getSum());
        assertEquals("", response.getConcatString());
    }
}
