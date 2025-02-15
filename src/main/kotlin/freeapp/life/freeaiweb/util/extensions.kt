package freeapp.life.freeaiweb.util

import com.linecorp.kotlinjdsl.querymodel.jpql.select.SelectQuery
import com.linecorp.kotlinjdsl.render.jpql.JpqlRenderContext
import com.linecorp.kotlinjdsl.render.jpql.JpqlRendered
import com.linecorp.kotlinjdsl.render.jpql.JpqlRenderer
import jakarta.persistence.EntityManager
import jakarta.persistence.NoResultException
import org.springframework.data.domain.Pageable
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

fun <T : Any> EntityManager.getSingleResultOrNull(
    render: JpqlRendered,
    classType: Class<T>
): T? {

    val typedQuery = this.createQuery(render.query, classType).apply {
        render.params.forEach { name, value ->
            setParameter(name, value)
        }
    }

    return try {
        typedQuery.singleResult
    } catch (e: NoResultException) {
        null
    }

}


inline fun <reified T : Any> EntityManager.getResult(
    render: JpqlRendered,
    classType: Class<T>
): MutableList<T> {

    val typedQuery = this.createQuery(render.query, classType).apply {
        render.params.forEach { name, value ->
            setParameter(name, value)
        }
    }
    return typedQuery.resultList
}



inline fun <T : Any> EntityManager.getCountByQuery(
    render: JpqlRendered,
    type: Class<T>,
): Long {

    val fetch = this.createQuery(render.query, type).apply {
        render.params.forEach { name, value ->
            setParameter(name, value)
        }
    }

    return fetch.resultList.size.toLong()
}


inline fun <reified T : Any> EntityManager.getResultWithPagination(
    render: JpqlRendered,
    type: Class<T>,
    pageable: Pageable
): MutableList<T> {

    val fetch = this.createQuery(render.query, type).apply {
        render.params.forEach { name, value ->
            setParameter(name, value)
        }
    }

    fetch.firstResult = pageable.offset.toInt()
    fetch.maxResults = pageable.pageSize

    return fetch.resultList
}



fun LocalDateTime.toString(pattern: String): String {
    val dateTimeFormatter =
        DateTimeFormatter.ofPattern(pattern)
    return dateTimeFormatter.format(this).toString()
}
