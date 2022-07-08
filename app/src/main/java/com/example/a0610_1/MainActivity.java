package com.example.a0610_1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    ImageView img_product_main;
    EditText edit_count;
    TextView txt_price, txt_delivery, txt_pay;
    CheckBox chk_agree;

    int val_price;
    int val_delivery = 2500;
    int val_pay = 0;
    int selected_price = 1500;
    int selected_count;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        img_product_main = findViewById(R.id.img_product_main);
        edit_count = findViewById(R.id.edit_count);
        txt_price = findViewById(R.id.txt_price);
        txt_delivery = findViewById(R.id.txt_delivery);
        txt_pay = findViewById(R.id.txt_pay);
        chk_agree = findViewById(R.id.chk_agree);

        findViewById(R.id.radio1).setOnClickListener(this);
        findViewById(R.id.radio2).setOnClickListener(this);
        findViewById(R.id.radio3).setOnClickListener(this);
        findViewById(R.id.btn_minus).setOnClickListener(this);
        findViewById(R.id.btn_pay).setOnClickListener(this);
        findViewById(R.id.btn_plus).setOnClickListener(this);

        sumTotal();
    }

    @Override
    public void onClick(View v) {
        int count=0;
        switch (v.getId()) {
            case R.id.radio1:
                img_product_main.setImageResource(R.drawable.product1);
                selected_price=1500;
                sumTotal();
                break;
            case R.id.radio2:
                img_product_main.setImageResource(R.drawable.product2);
                selected_price=2000;
                sumTotal();
                break;
            case R.id.radio3:
                img_product_main.setImageResource(R.drawable.product3);
                selected_price=3000;
                sumTotal();
                break;
            case R.id.btn_minus:
                count = Integer.parseInt(edit_count.getText().toString());
                if(count==1){
                    Toast.makeText(this, "최소수량은 1개입니다", Toast.LENGTH_SHORT).show();
                }else{
                    --count;
                    edit_count.setText(Integer.toString(count));
                }
                sumTotal();
                break;
            case R.id.btn_pay:
                if(chk_agree.isChecked()){
                    Intent intent = new Intent(MainActivity.this,SubActivity.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(this, "결제에 동의 해주세요", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.btn_plus:
                count = Integer.parseInt(edit_count.getText().toString());
                count++;
                edit_count.setText(Integer.toString(count));
                sumTotal();
                break;

        }
    }

    void sumTotal(){
        selected_count = Integer.parseInt(edit_count.getText().toString());

        val_price = selected_price * selected_count;

        if(val_price >= 10000)
            val_delivery =0;
        else
            val_delivery = 2500;

        val_pay = val_price + val_delivery;

        txt_price.setText(val_price + "원");
        txt_delivery.setText(val_delivery + "원");
        txt_pay.setText(val_pay + "원");
    }
}