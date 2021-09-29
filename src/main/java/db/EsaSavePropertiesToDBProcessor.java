package db;

import org.mapdb.DB;
import org.mapdb.HTreeMap;

public class EsaSavePropertiesToDBProcessor {
    private final HTreeMap<String, Object> exchangeStateMap;

    public EsaSavePropertiesToDBProcessor(String integrationName, DB db) {
        this.exchangeStateMap = (HTreeMap<String, Object>) db.hashMap(integrationName + ".exchange_state").open();
    }

    public void process(String clientTransactionId, String exchangeState) {
        System.out.println(String.format("set=%s ; %s",clientTransactionId, exchangeState));
        this.exchangeStateMap.put(clientTransactionId, exchangeState);
    }
}