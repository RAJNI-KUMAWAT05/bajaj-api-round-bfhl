package com.bajajfinserv.bfhl.service;

import com.bajajfinserv.bfhl.dto.request.BfhlRequest;
import com.bajajfinserv.bfhl.dto.response.BfhlResponse;

/**
 * Service interface for business operations related to BFHL API
 */
public interface BfhlService {
    
    /**
     * Processes request data and returns formatted response matching requirements
     * @param request BfhlRequest containing input data list
     * @return BfhlResponse with processed results
     */
    BfhlResponse processRequest(BfhlRequest request);
}
