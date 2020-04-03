package com.avans.AvansMovieApp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.avans.AvansMovieApp.Model.GlobalVariables;
import com.avans.AvansMovieApp.Utilities.FetchingUtilities.CreateMovieList;

public class CreateListActivity extends AppCompatActivity {
    EditText mName;
    EditText mDescription;
    EditText mLanguage;
    Button mCreate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_list);

        //Set views
        this.mName = findViewById(R.id.et_create_list_name);
        this.mDescription = findViewById(R.id.et_create_list_description);
        this.mLanguage = findViewById(R.id.et_create_list_language);
        this.mCreate = findViewById(R.id.btn_create_list_button);

        this.mCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CreateMovieList createMovieList = new CreateMovieList();
                createMovieList.initialiseCreateMovieList(GlobalVariables.SESSION_TOKEN, mName.getText().toString(), mDescription.getText().toString(), mLanguage.getText().toString());
                finish();
            }
        });
    }
}
