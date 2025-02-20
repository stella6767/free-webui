document.addEventListener("DOMContentLoaded", () => {
    const chatArea = document.getElementById('chatArea');
    chatArea.scrollTop = chatArea.scrollHeight;
});


document.addEventListener('htmx:responseError', evt => {
    const xhr = evt.detail.xhr;
    const alertContainer = document.getElementById('error-alert-container');

    if (alertContainer) {

        console.log("!!!")
        console.error(xhr.responseText);
        const alert =
            document.createElement('div');
        alert.className = "fixed top-3 left-6 right-6 z-50 flex justify-center transition-opacity duration-1000";
        alert.innerHTML = xhr.responseText;

        alertContainer.appendChild(alert);

        setTimeout(() => {
            alert.classList.add('opacity-0');
            // fade-out 애니메이션 지속시간(예: 1초) 후 요소 제거
            setTimeout(() => {
                alert.remove();
            }, 1000);
        }, 3000);
    }

});

function menualInitFlowbite() {
    console.log("flowbite 초기화");
    initFlowbite();
}

//htmx.logAll();

function closeModal() {
    //getInstances 가 아닌 단수형
    let modal = window.FlowbiteInstances.getInstance('Modal', 'default-modal');
    console.log("modal", modal);
    modal.hide();
}


htmx.defineExtension('debug', {
    onEvent: function (name, evt) {
        if (console.debug) {
            console.debug(name, evt)
        } else if (console) {
            console.log('DEBUG:', name, evt)
        } else {
            throw new Error('NO CONSOLE SUPPORTED')
        }
    }
})


function handleKeyDown(event) {
    // Check if the pressed key is Enter
    if (event.keyCode === 13) {
        event.preventDefault();
        console.log('Enter key pressed!');
    }
}


