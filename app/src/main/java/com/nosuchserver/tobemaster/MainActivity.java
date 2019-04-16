package com.nosuchserver.tobemaster;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.nosuchserver.tobemaster.base.activity.TestBaseActivity;
import com.nosuchserver.tobemaster.handlerlooper.HandlerLooperAct;

import java.nio.channels.GatheringByteChannel;

public class MainActivity extends TestBaseActivity {

    @Override
    protected void addViews(LinearLayout layout) {
        getButton(layout, "HandlerLooperAct", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // HandlerLooperAct
                HandlerLooperAct.start(mContext);
            }
        });
    }
}
