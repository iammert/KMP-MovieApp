package player

import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

@Stable
class PlayerState{
    var isPlaying by mutableStateOf(false)
        internal set
    var currentTime: Long by mutableStateOf(0)
    var duration = 0L
}