@import freeapp.life.freeaiweb.dto.ChatRespDto

@param ChatRespDto chat

<div class="w-5/6 text-gray-300 hover:text-white" id="chat-name-div-${chat.getId()}" hx-trigger="click"
     hx-get="/chat/${chat.getId()}" hx-target="#main-container" hx-swap="innerHTML scroll:#chatArea:bottom"
     hx-push-url="true">${chat.getName()}
</div>
