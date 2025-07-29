package io.codeforall.android.shoppinglist.loader;

import android.os.Handler;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import io.codeforall.android.shoppinglist.list.ShoppingListAdapter;
import io.codeforall.android.shoppinglist.persistence.model.Item;
import io.codeforall.android.shoppinglist.service.ItemService;

public class ItemLoader {

    private final ExecutorService executorService;
    private final Handler mainHandler;

    public ItemLoader(Handler mainHandler) {
        this.mainHandler = mainHandler;
        this.executorService = Executors.newSingleThreadExecutor();
    }

    public void loadItems(ItemService service, ShoppingListAdapter adapter) {

        // (Optional) Preparar estado inicial no UI thread (tipo "Loading...")
        mainHandler.post(() -> {
            // Podes mostrar uma progress bar ou limpar o adapter, se quiseres
        });

        executorService.execute(() -> {
            List<Item> items = service.getAll();

            // Atualiza a UI com os dados carregados
            mainHandler.post(() -> {
                adapter.setData(items);
            });
        });
    }

    public void stop() {
        executorService.shutdown();
    }
}
