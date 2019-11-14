package org.spring.springboot.util;

import java.util.concurrent.*;

public final class ThreadPoolUtil {

    private ThreadPoolUtil() {}

    private static final int queueSize = 2;
    private static final int corePoolSize = 1;
    private static final int maximumPoolSize = 1;
    private static final int keepAliveTime = 1;
    private static final BlockingQueue<Runnable> queue = new LinkedBlockingQueue<>(queueSize);

    private static final ThreadPoolExecutor executor = new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime, TimeUnit.SECONDS,
            new LinkedBlockingQueue<>(queue), (r, executor) -> {
                if (!executor.isShutdown()) {
                    try {
                        executor.getQueue().put(r);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });

    public static void executor(Runnable command) {
        executor.execute(command);
    }




















   // private static final int  aa= ((LinkedBlockingQueue) ThreadPoolUtil.queue).size();


//    new ThreadPoolExecutor(size, size, 0L, TimeUnit.MILLISECONDS, new SynchronousQueue<Runnable>(),
//                new ThreadPoolExecutor.CallerRunsPolicy())
   }


