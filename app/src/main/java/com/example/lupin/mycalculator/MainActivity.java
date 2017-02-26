package com.example.lupin.mycalculator;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.myapplication.R;

public class MainActivity extends AppCompatActivity {
    private TextView result;
    private EditText kg;
    private RadioGroup radioGroup;
    private RadioButton woman;
    private RadioButton man;
    private Button calculator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //从xml布局文件获取UI控件
        setContentView(R.layout.activity_main);
        result = (TextView) findViewById(R.id.result);
        kg = (EditText) findViewById(R.id.kg);
        radioGroup = (RadioGroup) findViewById(R.id.sex);
        man = (RadioButton) findViewById(R.id.man);
        woman = (RadioButton) findViewById(R.id.woman);
        calculator = (Button) findViewById(R.id.calculator);

    }

    @Override
    protected void onStart() {
        super.onStart();
        registerEvent();
    }

    protected void registerEvent() {
        calculator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //判断体重是否填写
                if (!kg.getText().toString().trim().equals("")) {
                    Double weight = Double.parseDouble(kg.getText().toString());
                    StringBuffer strResult = new StringBuffer("---评估结果---\n");
                    //判断性别
                    if (man.isChecked()) {
                        double h = Height(weight, "男");
                        strResult.append("男性标准身高" + h + "厘米");
                    } else {
                        double h = Height(weight, "女");
                        strResult.append("女性标准身高" + h + "厘米");
                    }
                    result.setText(strResult);

                } else {
                    showMessage("请输入体重");
                    Log.d("tag", "tizhognAAAAAAAAAAAAAAAAAAAAAAAA");
                }
            }
        });
    }

    protected void showMessage(String str) {
        final AlertDialog alertDialog = new AlertDialog.Builder(this).create();
        alertDialog.setTitle("系统提示");
        alertDialog.setMessage(str);

        alertDialog.setButton(DialogInterface.BUTTON_POSITIVE, "确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                alertDialog.dismiss();
            }
        });
        alertDialog.show();

    }

    protected int Height(double weight, String sex) {
        double height = 0;
        if (sex.equals("男")) {
            height = 170 - (62 - weight) / 0.6;
        } else {
            height = 158 - (52 - weight) / 0.5;
        }
        return (int) height;
    }
}

