@import java.util.Optional
@import freeapp.life.freeaiweb.entity.MessagePair

<%--todo dto로 바꾸기--%>
@param MessagePair msg

<div id="msg-pair-${msg.getId()}">
    <div class="flex justify-end">
        <div class="bg-[#414158] p-3 rounded-lg max-w-md text-white">${msg.getHumanMessage().getContent()}</div>
    </div>
    <div id="ai-response-div-${msg.getId()}" class="my-3 p-1">
        $unsafe{Optional.ofNullable(msg.getAiMessage()).map((val)->val.getContent()).orElse("") }
    </div>
</div>
