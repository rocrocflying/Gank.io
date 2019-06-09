package com.rocflying.gankio.ui;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.rocflying.gankio.R;

/**
 * Created by liupeng on 2019/6/8.
 */

public class WaitProgressDialog extends ProgressDialog {

    private Context context;
    private String msg;

    public WaitProgressDialog(Context context) {
        super(context);
        this.context = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = View.inflate(context, R.layout.load_view, null);
        TextView textView = view.findViewById(R.id.tv_load_tips);
        textView.setText(msg);
        setContentView(view);
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

}
