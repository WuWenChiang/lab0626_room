package com.example.aaclab;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.aaclab.InserActivity.ADD_DETAIL;
import static com.example.aaclab.InserActivity.ADD_TITLE;

public class MainActivity extends AppCompatActivity {
    private BeverageViewModel mViewModel;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.addButton)
    Button addButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initBeverageAdapter();
        addButton.setOnClickListener(v->addButtonCallback());
    }

    private static final int ADD_ACTIVITY = 1001;

    private void addButtonCallback() {
        Intent intent = new Intent(this,InserActivity.class);
        startActivityForResult(intent,ADD_ACTIVITY);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode,
                                    @Nullable Intent data) {
        switch (requestCode){
            case ADD_ACTIVITY:
                if(resultCode==RESULT_OK) {
                    Bundle bundle = data.getExtras();
                    String title = bundle.getString(ADD_TITLE);
                    String detail = bundle.getString(ADD_DETAIL);
                    BeverageEntity entity = new BeverageEntity(title, detail);
                    mViewModel.insert(entity);
                } else {
                    Toast.makeText(this, "not saved", Toast.LENGTH_LONG).show();
                }
                break;
        }
    }

    private void initBeverageAdapter() {
        final BeverageViewAdapter adapter = new BeverageViewAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mViewModel = ViewModelProviders.of(this).get(BeverageViewModel.class);
        mViewModel.getAllBeverages().observe(this,
                beverageEntities -> adapter.setBeverages(beverageEntities));
    }
}