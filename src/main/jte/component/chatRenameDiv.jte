

@param Long id
@param String name

<div class="w-5/6 text-gray-300 hover:text-white" id="chat-name-div-${id}" style="display:inline;"
     hx-trigger="keydown[key=='Enter'] from:#chat-name-div-${id}, blur" hx-put="/chat/${id}" hx-swap="outerHTML"
     hx-vals='{"name": "${name}"}'
     hx-on-keydown="handleKeyDown(event)" autofocus="autofocus" contenteditable="true">
    ${name}
</div>
