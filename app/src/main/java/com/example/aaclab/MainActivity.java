package com.example.aaclab;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.widget.Button;
import android.widget.Toast;

import com.example.aaclab.com.example.aaclab.db.BeverageEntity;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.aaclab.InsertActivity.ADD_DETAIL;
import static com.example.aaclab.InsertActivity.ADD_TITLE;
import static com.example.aaclab.ModifyActivity.MOD_DETAIL;
import static com.example.aaclab.ModifyActivity.MOD_ID;
import static com.example.aaclab.ModifyActivity.MOD_TITLE;

public class MainActivity extends AppCompatActivity {
    private static final int ADD_ACTIVITY = 1001;
    private static final int MOD_ACTIVITY = 1002;
    private BeverageViewModel mViewModel;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.addButton)
    Button addButton;
    @BindView(R.id.delAllButton)
    Button delAllButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initBeverageAdapter();
        addButton.setOnClickListener(v->addButtonCallback());
        delAllButton.setOnClickListener(v->delAllButtonCallback());
    }

    private void addButtonCallback() {
        Intent intent = new Intent(this, InsertActivity.class);
        startActivityForResult(intent,ADD_ACTIVITY);
    }

    private void delAllButtonCallback() {
        mViewModel.deleteAll();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode,
                                    @Nullable Intent data) {
        switch (requestCode) {
            case ADD_ACTIVITY:
                if (resultCode == RESULT_OK) {
                    Bundle bundle = data.getExtras();
                    String title = bundle.getString(ADD_TITLE);
                    String detail = bundle.getString(ADD_DETAIL);
                    BeverageEntity entity = new BeverageEntity(title, detail);
                    mViewModel.insert(entity);
                } else {
                    Toast.makeText(this, "not saved", Toast.LENGTH_LONG).show();
                }
                break;
            case MOD_ACTIVITY:
                if (resultCode == RESULT_OK) {
                    Bundle bundle = data.getExtras();
                    int id = bundle.getInt(MOD_ID);
                    String title = bundle.getString(MOD_TITLE);
                    String detail = bundle.getString(MOD_DETAIL);
                    BeverageEntity entity = new BeverageEntity(title, detail);
                    mViewModel.updateEntity(id, title, detail);
                } else {
                    Toast.makeText(this, "not saved", Toast.LENGTH_LONG).show();
                }
                break;
        }
    }

    private void initBeverageAdapter() {
        final BeverageViewAdapter adapter = new BeverageViewAdapter(this);
        recyclerView.setAdapter(adapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        DividerItemDecoration decoration = new DividerItemDecoration(this,
                layoutManager.getOrientation());
        recyclerView.addItemDecoration(decoration);
        mViewModel = ViewModelProviders.of(this).get(BeverageViewModel.class);
        mViewModel.getAllBeverages()
                .observe(this, beverageEntities -> adapter.setBeverages(beverageEntities));
        // hook delete action
        handleSingleDelete(recyclerView, adapter);
        // hook modify action
        handleSingleModify(recyclerView, adapter);
    }

    private void handleSingleDelete(RecyclerView recyclerView,
                                    BeverageViewAdapter adapter) {
        ItemTouchHelper helper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,
                ItemTouchHelper.LEFT|ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView,
                                  @NonNull RecyclerView.ViewHolder viewHolder,
                                  @NonNull RecyclerView.ViewHolder viewHolder1) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
                int position = viewHolder.getAdapterPosition();
                BeverageEntity entity = adapter.getBeverageAtPosition(position);
                mViewModel.delete(entity);
                Toast.makeText(MainActivity.this, String.format("delete, position=%d, i=%d",position,i),
                        Toast.LENGTH_LONG).show();
            }
        });
        helper.attachToRecyclerView(recyclerView);

    }

    private void handleSingleModify(RecyclerView recyclerView, BeverageViewAdapter adapter) {
        adapter.setmOnItemClickListener(v -> {
            RecyclerView.ViewHolder viewHolder = (RecyclerView.ViewHolder) v.getTag();
            int position = viewHolder.getAdapterPosition();
            //Toast.makeText(this, String.format("#%d is clicked", position),
            //        Toast.LENGTH_LONG).show();
            BeverageEntity entity = adapter.getBeverageAtPosition(position);
            Intent intent = new Intent(this, ModifyActivity.class);
            intent.putExtra(MOD_ID, entity.getId());
            intent.putExtra(MOD_TITLE, entity.getTitle());
            intent.putExtra(MOD_DETAIL, entity.getDetail());
            startActivityForResult(intent, MOD_ACTIVITY);
        });
    }
}