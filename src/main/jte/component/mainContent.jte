@import freeapp.life.freeaiweb.dto.ChatRespDto
@import freeapp.life.freeaiweb.entity.MessagePair

@param ChatRespDto chat

<div class="flex transition-all duration-300" id="content">
    <main class="flex flex-col flex-1 h-[90vh] xl:px-48 py-6 transition-all duration-300">
        <section class="flex-1 p-4 overflow-y-auto space-y-4 no-scrollbar"
                 hx-on--after-swap="this.scrollTop = this.scrollHeight" id="chatArea">
            @for(MessagePair pair : chat.getMessagePairs())
                @template.component.msgPair(msg = pair)
            @endfor
        </section>
        <div class="border-t border-gray-700 p-4">
            <form class="flex" hx-include="#client-id, #chat-id-box"
                  hx-on--after-request="javascript:document.getElementById('chatInput').value = ''"
                  hx-post="/message" hx-swap="beforeend" hx-target="#chatArea"
                  hx-trigger="keydown[!shiftKey && key=='Enter'] from:#chatInput, click from:#chat-input-btn"
                  id="chatForm">
                <textarea autocomplete="off"
                          class="flex-1 px-4 py-2 rounded-l-md bg-gray-700 border border-gray-600 focus:outline-none focus:ring-2 focus:ring-blue-500 text-white"
                          id="chatInput" name="msg" placeholder="Message AI" required="required"
                          rows="3">

                </textarea>
                <div class="flex flex-col">
                    <button class="flex-1 px-4 py-2 bg-gray-700 rounded-r-md hover:bg-gray-500 text-white font-bold flex items-center justify-center"
                            id="chat-input-btn" type="submit">
                        <svg aria-hidden="true" class="w-6 h-6" fill="none" viewbox="0 0 10 14">
                            <path d="M5 13V1m0 0L1 5m4-4 4 4" stroke="currentColor" stroke-linecap="round"
                                  stroke-linejoin="round" stroke-width="2"></path>
                        </svg>
                    </button>
                    <button class="flex-1 px-4 py-2 bg-gray-700 rounded-r-md hover:bg-gray-500
                              inline-block group text-white font-bold flex items-center justify-center"
                            id="chat-input-btn2" type="button" data-tooltip-target="tooltip-upload"
                            hx-post="/upload"
                            hx-encoding="multipart/form-data"
                            hx-trigger="change from:#fileInput"
                            hx-swap="none"
                            onclick="document.getElementById('fileInput').click()"
                    >
                        <input type="file" id="fileInput" name="file" class="hidden">
                        <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5"
                             stroke="currentColor" class="size-6">
                            <path stroke-linecap="round" stroke-linejoin="round"
                                  d="M3 16.5v2.25A2.25 2.25 0 0 0 5.25 21h13.5A2.25 2.25 0 0 0 21 18.75V16.5M16.5 12 12 16.5m0 0L7.5 12m4.5 4.5V3"/>
                        </svg>
                    </button>
                    <div id="tooltip-upload" role="tooltip" class="absolute z-10 invisible inline-block px-3 py-2 text-sm font-medium text-white transition-opacity duration-300 bg-gray-900 rounded-lg shadow-xs opacity-0 tooltip dark:bg-gray-700">
                        File Upload
                        <div class="tooltip-arrow" data-popper-arrow></div>
                    </div>
                </div>
            </form>
        </div>
    </main>
</div>


