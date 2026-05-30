package app.mafioso.utils

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

// https://github.com/Kotlin/kotlinx.coroutines/issues/2514#issuecomment-944078630
fun <T, M> StateFlow<T>.map(
    coroutineScope : CoroutineScope,
    mapper : (value : T) -> M
) : StateFlow<M> = map { mapper(it) }.stateIn(
    coroutineScope,
    SharingStarted.Eagerly,
    mapper(value)
)
