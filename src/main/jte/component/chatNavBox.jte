@import freeapp.life.freeaiweb.dto.ChatRespDto
@import org.springframework.data.domain.Page

@param ChatRespDto chat

<div class="mt-5" hx-get="/chats" hx-on--after-request="javascript:menualInitFlowbite()"
     hx-swap="innerHTML" hx-target="#chat-nav-list" hx-trigger="load, newChatEvent from:body"
        <%--                 hx-vals='js:{chatId: document.getElementById(chat-id-box).value}'--%>
     hx-vals='{"chatId": "${chat == null ? 0 : chat.getId()}"}'
     hx-swap-oob="true"
     id="chat-nav-box">
    !{Page<ChatRespDto> emptyPage = Page.empty();}
    @template.component.chatsNav(currentChat = chat, chats = emptyPage)
</div>
