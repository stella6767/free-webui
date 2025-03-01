document.addEventListener("DOMContentLoaded", () => {
    const chatArea = document.getElementById('chatArea');
    if (chatArea) {
        chatArea.scrollTop = chatArea.scrollHeight;
    }
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

        //alertContainer.

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

function fileResponse(){
    console.log('?');
    document.getElementById('isRagBox').value = 'true';
    menualInitFlowbite()
}


function initfileList(){
    console.log("what?");
    document.getElementById('chatInput').value = '';
    document.getElementById('isRagBox').value = 'false';
    const fileList = document.getElementById('file-list');
    // file-list의 모든 자식 요소를 순회하면서, id가 'isRagBox'가 아닌 요소들을 제거
    Array.from(fileList.children).forEach(child => {
        if (child.id !== 'isRagBox') {
            fileList.removeChild(child);
        }
    });

}


// document.body.addEventListener('htmx:beforeSwap', function(evt) {
//     const oobElements = evt.detail.xhr.responseXML?.querySelectorAll('[hx-swap-oob]');
//     if (!oobElements || oobElements.length === 0) {
//         // hx-swap-oob 요소가 없으면 교체 취소
//         evt.detail.shouldSwap = false;
//     }
// });

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



// htmx.defineExtension('prebserve-oob', {
//     onEvent: function (name, evt) {
//         if (name === 'beforeSwap') {
//             const target = evt.detail.target;
//             if (target.getAttribute('hx-swap-oob') && !evt.detail.xhr.responseXML) {
//                 console.log("교체 방지");
//                 evt.detail.shouldSwap = false; // 교체 방지
//             }
//         }
//     }
// });
// <div hx-ext="preserve-oob"></div>


function handleKeyDown(event) {
    // Check if the pressed key is Enter
    if (event.keyCode === 13) {
        event.preventDefault();
        console.log('Enter key pressed!');
    }
}


