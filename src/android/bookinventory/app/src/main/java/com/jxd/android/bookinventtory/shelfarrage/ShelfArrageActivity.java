package com.jxd.android.bookinventtory.shelfarrage;


import android.os.Bundle;

import com.jxd.android.bookinventtory.R;
import com.jxd.android.bookinventtory.base.BaseActivity;

public class ShelfArrageActivity extends BaseActivity<IShelfArragePresenter> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shelf_arrage);
    }
}
