package org.mdeveloperz.common.remote.datasource.mapper.mapper

import org.mdeveloperz.common.datasource.DataMapperException

abstract class RemoteToDataMapper<INPUT : Any, OUTPUT : Any> {
    fun toData(input: INPUT): OUTPUT = try {
        map(input)
    } catch (throwable: Throwable) {
        throw DataMapperException("Could not map ${input::class.qualifiedName} to Data => ${throwable.message}", throwable)
    }

    protected abstract fun map(input: INPUT): OUTPUT
}
