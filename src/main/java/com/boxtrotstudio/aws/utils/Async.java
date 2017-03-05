package com.boxtrotstudio.aws.utils;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Async {
    private final ExecutorService EXECUTOR = Executors.newCachedThreadPool();

    public final Future runAsync(Runnable runnable) {
        return EXECUTOR.submit(runnable);
    }

    public final <T> Future<T> runAsync(Callable<T> callable) {
        return EXECUTOR.submit(callable);
    }

    public final <T> Future<T> runAsync(Runnable runnable, T value) {
        return EXECUTOR.submit(runnable, value);
    }
}
