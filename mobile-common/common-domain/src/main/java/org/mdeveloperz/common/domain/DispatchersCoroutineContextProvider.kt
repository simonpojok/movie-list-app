package com.swensonhe.quickupdate.common.domain

import org.mdeveloperz.common.domain.usecase.CoroutineContextProvider
import kotlinx.coroutines.Dispatchers
import kotlin.coroutines.CoroutineContext

class DispatchersCoroutineContextProvider :
    CoroutineContextProvider {
    override val main: CoroutineContext by lazy { Dispatchers.Main }
    override val io: CoroutineContext by lazy { Dispatchers.IO }
}
