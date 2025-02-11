package freeapp.life.freeaiweb.config

import com.p6spy.engine.logging.Category
import com.p6spy.engine.spy.P6SpyOptions
import com.p6spy.engine.spy.appender.MessageFormattingStrategy

import jakarta.annotation.PostConstruct
import mu.KotlinLogging
import org.hibernate.engine.jdbc.internal.FormatStyle
import org.springframework.context.annotation.Configuration
import java.text.SimpleDateFormat
import java.util.*


@Configuration
class LoggingConfig(

) {

    private val log = KotlinLogging.logger {  }


    @PostConstruct
    fun setLogMessgeFormat(){
        P6SpyOptions.getActiveInstance().logMessageFormat = P6spyPrettySqlFormatter::class.java.name
    }

    class P6spyPrettySqlFormatter(

    ) : MessageFormattingStrategy {

        /**
         *  'var' on function parameter is not allowed - Kotlin에서 함수 파라미터로 var을 허용하지 않는 이유 / https://thecommelier.tistory.com/10
         */

        override fun formatMessage(connectionId: Int, now: String?, elapsed: Long, category: String?, prepared: String?, sql: String?, url: String?): String {
            val newSql = formatSql(category!!, sql!!)
            val currentDate = Date()
            val format1 = SimpleDateFormat("yy.MM.dd HH:mm:ss")
            //return now + "|" + elapsed + "ms|" + category + "|connection " + connectionId + "|" + P6Util.singleLine(prepared) + sql;
            return format1.format(currentDate) + " | " + "OperationTime : " + elapsed + "ms" + newSql
        }


        private fun formatSql(category: String, sql: String): String? {

            if (sql.length > 4000) return null

            var formatSql: String = sql

            if (formatSql.trim { it <= ' ' } == "") return formatSql

            // Only format Statement, distinguish DDL And DML
            if (Category.STATEMENT.name == category) {
                val tmpsql = formatSql.trim { it <= ' ' }.lowercase()
                formatSql = if (tmpsql.startsWith("create") || tmpsql.startsWith("alter") || tmpsql.startsWith("comment")) {
                    FormatStyle.DDL.formatter.format(formatSql)
                } else {
                    FormatStyle.BASIC.formatter.format(formatSql)
                }
                formatSql = "|\nHeFormatSql(P6Spy sql,Hibernate format):$formatSql"
            }
            return formatSql
        }

    }


}
