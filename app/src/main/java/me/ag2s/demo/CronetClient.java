package me.ag2s.demo;

import static org.chromium.net.CronetEngine.Builder.HTTP_CACHE_DISK;

import android.content.Context;
import android.util.Log;

import org.chromium.net.ExperimentalCronetEngine;


import java.io.PrintWriter;
import java.io.StringWriter;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class CronetClient {

    public static final String NET_ERROR = "err";
    private static final String TAG = "CronetClient";

    private static volatile CronetClient instance = null;
    private final Context mContext;
    public static final ExecutorService executor = Executors.newCachedThreadPool();


    private CronetClient() {
        this.mContext = APP.getContext();
    }

    public static CronetClient getInstance() {

        if (instance == null) {
            synchronized (CronetClient.class) {
                if (instance == null) {
                    instance = new CronetClient();
                }
            }
        }
        return instance;
    }


    private ExperimentalCronetEngine engine;

    public synchronized ExperimentalCronetEngine getEngine() {
        if (engine == null) {
            if (mContext == null) {
                return null;
            }

            ExperimentalCronetEngine.Builder builder = new ExperimentalCronetEngine.Builder(mContext)
                    .setStoragePath(mContext.getExternalCacheDir().getAbsolutePath())
                    .enableHttpCache(HTTP_CACHE_DISK, 1024 * 1024 * 500)
                    .enableQuic(true)
                    .enablePublicKeyPinningBypassForLocalTrustAnchors(true)
                    .enableNetworkQualityEstimator(true)
                    .addQuicHint("doh3.dns.nextdns.io", 443, 443)
                    .enableHttp2(true)
                    .enableSdch(true)
                    .setThreadPriority(0);

            builder.enableBrotli(true);

            engine = builder.build();
            //URL.setURLStreamHandlerFactory(engine.createURLStreamHandlerFactory());
            Log.e(TAG, engine.getVersionString());
        }

        return engine;
    }


    public static String getStackTrace(Throwable throwable) {
        StringWriter sw = new StringWriter();

        try (PrintWriter pw = new PrintWriter(sw)) {
            throwable.printStackTrace(pw);
            return sw.toString();
        }
    }


}
