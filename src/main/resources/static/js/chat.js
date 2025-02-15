
//htmx.logAll();


// const clientId = generateUUID(); // 고유 UUID 생성
// const clientIdInput = document.getElementById('client-id');
// clientIdInput.value = clientId; // Hidden Input에 값 설정
//
// const eventSource =
//     new EventSource(`http://localhost:8080/chat-sse/${clientId}`);
//
// eventSource.addEventListener("ai-response", e => {
//     const {data: responseData} = e;
//     //console.log("event data", responseData);
//     console.log(responseData);
//     const uniqueId = e.lastEventId;
//     // ai-content div 안에 새로운 메시지를 추가
//
//     const aiContentDiv =
//         document.getElementById(`chatArea`);
//
//     aiContentDiv.innerHTML += `${responseData} `;  // 새 메시지를 추가 (새로운 <p> 태그로 감싸서)
// });
//
//
// eventSource.onerror = (error) => {
//     console.error("SSE Error:", error);
//     eventSource.close();
// };
//
//
// // 페이지를 떠날 때 연결 종료
// window.addEventListener("beforeunload", () => {
//     eventSource.close();
// });
//
// function generateUUID() {
//     return 'xxxxxxxx-xxxx-4xxx-yxxx-xxxxxxxxxxxx'.replace(/[xy]/g, function (c) {
//         var r = Math.random() * 16 | 0, v = c === 'x' ? r : (r & 0x3 | 0x8);
//         return v.toString(16);
//     });
// }
