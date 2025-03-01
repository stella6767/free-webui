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
        @template.component.fileListBox()
        <div class="border-t border-gray-700 p-4">
            <form class="flex" hx-include="#client-id, #chat-id-box, #isRagBox"
                  hx-on--after-request="javascript:initfileList()"
                  hx-post="/message" hx-swap="beforeend" hx-target="#chatArea"
                  hx-trigger="keydown[!shiftKey && key=='Enter'] from:#chatInput, click from:#chat-input-btn"
                  hx-disinherit="*"
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
                    @template.component.ragUploadBtn()
                </div>
            </form>
        </div>
    </main>
</div>


