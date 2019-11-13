package kr.co.prnd

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.fragment.app.FragmentActivity
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import com.google.android.youtube.player.YouTubePlayerAndroidxFragment
import kr.co.prnd.youtubeplayerandroidxfragment.R

class YouTubePlayerView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : FrameLayout(context, attrs, defStyle) {

    private var videoId: String? = null
    var onInitializedListener: OnInitializedListener? = null

    init {
        LayoutInflater.from(context).inflate(R.layout.view_prnd_you_tube_player, this, true)

        attrs?.let {
            val typedArray =
                context.obtainStyledAttributes(attrs, R.styleable.YouTubePlayerView, defStyle, 0)
            videoId = typedArray.getString(R.styleable.YouTubePlayerView_videoId)
            typedArray.recycle()
        }


        if (videoId != null) {
            play(videoId!!)
        }
    }

    fun play(videoId: String, listener: OnInitializedListener? = null) {
        listener?.let { this.onInitializedListener = it }

        getYouTubeFragment()?.initialize(
            javaClass.simpleName,
            object : YouTubePlayer.OnInitializedListener {
                override fun onInitializationSuccess(
                    provider: YouTubePlayer.Provider,
                    player: YouTubePlayer,
                    wasRestored: Boolean
                ) {
                    if (!wasRestored) {
                        player.cueVideo(videoId)
                    }

                    onInitializedListener?.onInitializationSuccess(provider, player, wasRestored)
                }

                override fun onInitializationFailure(
                    provider: YouTubePlayer.Provider,
                    result: YouTubeInitializationResult
                ) {
                    onInitializedListener?.onInitializationFailure(provider, result)
                }
            })
    }

    private fun getYouTubeFragment(): YouTubePlayerAndroidxFragment? {
        val fragmentManager = (context as? FragmentActivity)?.supportFragmentManager
        return fragmentManager?.findFragmentByTag(context.getString(R.string.prnd_youtube_player_view))
                as? YouTubePlayerAndroidxFragment
    }

    interface OnInitializedListener {
        fun onInitializationSuccess(
            provider: YouTubePlayer.Provider,
            player: YouTubePlayer,
            wasRestored: Boolean
        )

        fun onInitializationFailure(
            provider: YouTubePlayer.Provider,
            result: YouTubeInitializationResult
        )
    }
}