package com.example.aaclab;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ModifyActivity extends AppCompatActivity {
    public static final String MOD_TITLE = "mod.title";
    public static final String MOD_DETAIL = "mod.detail";
    public static final String MOD_ID = "mod.id";
    private int beverage_id;

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
        titleEditText.setText(getIntent().getStringExtra(MOD_TITLE));
        detailEditText.setText(getIntent().getStringExtra(MOD_DETAIL));
        beverage_id = getIntent().getIntExtra(MOD_ID, -9999);
        button.setOnClickListener(v -> doSubmit());
    }

    private void doSubmit() {
        Intent intent = new Intent();
        Bundle bundle = new Bundle();
        bundle.putString(MOD_TITLE, titleEditText.getText().toString());
        bundle.putString(MOD_DETAIL, detailEditText.getText().toString());
        bundle.putInt(MOD_ID, beverage_id);
        intent.putExtras(bundle);
        setResult(RESULT_OK, intent);
        finish();
    }
}