package db;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.mapdb.MapModificationListener;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MapDBModificationListener implements MapModificationListener {

    private final ExecutorService executorService = Executors.newFixedThreadPool(5);

    public MapDBModificationListener() {
    }

    @Override
    public void modify(@NotNull Object key, @Nullable Object oldValue, @Nullable Object newValue, boolean triggered) {
        System.out.println("MapDBModificationListener");
        if (oldValue != null && newValue == null && triggered) {
            executorService.execute(() -> {
                System.out.println("MapDBModificationListener timeout del");
            });
        }
    }
}