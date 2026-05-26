document.addEventListener("DOMContentLoaded", () => {
    const jsonInput = document.getElementById("json-input");
    const backendUrlInput = document.getElementById("backend-url");
    const submitBtn = document.getElementById("submit-btn");
    const btnLoader = document.getElementById("btn-loader");
    const errorBox = document.getElementById("error-box");
    const errorMessage = document.getElementById("error-message");
    const apiStatus = document.getElementById("api-status");

    const emptyResults = document.getElementById("empty-results");
    const resultsContent = document.getElementById("results-content");

    // Output DOM refs
    const resSuccess = document.getElementById("res-success");
    const resUserId = document.getElementById("res-userid");
    const resEmail = document.getElementById("res-email");
    const resRoll = document.getElementById("res-roll");
    const resSum = document.getElementById("res-sum");
    const resConcat = document.getElementById("res-concat");

    const listAlphabets = document.getElementById("list-alphabets");
    const listNumbers = document.getElementById("list-numbers");
    const listSpecial = document.getElementById("list-special");

    const blockAlphabets = document.getElementById("block-alphabets");
    const blockNumbers = document.getElementById("block-numbers");
    const blockSpecial = document.getElementById("block-special");

    const filterPills = document.querySelectorAll(".filter-pill");

    // Active filters set
    let activeFilters = new Set(["alphabets", "numbers", "special"]);

    // Test API status on start
    checkApiStatus();

    // Check target API status
    async function checkApiStatus() {
        const url = backendUrlInput.value.trim();
        try {
            const res = await fetch(url, { method: "GET" });
            if (res.ok) {
                apiStatus.textContent = "API Online";
                apiStatus.classList.add("online");
            } else {
                apiStatus.textContent = "API Offline";
                apiStatus.classList.remove("online");
            }
        } catch (e) {
            apiStatus.textContent = "API Offline";
            apiStatus.classList.remove("online");
        }
    }

    // JSON check and payload submit
    submitBtn.addEventListener("click", async () => {
        errorBox.style.display = "none";
        const rawJson = jsonInput.value.trim();
        const url = backendUrlInput.value.trim();

        if (!rawJson) {
            showError("Please enter a JSON payload.");
            return;
        }

        let parsedPayload;
        try {
            parsedPayload = JSON.parse(rawJson);
        } catch (e) {
            showError("Invalid JSON structure. Ensure double quotes are used for keys and strings.");
            return;
        }

        if (!parsedPayload || typeof parsedPayload !== "object" || !Array.isArray(parsedPayload.data)) {
            showError("Invalid payload format. Must contain a 'data' array.");
            return;
        }

        // Show loading state
        submitBtn.disabled = true;
        btnLoader.style.display = "block";
        submitBtn.querySelector("span").textContent = "Processing...";

        try {
            const response = await fetch(url, {
                method: "POST",
                headers: {
                    "Content-Type": "application/json"
                },
                body: JSON.stringify(parsedPayload)
            });

            if (!response.ok) {
                const errData = await response.json().catch(() => ({}));
                throw new Error(errData.error_message || `HTTP Server Error ${response.status}`);
            }

            const data = await response.json();
            renderResponse(data);
            checkApiStatus(); // Refresh status indicator
        } catch (err) {
            showError(err.message || "Failed to connect to backend server.");
            hideResults();
        } finally {
            submitBtn.disabled = false;
            btnLoader.style.display = "none";
            submitBtn.querySelector("span").textContent = "Process Payload";
        }
    });

    // Render results
    function renderResponse(data) {
        emptyResults.style.display = "none";
        resultsContent.style.display = "flex";

        resSuccess.textContent = data.is_success ? "true" : "false";
        resUserId.textContent = data.user_id || "-";
        resEmail.textContent = data.email || "-";
        resRoll.textContent = data.roll_number || "-";
        resSum.textContent = data.sum !== undefined ? data.sum : "0";
        resConcat.textContent = data.concat_string || "-";

        // Display list elements
        renderTags(listAlphabets, data.alphabets);
        
        // Combine odd and even numbers for full numbers display
        const allNumbers = [...(data.even_numbers || []), ...(data.odd_numbers || [])];
        renderTags(listNumbers, allNumbers);
        
        renderTags(listSpecial, data.special_characters);

        updateDisplayBlocks();
    }

    // Helper to render individual tag elements
    function renderTags(container, list) {
        container.innerHTML = "";
        if (!list || list.length === 0) {
            container.innerHTML = '<span class="helper-text">None found</span>';
            return;
        }
        list.forEach(item => {
            const span = document.createElement("span");
            span.className = "item-tag";
            span.textContent = item;
            container.appendChild(span);
        });
    }

    // Update visibility of blocks based on active filters
    function updateDisplayBlocks() {
        blockAlphabets.style.display = activeFilters.has("alphabets") ? "flex" : "none";
        blockNumbers.style.display = activeFilters.has("numbers") ? "flex" : "none";
        blockSpecial.style.display = activeFilters.has("special") ? "flex" : "none";
    }

    // Handle filter pills toggles
    filterPills.forEach(pill => {
        pill.addEventListener("click", () => {
            const filterType = pill.getAttribute("data-filter");
            if (activeFilters.has(filterType)) {
                activeFilters.delete(filterType);
                pill.classList.remove("active");
            } else {
                activeFilters.add(filterType);
                pill.classList.add("active");
            }
            updateDisplayBlocks();
        });
    });

    function showError(msg) {
        errorMessage.textContent = msg;
        errorBox.style.display = "flex";
    }

    function hideResults() {
        emptyResults.style.display = "flex";
        resultsContent.style.display = "none";
    }
});
