package com.jxd.android.bookinventtory.shelfarrage;

/**
 * Created by Administrator on 2017/11/4.
 */

public class UploadEvent {
    private int percent=0;
    private boolean finished=false;
    public UploadEvent(int percent , boolean finished){
        this.percent=percent;
        this.finished=finished;
    }

    public boolean isFinished() {
        return finished;
    }

    public int getPercent() {
        return percent;
    }
}
