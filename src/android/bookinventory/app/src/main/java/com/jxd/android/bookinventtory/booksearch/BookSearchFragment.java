package com.jxd.android.bookinventtory.booksearch;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jxd.android.bookinventtory.R;
import com.jxd.android.bookinventtory.adapter.BookSearchAdapter;
import com.jxd.android.bookinventtory.base.BaseFragment;
import com.jxd.android.bookinventtory.bean.BookBean;
import com.trello.rxlifecycle2.LifecycleTransformer;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

/**
 * 图书检索界面
 * Use the {@link BookSearchFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BookSearchFragment
        extends BaseFragment<IBookSearchPresenter>
        implements IBookSearchView {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    BookSearchAdapter bookSearchAdapter;
    List<BookBean> bookBeanList;


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
        View rootView=inflater.inflate(R.layout.fragment_book_search, container, false);

        unbinder = ButterKnife.bind(this , rootView );

        EventBus.getDefault().register(this);

        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();

        //EventBus.getDefault().register(this);

    }

    @Override
    public void onStop() {
        super.onStop();

        //EventBus.getDefault().unregister(this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        EventBus.getDefault().unregister(this);
    }

    @Override
    public int getNavigateMenuId() {
        return R.id.navigation_booksearch;
    }


    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void toast(String msg) {

    }

    @Override
    protected void fetchData() {
        //iPresenter.getBookList(  );
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void searchBook( BookBean condition ){
        toast("start search....");
        //// TODO: 2017/9/30
        iPresenter.getBookList(condition );
    }


    @Override
    public void callback(List<BookBean> data) {
        //bookBeanList.add( data );
        bookSearchAdapter.addData(data);
    }
}
