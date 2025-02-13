//package freeapp.life.freeaiweb.view
//
//import kotlinx.html.DIV
//import kotlinx.html.classes
//import kotlinx.html.div
//import kotlinx.html.link
//
//
//import com.vladsch.flexmark.html.HtmlRenderer
//import com.vladsch.flexmark.parser.Parser
//import org.springframework.web.bind.annotation.*
//import org.springframework.web.servlet.mvc.method.annotation.SseEmitter
//import java.util.concurrent.Executors
//import kotlin.concurrent.thread
//
//@RestController
//@RequestMapping("/chat")
//class ChatController(val chatRepo: ChatRepository) { // 채팅 관련 Repository 예시
//
//    private val parser: Parser = Parser.builder().build()
//    private val renderer: HtmlRenderer = HtmlRenderer.builder().build()
//
//    @GetMapping("/{chatId}/stream")
//    fun chatGenerate(
//        @PathVariable chatId: Long,
//        @RequestHeader("Authorization") apiKeyHeader: String // 인증 관련 처리 예시
//    ): SseEmitter {
//        // SseEmitter 생성 (무제한 timeout: 0L)
//        val emitter = SseEmitter(0L)
//
//        // 채팅 데이터를 DB에서 조회 (예: chatRepo.retrieveChat(chatId))
//        val chatMessagePairs = chatRepo.retrieveChat(chatId)
//        val lastMessageId = chatMessagePairs.lastOrNull()?.id ?: 0L
//
//        // API 키 검증 로직, 예를 들어 listEngines(apiKey)와 유사한 처리
//        // (여기서는 간략하게 처리)
//
//        // 채널 역할을 하는 코루틴이나 스레드를 이용해 SSE 이벤트를 전송
//        // 여기서는 스레드를 이용한 간단한 예시:
//        thread {
//            var accumulated = ""
//            try {
//                // 여기는 실제로 AI 생성 이벤트를 받아오는 로직이 들어가야 함.
//                // 예시로 3회에 걸쳐 텍스트를 받아온다고 가정합니다.
//                val events = listOf(
//                    GenerationEvent.Text("코드의 첫 줄입니다.\n"),
//                    GenerationEvent.Text("두 번째 줄입니다.\n"),
//                    GenerationEvent.End("생성이 완료되었습니다.")
//                )
//
//                events.forEach { event ->
//                    when (event) {
//                        is GenerationEvent.Text -> {
//                            accumulated += event.text
//                            // 마크다운을 HTML로 변환
//                            val html = renderer.render(parser.parse(accumulated))
//                            emitter.send(SseEmitter.event().data(html))
//                        }
//                        is GenerationEvent.End -> {
//                            // DB 업데이트: 마지막 메시지 쌍에 AI 메시지 추가
//                            chatRepo.addAiMessageToPair(lastMessageId, accumulated)
//                            val html = renderer.render(parser.parse(accumulated))
//                            emitter.send(SseEmitter.event().data(html))
//                            emitter.complete()
//                        }
//                    }
//                    Thread.sleep(1000) // 예시로 1초 간격
//                }
//            } catch (ex: Exception) {
//                emitter.completeWithError(ex)
//            }
//        }
//        return emitter
//    }
//}
//
//// GenerationEvent 예시
//sealed class GenerationEvent {
//    data class Text(val text: String) : GenerationEvent()
//    data class End(val text: String) : GenerationEvent()
//}
