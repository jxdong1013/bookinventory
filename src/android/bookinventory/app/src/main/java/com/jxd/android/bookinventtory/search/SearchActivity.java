package com.jxd.android.bookinventtory.search;

import android.content.Intent;
import android.database.MatrixCursor;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.widget.AppCompatAutoCompleteTextView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jxd.android.bookinventtory.R;
import com.jxd.android.bookinventtory.adapter.SearchKeyAdapter;
import com.jxd.android.bookinventtory.adapter.SearchKeySection;
import com.jxd.android.bookinventtory.base.BaseActivity;
import com.jxd.android.bookinventtory.base.BaseApplication;
import com.jxd.android.bookinventtory.bean.SearchKeyBean;
import com.jxd.android.bookinventtory.config.Constants;
import com.jxd.android.bookinventtory.utils.MatrixCursorUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.realm.RealmResults;
import io.realm.Sort;

/**
 * 搜索界面
 */
public class SearchActivity extends BaseActivity<ISearchPresenter>
    implements ISearchView , SearchView.OnQueryTextListener , SearchView.OnCloseListener , BaseQuickAdapter.OnItemChildClickListener {

    @BindView(R.id.searchView)
    SearchView searchView;
    @BindView(android.support.v7.appcompat.R.id.search_src_text)
    AppCompatAutoCompleteTextView searchInput;
    @BindView(R.id.searchbarKeyRecyclerView)
    RecyclerView recyclerView;

    SearchKeyAdapter searchKeyAdapter;
    List<SearchKeySection> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        unbinder = ButterKnife.bind(this);
        searchInput = searchView.findViewById(android.support.v7.appcompat.R.id.search_src_text);
        //searchInput.setTextColor(ContextCompat.getColor( this , R.color.white ));//设置字体颜色
        searchInput.setThreshold(1);//设置自动提示=1
        ViewGroup.LayoutParams layoutParams = searchInput.getLayoutParams();
        layoutParams.height = LinearLayout.LayoutParams.WRAP_CONTENT;
        searchInput.setLayoutParams(layoutParams);
        //searchInput.setHintTextColor( ContextCompat.getColor(this , R.color.white) );//设置提示字体颜色

        DaggerSearchComponent.builder()
                .appComponent( ((BaseApplication)this.getApplication()).getAppComponent() )
                .searchModule(new SearchModule(this))
                .build()
                .inject(this);

        searchView.setOnQueryTextListener( this );
        searchView.setOnCloseListener(this);
        searchView.setSubmitButtonEnabled(true);


        recyclerView.setLayoutManager( new StaggeredGridLayoutManager( 4 , StaggeredGridLayoutManager.VERTICAL));
        data = new ArrayList<>();
        searchKeyAdapter = new SearchKeyAdapter(data );
        recyclerView.setAdapter(searchKeyAdapter);

        searchKeyAdapter.setOnItemChildClickListener(this);

        iPresenter.getSearchKeysListAsync("");

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        searchView.clearFocus();
        //searchView.onActionViewCollapsed();
//        SearchKeyBean key = new SearchKeyBean();
//        key.setKey(query);
        SearchKeyBean key = existKey(query);

        iPresenter.addSearchKey( key );

        //finish();

        return true;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        //toast(newText);
        return false;
    }

    @Override
    public boolean onClose() {
        toast("onClose");
        return false;
    }

    /**
     *
     * @param key
     * @return
     */
    protected SearchKeyBean existKey(String key){
        if(data==null) {
            SearchKeyBean bean = new SearchKeyBean();
            bean.setKey(key);
            bean.setHot(1);
            return bean;
        }

        for(SearchKeySection bean : data){
            if( !bean.isHeader && bean.t !=null && bean.t.getKey().equals( key ) ) {
                SearchKeyBean newBean = new SearchKeyBean();
                newBean.setKey(key);
                newBean.setHot( bean.t.getHot()+1 );
                return  newBean;
            }
        }

        SearchKeyBean bean = new SearchKeyBean();
        bean.setKey(key);
        bean.setHot(1);
        return bean;
    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
        if(view.getId() == R.id.ivDelete){
            iPresenter.deleteSearchKey();
        }else if(view.getId()==R.id.search_item_key){
            SearchKeySection searchKeySection= (SearchKeySection)adapter.getItem(position);

            SearchKeyBean searchKeyBean =new SearchKeyBean();
            searchKeyBean.setKey( searchKeySection.t.getKey() );
            searchKeyBean.setHot( searchKeySection.t.getHot() + 1);

            iPresenter.addSearchKey( searchKeyBean );
        }
    }

    @Override
    public void showProgress() {
        super.showProgress();
    }

    @Override
    public void hideProgress() {
        super.hideProgress();
    }

    @Override
    public void onRefresh( List<SearchKeySection> data ) {
        this.data = data;
        searchKeyAdapter.setNewData(this.data);

        hideProgress();
    }

    @Override
    public void onClear() {
        data.clear();
        SearchKeySection bean=new SearchKeySection(true,"最近搜索");
        data.add(bean);
        searchKeyAdapter.setNewData(data);
    }


    @Override
    public void onAdded( SearchKeyBean searchKeyBean) {
        Intent intent = this.getIntent();
        intent.putExtra(Constants.Key_SearchKey, searchKeyBean);
        this.setResult(RESULT_OK , intent);
        this.finish();
    }


    @OnClick({R.id.tvBack})
    public void onClick(View v){
        if( v.getId() == R.id.tvBack){
            finish();
        }
    }
}
