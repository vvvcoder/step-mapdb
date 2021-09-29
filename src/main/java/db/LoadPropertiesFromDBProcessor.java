package db;

import org.mapdb.DB;
import org.mapdb.HTreeMap;

import java.util.Map;

public class LoadPropertiesFromDBProcessor {
    private final HTreeMap<String, Object> exchangeStateMap;

    public LoadPropertiesFromDBProcessor(DB db, String integrationName) {
        this.exchangeStateMap = (HTreeMap<String, Object>) db.hashMap(integrationName + ".exchange_state").open();
    }

    public void process(String clientTransactionId) {
        String exchangeState = (String)this.exchangeStateMap.get(clientTransactionId);
        System.out.println("get = " + exchangeState);
//        if (exchangeState != null) {
//            this.exchangeStateMap.remove(clientTransactionId);
//        }
    }
}