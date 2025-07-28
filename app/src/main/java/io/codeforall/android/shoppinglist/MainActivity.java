package io.codeforall.android.shoppinglist;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import io.codeforall.android.shoppinglist.list.Item;
import io.codeforall.android.shoppinglist.list.ShoppingListAdapter;

public class MainActivity extends AppCompatActivity {

    private ActivityResultLauncher<Intent> formLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = setUpRecyclerView();

        setUpFormActivity(recyclerView);

    }

    private RecyclerView setUpRecyclerView() {
        ShoppingListAdapter adapter = new ShoppingListAdapter(Item.list(), this);

        RecyclerView recyclerView = findViewById(R.id.list);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        return recyclerView;
    }

    private void setUpFormActivity(RecyclerView recyclerView){
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
                        if (data != null && data.hasExtra(FormActivity.DESCRIPTION) && data.hasExtra(FormActivity.NAME)) {
                            String name = data.getStringExtra(FormActivity.NAME);
                            String description = data.getStringExtra(FormActivity.DESCRIPTION);
                            ShoppingListAdapter adapter = (ShoppingListAdapter) recyclerView.getAdapter();
                            assert adapter != null;
                            adapter.add(name, description);
                        }
                    }
                }
        );
    }
}