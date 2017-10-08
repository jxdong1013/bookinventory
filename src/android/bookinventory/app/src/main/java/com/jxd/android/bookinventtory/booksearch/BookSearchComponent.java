package com.jxd.android.bookinventtory.booksearch;

import com.jxd.android.bookinventtory.di.AppComponent;
import com.jxd.android.bookinventtory.mvp.PerFragment;

import dagger.Component;

/**
 * Created by jinxiangdong on 2017/9/30.
 */
@PerFragment
@Component(modules = {BookSearchModule.class}, dependencies = {AppComponent.class})
public interface BookSearchComponent {

    void inject(BookSearchFragment bookSearchFragment);

    IBookSearchModel getBookSearchModel();

}
