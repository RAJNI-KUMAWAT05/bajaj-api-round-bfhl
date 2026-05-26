package com.bajajfinserv.bfhl.dto.request;

import java.util.List;

/**
 * Request DTO for /bfhl endpoint
 */
public class BfhlRequest {

    private List<String> data;

    public BfhlRequest() {
    }

    public BfhlRequest(List<String> data) {
        this.data = data;
    }

    public List<String> getData() {
        return data;
    }

    public void setData(List<String> data) {
        this.data = data;
    }
}
