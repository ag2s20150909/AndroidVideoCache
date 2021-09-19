package me.ag2s.demo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.MediaController;

import com.danikula.videocache.HttpProxyCacheServer;

import me.ag2s.demo.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.videoView.setMediaController(new MediaController(this));
        binding.play.setOnClickListener(v -> {
            try{
                HttpProxyCacheServer proxy=APP.getProxy(MainActivity.this);
                String proxyUrl = proxy.getProxyUrl("https://storage.googleapis.com/exoplayer-test-media-1/mkv/android-screens-lavf-56.36.100-aac-avc-main-1280x720.mkv");
                Log.e("GG",proxyUrl);
                if(binding.videoView.isPlaying()){
                    binding.videoView.pause();
                }
                binding.videoView.setVideoPath(proxyUrl);
                binding.videoView.start();
            }catch (Exception e){
                Log.e("eee",e.getMessage(),e);

                e.printStackTrace();
            }
        });


    }
}