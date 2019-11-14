 
# What is YouTubePlayerView?
- [YouTube Player API](https://developers.google.com/youtube/android/player/) is too old version
<br>: Never updated from 2015/10/12
- There are 3 problems with [YouTube Player API](https://developers.google.com/youtube/android/player/) library.

## Problem 1 - Jar File
- We should use [Jar library file](https://developers.google.com/youtube/android/player/downloads/YouTubeAndroidPlayerApi-1.2.2.zip), not like `implementation xxxx`
- That is old way and it makes difficult to manage the library

## Problem 2 - Fragment style
- If you want YouTube player with another view, you have to use `YouTubePlayerFragment`
(Or you have to extend `YouTubeBaseActivity`)
<br><br><img src="https://github.com/PRNDcompany/YouTubePlayerView/blob/master/arts/youtube_dialog.png" width="250"><br>
```kotlin
val youtubePlayerFragment = YouTubePlayerSupportFragment()
fragmentManager.beginTransaction()
    .replace(binding.youtubePlayerContainer.id, youtubePlayerFragment)
    .commitAllowingStateLoss()
youtubePlayerFragment.initialize(...)
```

## Problem 3 - Conflict Fragment with androidx
- Furthermore if you use `androidx` package, you can not use `YouTubePlayerFragment`.
- `androidx.fragment.app.FragmentManager` need `androidx.fragment.app.Fragment`, but `YouTubePlayerFragment` is `android.app.Fragment` 
- You can find many question about this issue
<br>: [Youtube player support fragment no longer working on Android studio 3.2 (androidx)](https://stackoverflow.com/questions/52577000/youtube-player-support-fragment-no-longer-working-on-android-studio-3-2-android)
<br>: [YoutubeAndroidPlayerAPI error after migrating to AndroidX in Android Studio](https://stackoverflow.com/questions/56798955/youtubeandroidplayerapi-error-after-migrating-to-androidx-in-android-studio)


# YouTubePlayerView is super easy YouTube Player View
        
## Setup
### Gradle
[ ![Download](https://api.bintray.com/packages/prnd/maven/youtube-player-view/images/download.svg) ](https://bintray.com/prnd/maven/youtube-player-view/_latestVersion)
```gradle
dependencies {
    implementation 'kr.co.prnd:youtube-player-view:x.x.x'
    //implementation 'kr.co.prnd:youtube-player-view:1.0.0'    
}

```

If you think this library is useful, please press star button at upside. 
<br/>
<img src="https://phaser.io/content/news/2015/09/10000-stars.png" width="200">
<br/><br/>



## How to use
- You can use 2 style
### XML style
```xml
 <kr.co.prnd.YouTubePlayerView
    android:id="@+id/you_tube_player_fragment_view"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    app:videoId="VIDEO_ID" />
```

### Dynamic code style
```xml
<kr.co.prnd.YouTubePlayerView
    android:id="@+id/you_tube_player_fragment_view"
    android:layout_width="match_parent"
    android:layout_height="wrap_content" />
```
```kotlin
val youTubePlayerView:YouTubePlayerView = findViewById(R.id.you_tube_player_fragment_view)
youTubePlayerView.play(VIDEO_ID)
```

## FAQ
### - Why don't you need a developer key?
- This is a very strange thing.
- When we use youtube player api, you can use any developer key without empty string
- So `YouTubePlayerView` set developer key itself
- [Check this code](https://github.com/PRNDcompany/YouTubePlayerView/blob/722a2451a5de5b7f9cd5944834f6fc3c77374ea8/youtube-player-view/src/main/java/kr/co/prnd/YouTubePlayerView.kt#L42)


## License 
 ```code
Copyright 2019 PRNDcompany

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
