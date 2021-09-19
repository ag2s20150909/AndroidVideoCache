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
        binding.play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    HttpProxyCacheServer proxy=APP.getProxy(MainActivity.this);
                    String proxyUrl = proxy.getProxyUrl("https://9890.vod.myqcloud.com/9890_4e292f9a3dd011e6b4078980237cc3d3.f20.mp4");
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
            }
        });


    }
}