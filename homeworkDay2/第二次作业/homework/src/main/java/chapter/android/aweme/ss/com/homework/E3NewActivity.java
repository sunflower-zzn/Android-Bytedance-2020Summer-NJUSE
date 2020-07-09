package chapter.android.aweme.ss.com.homework;

import android.app.AppComponentFactory;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;

public class E3NewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chatroom);
        Intent intent = getIntent();
        int itemId = intent.getIntExtra("itemId",0);
        TextView text = (TextView) findViewById(R.id.tv_content_info);
        text.setText("当前是第 "+itemId+" 个item！");
    }
}
