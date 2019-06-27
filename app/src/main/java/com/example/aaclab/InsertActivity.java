package com.example.aaclab;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;

public class InsertActivity extends AppCompatActivity {
    public static final String ADD_TITLE = "beverage.add.title";
    public static final String ADD_DETAIL = "beverage.add.detail";

    @BindView(R.id.submitButton)
    Button button;
    @BindView(R.id.titleEditText)
    EditText titleEditText;
    @BindView(R.id.detailEditText)
    EditText detailEditText;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);
        ButterKnife.bind(this);
        button.setOnClickListener(v->addButtonCallback());
    }

    private void addButtonCallback() {
        Intent intent = new Intent();
        Bundle bundle = new Bundle();
        bundle.putString(ADD_TITLE, titleEditText.getText().toString());
        bundle.putString(ADD_DETAIL, detailEditText.getText().toString());
        intent.putExtras(bundle);
        setResult(RESULT_OK, intent);
        finish();
    }
}