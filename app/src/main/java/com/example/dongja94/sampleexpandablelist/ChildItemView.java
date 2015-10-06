package com.example.dongja94.sampleexpandablelist;

import android.content.Context;
import android.widget.FrameLayout;
import android.widget.TextView;

/**
 * Created by dongja94 on 2015-10-06.
 */
public class ChildItemView extends FrameLayout {
    public ChildItemView(Context context) {
        super(context);
        init();
    }
    TextView contentView;
    private void init() {
        inflate(getContext(), R.layout.view_child_item, this);
        contentView = (TextView)findViewById(R.id.text_content);
    }

    public void setChildItem(ChildItem item) {
        contentView.setText(item.child);
    }
}
