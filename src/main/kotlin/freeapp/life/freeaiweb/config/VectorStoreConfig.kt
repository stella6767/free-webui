package freeapp.life.freeaiweb.config

import org.springframework.ai.embedding.EmbeddingModel
import org.springframework.ai.vectorstore.SimpleVectorStore
import org.springframework.ai.vectorstore.VectorStore
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration


@Configuration
class VectorStoreConfig(

) {
    @Bean
    fun simpleVectorStore(embeddingModel: EmbeddingModel): VectorStore {

        return SimpleVectorStore.builder(embeddingModel).build()
    }

}
