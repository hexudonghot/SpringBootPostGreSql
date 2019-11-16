package org.spring.springboot.util;

import java.util.concurrent.*;

public final class ThreadPoolUtil {

    private ThreadPoolUtil() {}

    private static final int queueSize = 4;
    private static final int corePoolSize = 2;
    private static final int maximumPoolSize = 2;
    private static final int keepAliveTime = 100000;
    private static final BlockingQueue<Runnable> queue = new LinkedBlockingQueue<Runnable>(queueSize);
    private static final ThreadPoolExecutor executor = new ThreadPoolExecutor(
            corePoolSize, maximumPoolSize, keepAliveTime, TimeUnit.SECONDS,
            queue, new ThreadPoolExecutor.CallerRunsPolicy());

    public static void executor(Runnable command) {
        executor.execute(command);
    }




















   // private static final int  aa= ((LinkedBlockingQueue) ThreadPoolUtil.queue).size();


//    new ThreadPoolExecutor(size, size, 0L, TimeUnit.MILLISECONDS, new SynchronousQueue<Runnable>(),
//                new ThreadPoolExecutor.CallerRunsPolicy())
   }


