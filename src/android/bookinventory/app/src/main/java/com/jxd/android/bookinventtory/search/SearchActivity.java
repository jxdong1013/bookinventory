package com.jxd.android.bookinventtory.search;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatAutoCompleteTextView;

import com.jxd.android.bookinventtory.R;
import com.jxd.android.bookinventtory.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SearchActivity extends BaseActivity<ISearchPresenter>
    implements ISearchView{

    @BindView(R.id.searchView)
    android.support.v7.widget.SearchView searchView;
    @BindView(android.support.v7.appcompat.R.id.search_src_text)
    AppCompatAutoCompleteTextView searchInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        unbinder = ButterKnife.bind(this);
        searchInput = searchView.findViewById(android.support.v7.appcompat.R.id.search_src_text);
        searchInput.setTextColor(ContextCompat.getColor( this , R.color.white ));

    }
}
