import db.EsaSavePropertiesToDBProcessor;
import db.LoadScheduled;
import db.OnHeapMapDBConfiguration;
import org.mapdb.DB;

public class App {
    public static void main(String[] args) throws InterruptedException {
        String integrationName = "vitalik";
        DB db = OnHeapMapDBConfiguration.getOnHeapMapDB(integrationName, 5000, 250);
        EsaSavePropertiesToDBProcessor set = new EsaSavePropertiesToDBProcessor(integrationName, db);
        set.process("1", "2");
        LoadScheduled loadScheduled = new LoadScheduled(db, integrationName, "1");
        Thread.currentThread().join();
    }
}
