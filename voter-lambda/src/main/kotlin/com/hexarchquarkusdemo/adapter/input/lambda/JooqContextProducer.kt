package com.hexarchquarkusdemo.adapter.input.lambda


import io.agroal.api.AgroalDataSource
import org.jooq.DSLContext
import org.jooq.SQLDialect
import org.jooq.impl.DSL
import javax.enterprise.context.Dependent
import javax.enterprise.inject.Produces
import javax.inject.Inject


@Dependent
class JooqContextProducer {
    @Inject
    lateinit var dataSource: AgroalDataSource

    @Produces
    fun dslContext(): DSLContext {
        return DSL.using(dataSource, SQLDialect.POSTGRES)
    }
}