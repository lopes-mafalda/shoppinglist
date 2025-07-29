package io.codeforall.android.shoppinglist;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.Button;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import io.codeforall.android.shoppinglist.loader.ItemLoader;
import io.codeforall.android.shoppinglist.persistence.DBHelper;
import io.codeforall.android.shoppinglist.persistence.model.Item;
import io.codeforall.android.shoppinglist.list.ShoppingListAdapter;
import io.codeforall.android.shoppinglist.service.ItemService;

public class MainActivity extends AppCompatActivity {

    private ActivityResultLauncher<Intent> formLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DBHelper dbHelper = new DBHelper(this);
        ItemService service = new ItemService(dbHelper);

        //database clearing and fake data:
        //service.clear();
        //service.seed();

        RecyclerView recyclerView = setUpRecyclerView(service);
        setUpFormActivity(recyclerView, service);

        ItemLoader itemLoader = new ItemLoader(new Handler(Looper.getMainLooper()));

        itemLoader.loadItems(service, (ShoppingListAdapter) recyclerView.getAdapter());
    }

    private RecyclerView setUpRecyclerView(ItemService service) {
        ShoppingListAdapter adapter = new ShoppingListAdapter(service);

        RecyclerView recyclerView = findViewById(R.id.list);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        return recyclerView;
    }

    private void setUpFormActivity(RecyclerView recyclerView, ItemService service) {
        Button addButton = findViewById(R.id.form_button);
        addButton.setOnClickListener(v -> {
            Intent intent = new Intent(this, FormActivity.class);
            formLauncher.launch(intent);
        });

        formLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {

                    if (result.getResultCode() == RESULT_OK) {
                        Intent data = result.getData();

                        if (data != null && data.hasExtra(FormActivity.QUANTITY) && data.hasExtra(FormActivity.NAME)) {
                            String name = data.getStringExtra(FormActivity.NAME);
                            Integer quantity = Integer.valueOf(data.getStringExtra(FormActivity.QUANTITY));

                            Item savedItem = service.save(new Item(name, quantity));

                            ShoppingListAdapter adapter = (ShoppingListAdapter) recyclerView.getAdapter();

                            assert adapter != null;
                            adapter.add(savedItem);
                        }
                    }
                }
        );
    }
}