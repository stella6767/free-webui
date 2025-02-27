!{String connectionId = java.util.UUID.randomUUID().toString();}

<div>
    <input id="client-id" name="clientId" type="hidden" value="${connectionId}">
    <div hx-ext="sse" hx-swap-oob="true" id="sse-listener"
         sse-connect="/chat-sse/${connectionId}" sse-swap="ai-response"></div>
</div>

