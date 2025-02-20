package freeapp.life.freeaiweb.config.filter


import jakarta.servlet.Filter
import jakarta.servlet.FilterChain
import jakarta.servlet.ServletRequest
import jakarta.servlet.ServletResponse
import mu.KotlinLogging
import org.slf4j.MDC
import java.util.*


class MDCLoggingFilter(

) : Filter {

    private val log = KotlinLogging.logger {  }
    override fun doFilter(request: ServletRequest, response: ServletResponse, chain: FilterChain) {
        //log.debug { "MDC가 제일 먼저 실행됨 " }
        val uuid = UUID.randomUUID()
        MDC.put("request_id", uuid.toString())
        response.characterEncoding = Charsets.UTF_8.name()
        chain.doFilter(request, response)
        MDC.clear()
    }


}
