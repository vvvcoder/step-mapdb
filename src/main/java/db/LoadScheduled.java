package db;

import org.mapdb.DB;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class LoadScheduled {
    private final ScheduledExecutorService scheduler;
    private final LoadPropertiesFromDBProcessor get;
    private final String clientTransactionId;

    public LoadScheduled(DB db, String integrationName, String clientTransactionId) {
        this.get = new LoadPropertiesFromDBProcessor(db, integrationName);
        this.clientTransactionId = clientTransactionId;
        this.scheduler = Executors.newScheduledThreadPool(1);
        this.scheduler.scheduleAtFixedRate(this::checkAll, 0, 1000, TimeUnit.MILLISECONDS);
        Runtime.getRuntime().addShutdownHook(new Thread(scheduler::shutdownNow));
    }

    private void checkAll() {
        get.process(clientTransactionId);
    }

    public void stop() {
        scheduler.shutdownNow();
    }
}
