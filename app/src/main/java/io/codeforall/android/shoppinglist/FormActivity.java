package io.codeforall.android.shoppinglist;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class FormActivity extends AppCompatActivity {

    public static final String NAME = "Name";
    public static final String DESCRIPTION = "Description";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);

        setUpAddButton();
        setUpBackButton();
    }

    private void setUpAddButton() {
        Button addButton = findViewById(R.id.add_form_button);

        addButton.setOnClickListener(v -> {

            EditText nameInput = findViewById(R.id.name_input);
            EditText descriptionInput = findViewById(R.id.description_input);

            String nameStr = String.valueOf(nameInput.getText()).trim();
            String descStr = String.valueOf(descriptionInput.getText()).trim();

            Intent resultIntent = new Intent();
            resultIntent.putExtra(NAME, nameStr);
            resultIntent.putExtra(DESCRIPTION, descStr);

            setResult(RESULT_OK, resultIntent);
            finish();
        });
    }

    private void setUpBackButton() {
        Button backButton = findViewById(R.id.back_button);
        backButton.setOnClickListener(v -> {
            finish();
        });
    }
}