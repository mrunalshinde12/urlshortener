const API_BASE = 'http://localhost:9090';

function shortenUrl() {
    const input = document.getElementById("urlInput");
    const result = document.getElementById("result");
    const btn = document.querySelector("button");
    
    let url = input.value.trim();
    if (!url) {
        result.innerHTML = '<div class="error">Please enter a URL</div>';
        return;
    }
    
    btn.disabled = true;
    btn.innerHTML = '<i class="fas fa-spinner fa-spin"></i> Shortening...';
    
    fetch(`${API_BASE}/api/shorten`, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ originalUrl: url })
    })
    .then(res => {
        if (!res.ok) throw new Error(`HTTP ${res.status}`);
        return res.json();
    })
    .then(data => {
        const shortUrl = `${API_BASE}/s/${data.shortCode}`;
        result.innerHTML = `
            <div class="success"> 
                Short URL: ${shortUrl}
                <small><button onclick="copyToClipboard('${shortUrl}')">Copy Link</button></small>
            </div>`;
        loadStats();
    })
    .catch(err => {
        result.innerHTML = `<div class="error">❌ ${err.message}</div>`;
    })
    .finally(() => {
        btn.disabled = false;
        btn.innerHTML = '<i class="fas fa-magic"></i> Shorten';
    });
}

function copyToClipboard(text) {
    navigator.clipboard.writeText(text).then(() => {
        console.log("Copied:", text);
    }).catch(err => {
        console.error("Copy failed:", err);
    });
}

function loadStats() {
    fetch(`${API_BASE}/stats`)
    .then(res => res.json())
    .then(urls => {
        document.getElementById("stats").innerHTML = 
            urls.slice(0,5).map(u => { 
                const shortUrl = `${API_BASE}/s/${u.shortCode}`;
                return `
                    <div class="stat-item">
                        <span title="${u.originalUrl}">${u.originalUrl.slice(0,30)}...</span>
                        <a href="${shortUrl}" target="_blank" class="short-link">${shortUrl}</a>
                        <button class="copy-btn" onclick="copyToClipboard('${shortUrl}')">Copy</button>
                    </div>
                `;
            }).join('');
    }).catch(() => {});
}



document.getElementById("urlInput").addEventListener("keypress", e => e.key === "Enter" && shortenUrl());
document.getElementById("urlInput").focus();
loadStats();
setInterval(loadStats, 5000);