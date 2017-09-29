package com.jxd.android.bookinventtory.utils;

import android.database.Cursor;
import android.database.MatrixCursor;

import com.jxd.android.bookinventtory.bean.SearchKeyBean;

import java.util.List;

import static android.os.Build.VERSION_CODES.O;

/**
 * Created by Administrator on 2017/9/29.
 */

public class MatrixCursorUtil {

    public static MatrixCursor createSearchKeyCursor(List<SearchKeyBean> keys){
        String[] columnNames= {"key"};
        MatrixCursor matrixCursor = new MatrixCursor( columnNames  );

        for(SearchKeyBean bean : keys) {
            matrixCursor.addRow( new Object[]{ bean.getKey() });
        }
        return  matrixCursor;
    }

}
