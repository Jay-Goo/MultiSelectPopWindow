package com.jaygoo.demo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.jaygoo.widget.MultiSelectPopWindow;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<String> names = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                names.add("高三5班");
                names.add("高三1班");
                names.add("高二5班");
                names.add("初中1790班");
                names.add("240101班");
                names.add("清华大学速成班");
                names.add("帅哥速成班");
                names.add("程序员速成0341班");
                names.add("扯蛋0班");

                new MultiSelectPopWindow.Builder(MainActivity.this)
                        .setNameArray(names)
                        .setConfirmListener(new MultiSelectPopWindow.OnConfirmClickListener() {
                            @Override
                            public void onClick(ArrayList<Integer> indexList, ArrayList<String> selectedList) {
                                Toast.makeText(getApplication(), indexList.size()+"", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setCancel("取消")
                        .setConfirm("完成")
                        .setTitle("班级列表")
                        .build()
                        .show(findViewById(R.id.mBottom));
            }
        });

    }
}
