package me.ag2s.demo;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;

import com.danikula.videocache.HttpProxyCacheServer;

import java.io.File;

public class APP extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        context=getApplicationContext();
        CronetClient.getInstance().getEngine();
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        context=getApplicationContext();
    }
    @SuppressLint("StaticFieldLeak")
    private static Context context;

    public static Context getContext() {
        return context;
    }

    private HttpProxyCacheServer proxy;

    public static HttpProxyCacheServer getProxy(Context context) {
        APP app = (APP) context.getApplicationContext();
        return app.proxy == null ? (app.proxy = app.newProxy()) : app.proxy;
    }


    private HttpProxyCacheServer newProxy() {
       return new  HttpProxyCacheServer.Builder(this)
               .cronetEngine(CronetClient.getInstance().getEngine())
               .cacheDirectory(new File(this.getExternalCacheDir(),"videoCache"))
               .maxCacheSize(1024 * 1024 * 1024)       // 1 Gb for cache
        .build();
//        return new HttpProxyCacheServer(this,CronetClient.getInstance().getEngine());
    }
}
