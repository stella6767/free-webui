package freeapp.life.freeaiweb.config

import com.fasterxml.jackson.databind.ObjectMapper

import mu.KotlinLogging
import org.springframework.beans.factory.annotation.Value

import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer


@Configuration
class WebMvcConfig (
    private val mapper: ObjectMapper
) : WebMvcConfigurer {

    private val log = KotlinLogging.logger {  }

    @Value("\${spring.profiles.active:unknown}")
    private val profile: String? = null
    override fun addCorsMappings(registry: CorsRegistry) {
        registry.addMapping("/**")
            .exposedHeaders("HX-Push")
    }
}
