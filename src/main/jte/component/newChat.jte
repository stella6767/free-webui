
<div id="content">
    <div class="bg-gray-900 h-[80vh] flex items-center justify-center">
        <div class="text-center">
            <h1 class="text-2xl font-semibold text-white mb-6">What can I help with?</h1>
            <div class="flex items-stretch justify-center" hx-include="#client-id, #chat-input-area"
                 hx-post="/chat" hx-target="#main-container"
                 hx-trigger="keydown[!shiftKey && key=='Enter'] from:#chat-input-area, click from:#chat-init-btn"
                 id="new-chat-msg-box">
                        <textarea
                                class="bg-gray-700 w-96 p-3 border border-gray-300 rounded-l-lg focus:outline-none focus:ring-2 focus:ring-blue-500"
                                id="chat-input-area" name="msg" placeholder="Message AI" required="required"></textarea>
                <button class="px-4 py-2 bg-gray-700 rounded-r-md hover:bg-gray-500 text-white font-bold flex items-center justify-center"
                        id="chat-init-btn" type="submit">
                    <svg aria-hidden="true" class="w-6 h-6" fill="none" viewbox="0 0 10 14">
                        <path d="M5 13V1m0 0L1 5m4-4 4 4" stroke="currentColor" stroke-linecap="round"
                              stroke-linejoin="round" stroke-width="2"></path>
                    </svg>
                </button>
            </div>
        </div>
    </div>
</div>
