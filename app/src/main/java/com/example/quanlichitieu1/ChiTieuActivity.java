package com.example.quanlichitieu1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.example.quanlichitieu1.adapter.ContactAdapter;
import com.example.quanlichitieu1.data.DBManager;
import com.example.quanlichitieu1.model.ChiTieu;

import java.util.List;

public class ChiTieuActivity extends AppCompatActivity  {

    private Button btnBack;
    private ListView lvResult;

    private List<ChiTieu> chiTieuList;
    private ContactAdapter contactAdapter;
    public final DBManager dbManager = new DBManager(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tieu);
        Widget();
        chiTieuList = dbManager.getAllChiTieu();
        setAdapter();
        lvResult.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ChiTieu chitieu =chiTieuList.get(position);
                Intent intent = new Intent(ChiTieuActivity.this,DeleteUpdateActivity.class);
                intent.putExtra(DeleteUpdateActivity.ID,chitieu.getmID());
                intent.putExtra(DeleteUpdateActivity.TIEN,chitieu.getmTien());
                intent.putExtra(DeleteUpdateActivity.HANG_MUC,chitieu.getmHangMuc());
                intent.putExtra(DeleteUpdateActivity.VI_TRI_HANG_MUC,chitieu.getmViTriHangMuc());
                intent.putExtra(DeleteUpdateActivity.TIME,chitieu.getmTime());
                startActivity(intent);
                overridePendingTransition(R.anim.anim_enter_to_down,R.anim.anim_exit_to_down);
            }
        });
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(ChiTieuActivity.this,OptionActivity.class);
                startActivity(intent1);
                overridePendingTransition(R.anim.anim_enter_to_up,R.anim.anim_exit_to_up);
            }
        });
    }
    public void Widget(){
        lvResult = findViewById(R.id.lv_result);
        btnBack = findViewById(R.id.btn_back);
    }


    public void setAdapter(){
        if(contactAdapter==null){
            contactAdapter = new ContactAdapter(this,R.layout.item_listview,chiTieuList);
            lvResult.setAdapter(contactAdapter);
        }else{
            contactAdapter.notifyDataSetChanged();
            lvResult.setSelection(contactAdapter.getCount()-1);
        }
    }

}
