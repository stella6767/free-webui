package freeapp.life.freeaiweb.exception

import gg.jte.TemplateEngine
import gg.jte.TemplateOutput
import gg.jte.output.StringOutput
import mu.KotlinLogging
import org.springframework.core.annotation.AnnotationUtils
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.server.ResponseStatusException


@RestControllerAdvice
class GlobalExceptionHandler(
    private val templateEngine: TemplateEngine,
) {

    private val log = KotlinLogging.logger {  }

    /**
     * Handle exception
     *
     *  Error는 Exception의 하위 클래스가 아니므로 잡지 못한다.
     *  필터, 인터셉터, 서블릿 컨테이너 레벨, 혹은 비동기 작업 내에서 발생한 예외는 Spring MVC의 예외 처리 체인에 도달하지 않을 수 있음
     *
     * @param ex
     * @return
     */
    @ExceptionHandler(Exception::class)
    fun handleException(ex: Exception): ResponseEntity<String> {

        log.error { ex.stackTraceToString() }
        val status =
            if (ex is ResponseStatusException) { ex.statusCode } else {
            AnnotationUtils.findAnnotation(ex.javaClass, ResponseStatus::class.java)?.value ?: HttpStatus.BAD_REQUEST
        }

        val output: TemplateOutput = StringOutput()
        templateEngine.render("component/errorAlert.jte", mapOf("msg" to ex.message), output)
        return ResponseEntity(output.toString(), status)
    }


}
