package kr.co.prnd

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
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
    private var fragmentName: String? = null
    var onInitializedListener: OnInitializedListener? = null
    private val fragmentManager: FragmentManager
    private val youTubePlayerAndroidxFragment = YouTubePlayerAndroidxFragment()

    init {

        LayoutInflater.from(context).inflate(R.layout.view_prnd_you_tube_player, this, true)

        attrs?.let {
            val typedArray =
                context.obtainStyledAttributes(attrs, R.styleable.YouTubePlayerView, defStyle, 0)
            videoId = typedArray.getString(R.styleable.YouTubePlayerView_videoId)
            fragmentName = typedArray.getString(R.styleable.YouTubePlayerView_fragment)
            typedArray.recycle()
        }

        fragmentManager = getFragmentManager()
        fragmentManager.beginTransaction()
            .replace(R.id.fragment_container, youTubePlayerAndroidxFragment)
            .commitAllowingStateLoss()

        if (videoId != null) {
            play(videoId!!)
        }
    }

    private fun getFragmentManager(): FragmentManager {
        val activityFragmentManager = getFragmentActivity().supportFragmentManager
        if (fragmentName == null) {
            return activityFragmentManager
        } else {
            for (fragment in activityFragmentManager.fragments) {
                if (fragment.javaClass.name == fragmentName) {
                    return fragment.childFragmentManager
                }
            }
            throw IllegalArgumentException("[$fragmentName] can not found. Please check your fragment name")
        }
    }

    private fun getFragmentActivity(): FragmentActivity {
        var targetContext = context
        var targetActivity: Activity? = null
        while (targetContext is ContextWrapper) {
            if (targetContext is Activity) {
                targetActivity = targetContext
                break
            }
            targetContext = targetContext.baseContext
        }
        return (targetActivity as? FragmentActivity)
            ?: throw IllegalArgumentException("You have to extend FragmentActivity or AppCompatActivity")
    }


    fun play(videoId: String, listener: OnInitializedListener? = null) {
        listener?.let { this.onInitializedListener = it }

        youTubePlayerAndroidxFragment.initialize(
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