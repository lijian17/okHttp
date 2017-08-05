package net.dxs.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import net.dxs.okhttp.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        initView();
        initData();
    }

    private void initView() {
        ((Button) findViewById(R.id.btn_simpleOkHttp)).setOnClickListener(this);
        ((Button) findViewById(R.id.btn_encapsulationOkHttp)).setOnClickListener(this);
    }

    private void initData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_simpleOkHttp:
                startActivity(new Intent(this, SimpleOkHttpActivity.class));
                break;
            case R.id.btn_encapsulationOkHttp:
                startActivity(new Intent(this, EncapsulationOkHttpActivity.class));
                break;
            default:
                break;
        }
    }
}
