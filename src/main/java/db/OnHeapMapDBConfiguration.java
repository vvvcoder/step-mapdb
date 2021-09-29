package db;

import org.mapdb.DB;
import org.mapdb.DBMaker;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class OnHeapMapDBConfiguration {
    /**
     * @param timeout ttl записи в бд, в миллисекундах
     * @param timeoutCheckPeriod интервал проверки timeout, в миллисекундах
     */
    public static DB getOnHeapMapDB(String integrationName, long timeout, long timeoutCheckPeriod) {
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(5);
        DB db = DBMaker.heapDB().make();
        db.hashMap(integrationName + ".exchange_state")
                .expireExecutor(scheduledExecutorService)
                .expireExecutorPeriod(timeoutCheckPeriod)
                .expireAfterCreate(timeout)
                .modificationListener(new MapDBModificationListener())
                .create();
        return db;
    }
}
