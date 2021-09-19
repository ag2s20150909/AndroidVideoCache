package com.danikula.videocache;

import androidx.annotation.Nullable;

import com.danikula.videocache.file.DiskUsage;
import com.danikula.videocache.file.FileNameGenerator;
import com.danikula.videocache.headers.HeaderInjector;
import com.danikula.videocache.sourcestorage.SourceInfoStorage;

import org.chromium.net.CronetEngine;

import java.io.File;

/**
 * Configuration for proxy cache.
 *
 * @author Alexey Danilov (danikula@gmail.com).
 */
class Config {

    public final File cacheRoot;
    public final FileNameGenerator fileNameGenerator;
    public final DiskUsage diskUsage;
    public final SourceInfoStorage sourceInfoStorage;
    public final HeaderInjector headerInjector;
    @Nullable
    public final CronetEngine cronetEngine;

    Config(File cacheRoot, FileNameGenerator fileNameGenerator, DiskUsage diskUsage, SourceInfoStorage sourceInfoStorage, HeaderInjector headerInjector,CronetEngine cronetEngine) {
        this.cacheRoot = cacheRoot;
        this.fileNameGenerator = fileNameGenerator;
        this.diskUsage = diskUsage;
        this.sourceInfoStorage = sourceInfoStorage;
        this.headerInjector = headerInjector;
        this.cronetEngine=cronetEngine;
    }

    File generateCacheFile(String url) {
        String name = fileNameGenerator.generate(url);
        return new File(cacheRoot, name);
    }

}
