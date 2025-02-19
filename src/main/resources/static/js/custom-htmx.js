// document.addEventListener("DOMContentLoaded", () => {
//     document.body.addEventListener('htmx:afterSwap', (e) => {
//         console.log("flowbite 초기화");
//         initFlowbite();
//     });
// });

function menualInitFlowbite() {
    console.log("flowbite 초기화");
    initFlowbite();
}

//htmx.logAll();

htmx.defineExtension('debug', {
    onEvent: function(name, evt) {
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
