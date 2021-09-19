package me.ag2s.demo;

import android.app.Application;
import android.content.Context;

import com.danikula.videocache.HttpProxyCacheServer;

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
        return new HttpProxyCacheServer(this,CronetClient.getInstance().getEngine());
    }
}
