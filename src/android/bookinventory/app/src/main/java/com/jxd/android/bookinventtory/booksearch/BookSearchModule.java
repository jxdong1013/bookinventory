package com.jxd.android.bookinventtory.booksearch;

import com.jxd.android.bookinventtory.http.ApiService;
import com.trello.rxlifecycle2.LifecycleTransformer;

import dagger.Module;
import dagger.Provides;

/**
 * Created by jinxiangdong on 2017/9/30.
 */
@Module
public class BookSearchModule {

    private BookSearchFragment bookSearchFragment;

    public BookSearchModule(BookSearchFragment bookSearchFragment){
        this.bookSearchFragment = bookSearchFragment;
    }

    @Provides
    public IBookSearchModel provideBookSearchModel(ApiService apiService ){
        return new BookSearchModel( apiService,  bookSearchFragment);
    }

}
