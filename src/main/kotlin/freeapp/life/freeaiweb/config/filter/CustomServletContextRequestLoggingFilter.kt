package freeapp.life.freeaiweb.config.filter

import jakarta.servlet.http.HttpServletRequest
import org.springframework.web.filter.ServletContextRequestLoggingFilter

class CustomServletContextRequestLoggingFilter(


) : ServletContextRequestLoggingFilter() {

    init {
        isIncludeHeaders = true
        isIncludeClientInfo = true
        isIncludePayload = true
        isIncludeQueryString = true
        maxPayloadLength = 1000
    }

//    override fun shouldLog(request: HttpServletRequest): Boolean {
//        val userAgent = request.getHeader("User-Agent")
//        return !(userAgent.contains("ELB-HealthChecker") || userAgent.contains("Prometheus"))
//    }


    override fun createMessage(request: HttpServletRequest, prefix: String, suffix: String): String {

        val url = if (isIncludeQueryString){
            var requestURI = request.requestURI
            val queryString = request?.queryString
            if (queryString != null){
                requestURI += "?$queryString"
            }
            requestURI
        }else request.requestURI

        println("request.queryString=>${request.queryString}")

        val payload = if (isIncludePayload) {
            val payloadData = getMessagePayload(request)
            payloadData ?: ""
        }else ""

        return  """
            
            HTTP Method: ${request.method}
            url: ${url} 
            payload: $payload
                      
        """.trimIndent()
    }
}
