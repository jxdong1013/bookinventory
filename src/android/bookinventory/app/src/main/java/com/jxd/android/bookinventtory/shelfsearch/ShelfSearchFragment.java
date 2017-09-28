package com.jxd.android.bookinventtory.shelfsearch;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jxd.android.bookinventtory.R;
import com.jxd.android.bookinventtory.base.BaseFragment;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ShelfSearchFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ShelfSearchFragment extends BaseFragment {

    public ShelfSearchFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment ShelfSearchFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ShelfSearchFragment newInstance() {
        ShelfSearchFragment fragment = new ShelfSearchFragment();
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
        return inflater.inflate(R.layout.fragment_shelf_search, container, false);
    }


    @Override
    public int getNavigateMenuId() {
        return R.id.navigation_shelfsearch;
    }
}
