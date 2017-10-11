package com.jxd.android.bookinventtory.utils;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;
import com.jxd.android.bookinventtory.R;
import com.jxd.android.bookinventtory.base.BaseApplication;

public class ToastUtils{
    private static Toast toast;

    public static void showShortToast(String msg){
        showToast(msg, Toast.LENGTH_SHORT);
    }

    private static void showToast(String msg, int duration){
        if(toast==null) {
            toast = Toast.makeText(BaseApplication.single, msg , duration);
        }
        toast.setDuration(duration);
        toast.setText(msg);
        toast.show();
    }

    public static void showLongToast(String msg){
        showToast(msg, Toast.LENGTH_LONG);
    }

    public static void showToast(Context context , String msg , int resId , int duration){
        Toast t =  Toast.makeText(BaseApplication.single, msg , duration);
        t.setGravity(Gravity.CENTER ,0,0);
        View v = new View(context );
        v.setBackgroundResource(resId);
        t.setView(v);
        t.setText(msg);
        t.show();
    }

//    public static void showSign( String msg){
//        Toast t =  Toast.makeText(BaseApplication.single, msg , Toast.LENGTH_LONG);
//        t.setGravity(Gravity.CENTER ,0,0);
//        LinearLayout toastView = (LinearLayout) t.getView();
//        ImageView imageCodeProject = new ImageView(BaseApplication.single);
//        imageCodeProject.setImageResource(R.mipmap.ic_sign);
//        toastView.addView(imageCodeProject, 0);
//        t.setText(msg);
//        t.show();
//    }
}
