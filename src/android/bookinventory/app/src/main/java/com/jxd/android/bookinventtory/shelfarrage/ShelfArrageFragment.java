package com.jxd.android.bookinventtory.shelfarrage;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jxd.android.bookinventtory.R;
import com.jxd.android.bookinventtory.base.BaseFragment;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ShelfArrageFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ShelfArrageFragment extends BaseFragment {

    public ShelfArrageFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment ShelfArrageFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ShelfArrageFragment newInstance( ) {
        ShelfArrageFragment fragment = new ShelfArrageFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_shelf_arrage, container, false);
    }

    @Override
    public int getNavigateMenuId() {
        return R.id.navigation_shelfarrage;
    }

    @Override
    protected void fetchData() {

    }
}
