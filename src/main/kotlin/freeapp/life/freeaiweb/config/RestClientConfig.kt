package freeapp.life.freeaiweb.config


import mu.KotlinLogging
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.http.HttpRequest
import org.springframework.http.client.ClientHttpRequestExecution
import org.springframework.http.client.ClientHttpRequestInterceptor
import org.springframework.http.client.ClientHttpResponse
import org.springframework.http.client.JdkClientHttpRequestFactory
import org.springframework.web.client.ResponseErrorHandler
import org.springframework.web.client.RestClient
import org.springframework.web.reactive.function.client.*
import java.net.URI
import java.time.Duration
import java.util.concurrent.Executors


@Configuration
class RestClientConfig(

) {

    private val log = KotlinLogging.logger {  }

    private val ollamaUrl = "http://localhost:11434"

    @Bean
    fun ollamaClient(): RestClient {

        val requestFactory = getRequestFactory()

        return RestClient
            .builder()
            .baseUrl(ollamaUrl)
            .requestFactory(requestFactory)
            //.defaultStatusHandler(RestClientErrorHandler())
            .requestInterceptor(RestTemplateLoggingInterceptor())
            .build()

    }


    @Bean
    fun basicClient(): WebClient {

        val exchangeStrategies = ExchangeStrategies.builder()
            .codecs { configurer ->
                configurer.defaultCodecs().maxInMemorySize(-1)
            } // to unlimited memory size
            .build()

        return WebClient.builder()
            .exchangeStrategies(exchangeStrategies)
            .build()
    }





    private fun getRequestFactory(): JdkClientHttpRequestFactory {

        val requestFactory =
            JdkClientHttpRequestFactory(
                java.net.http.HttpClient.newBuilder().executor(Executors.newVirtualThreadPerTaskExecutor()).build()
            )

        requestFactory.setReadTimeout(Duration.ofSeconds(5))
        return requestFactory
    }


    class RestTemplateLoggingInterceptor : ClientHttpRequestInterceptor {

        val log = KotlinLogging.logger{}

        override fun intercept(request: HttpRequest, body: ByteArray, execution: ClientHttpRequestExecution): ClientHttpResponse {

            log.info {
                """
                    
            =====Request======
            Headers: ${request.headers}    
            Request Method:${request.method}
            Request URI: ${request.uri}            
            =====Request======
            """.trimIndent()
            }

            return execution.execute(request, body)
        }

    }


    class RestClientErrorHandler : ResponseErrorHandler {

        private val log = KotlinLogging.logger {}
        override fun hasError(response: ClientHttpResponse): Boolean {
            val statusCode = response.statusCode
            return !statusCode.is2xxSuccessful
        }
        override fun handleError(url: URI, method: HttpMethod, response: ClientHttpResponse) {
            super.handleError(url, method, response)
        }
    }

}
