package com.example.qltc.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.qltc.R;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerFragment;
import com.google.android.youtube.player.YouTubePlayerSupportFragment;
import com.google.android.youtube.player.YouTubePlayerView;

public class YoutubeActivity extends YouTubeBaseActivity implements YouTubePlayer.OnInitializedListener {


    public static final String DEVELOPER_KEY ="AIzaSyCtJKX0Av9ezPMfkD377iAV8QHBBMHm3rs";
    private static final String VIDEO_ID="FoJ-9XEe4Hw";
    private static final int RECOVERY_DIALOG_REQUEST = 1;
    YouTubePlayerFragment myYouTubePlayerFragment;

    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.fragment_youtube_direction);
        myYouTubePlayerFragment = (YouTubePlayerFragment) getFragmentManager().findFragmentById(R.id.viewYoutube);
        myYouTubePlayerFragment.initialize(DEVELOPER_KEY,this);
    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
        if(!b){
            youTubePlayer.cueVideo(VIDEO_ID);
        }
    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider,
                                        YouTubeInitializationResult youTubeInitializationResult) {
        if(youTubeInitializationResult.isUserRecoverableError()){
            youTubeInitializationResult.getErrorDialog(this,RECOVERY_DIALOG_REQUEST).show();

        }else{
            String errorMessage = String.format("There was an error initializing the YouTubePlayer (%1$s)"
                    ,youTubeInitializationResult.toString());
            Toast.makeText(this,errorMessage,Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == RECOVERY_DIALOG_REQUEST){
            getYouTubePlayerProvider().initialize(DEVELOPER_KEY,this);
        }
    }

    private YouTubePlayer.Provider getYouTubePlayerProvider() {
        return (YouTubePlayerView)findViewById(R.id.viewYoutube);
    }

}

