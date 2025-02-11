package freeapp.life.freeaiweb.config

import com.linecorp.kotlinjdsl.render.jpql.JpqlRenderContext
import com.linecorp.kotlinjdsl.render.jpql.JpqlRenderer
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.jpa.repository.config.EnableJpaAuditing
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.transaction.annotation.EnableTransactionManagement


@Configuration
@EnableJpaAuditing
class JdslConfig {

    @Bean
    fun jpqlRenderContext(): JpqlRenderContext {
        return JpqlRenderContext()
    }
    @Bean
    fun jpqlRenderer(): JpqlRenderer {
        return JpqlRenderer()
    }


}
