@import freeapp.life.freeaiweb.dto.ChatRespDto
@import java.util.Optional

@param ChatRespDto chat

<div class="w-5/6 text-gray-300 hover:text-white"
     id="chat-name-div-${chat != null ? chat.getId() : 0L}"
     hx-trigger="click"
     hx-get="/chat/${chat != null ? chat.getId() : 0L}"
     hx-target="#main-container"
     hx-swap="innerHTML scroll:#chatArea:bottom"
     hx-push-url="true">
    ${chat != null ? chat.getName() : ""}
</div>
