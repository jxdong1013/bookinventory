package com.jxd.android.bookinventtory.booksearch;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jxd.android.bookinventtory.R;
import com.jxd.android.bookinventtory.base.BaseFragment;

/**
 * 图书检索界面
 * Use the {@link BookSearchFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BookSearchFragment extends BaseFragment implements IBookSearchView{

    public BookSearchFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     * @return A new instance of fragment BookSearchFragment.
     */
    public static BookSearchFragment newInstance() {
        BookSearchFragment fragment = new BookSearchFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_book_search, container, false);
    }


    @Override
    public int getNavigateMenuId() {
        return R.id.navigation_booksearch;
    }
}
