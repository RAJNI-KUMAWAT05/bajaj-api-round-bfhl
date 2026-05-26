package com.bajajfinserv.bfhl.controller;

import com.bajajfinserv.bfhl.dto.request.BfhlRequest;
import com.bajajfinserv.bfhl.dto.response.BfhlResponse;
import com.bajajfinserv.bfhl.service.BfhlService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Controller for handling BFHL requests
 */
@RestController
@RequestMapping("/bfhl")
@CrossOrigin(origins = "*")
public class BfhlController {

    private final BfhlService bfhlService;

    public BfhlController(BfhlService bfhlService) {
        this.bfhlService = bfhlService;
    }

    /**
     * POST /bfhl - Processes JSON data array and returns structured summary
     */
    @PostMapping
    public ResponseEntity<BfhlResponse> processData(@RequestBody BfhlRequest request) {
        BfhlResponse response = bfhlService.processRequest(request);
        return ResponseEntity.ok(response);
    }

    /**
     * GET /bfhl - Returns hardcoded operation code as per standard BFHL API round specs
     */
    @GetMapping
    public ResponseEntity<Map<String, Object>> getOperationCode() {
        Map<String, Object> response = new HashMap<>();
        response.put("operation_code", 1);
        return ResponseEntity.ok(response);
    }
}
