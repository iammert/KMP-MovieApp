package player

import android.os.Handler
import android.os.Looper
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import androidx.media3.exoplayer.ExoPlayer
import org.example.movieapp.App

actual class AudioPlayer actual constructor(private val playerState: PlayerState) : Runnable  {

    private val handler = Handler(Looper.getMainLooper())

    private val mediaPlayer = ExoPlayer.Builder(App.requireInstance()).build()

    private val listener = object: Player.Listener{

        override fun onIsPlayingChanged(isPlaying: Boolean) {
            playerState.isPlaying = isPlaying
            if (isPlaying){
                handler.removeCallbacks(this@AudioPlayer)
                handler.postDelayed(this@AudioPlayer, 100)
            }else{
                handler.removeCallbacks(this@AudioPlayer)
            }
        }
    }

    actual fun play() {
        mediaPlayer.play()
    }

    actual fun pause() {
        mediaPlayer.pause()
    }

    actual fun setSongUrl(songUrl: String) {
        val mediaItem = MediaItem.fromUri(songUrl)
        mediaPlayer.addListener(listener)
        mediaPlayer.setMediaItem(mediaItem)
        mediaPlayer.prepare()
        mediaPlayer.playWhenReady = true
    }

    actual fun cleanUp() {
        mediaPlayer.release()
        handler.removeCallbacks(this@AudioPlayer)
    }

    override fun run() {
        playerState.currentTime = mediaPlayer.currentPosition / 1000
        handler.postDelayed(this, 1000)
    }
}