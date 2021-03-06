package com.renxl.rxjavaretrofitdagger2demo.dagger2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import com.renxl.rxjavaretrofitdagger2demo.R;
import com.renxl.rxjavaretrofitdagger2demo.dagger2.componet.Dagger2Component;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.OkHttpClient;

/**
 * Created by renxl
 * On 2017/4/10 20:50.
 */

public class Dagger2Activity extends AppCompatActivity {
    @BindView(R.id.btn_dagger)
    Button btnDagger;

    // 声明需要注入的对象
    @DaggerQualifier("A")
    @Inject
    User user;

    @Inject
    OkHttpClient okHttpClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dagger2);
        ButterKnife.bind(this);

        // 绑定注入的 Component，如果没这个步骤则不会成功注入
        Dagger2Component.getInstance().inject(this);

        // dagger2Module(new Dagger2Module()) // 如果 Component 中使用了 Module 并且 Module 的构造函数需要参数， 则必须添加 Module
    }

    @OnClick(R.id.btn_dagger)
    public void onViewClicked() {
        startActivity(new Intent(this, Dagger22Activity.class));
        user.doSomething("Dagger2 生效 USER" + user.toString() + "\n" + okHttpClient.toString());
    }
}
