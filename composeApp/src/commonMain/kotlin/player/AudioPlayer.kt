package player

expect class AudioPlayer(playerState: PlayerState) {
    fun play()
    fun pause()
    fun setSongUrl(songUrl: String)
    fun cleanUp()
}