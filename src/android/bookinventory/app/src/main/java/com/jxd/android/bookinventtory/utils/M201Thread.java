package com.jxd.android.bookinventtory.utils;

import android.os.Handler;
import android.os.Message;

import com.jxd.android.bookinventtory.bean.InventoryEvent;
import com.rfid.api.ADReaderInterface;
import com.rfid.api.ISO15693Interface;
import com.rfid.api.ISO15693Tag;
import com.rfid.def.ApiErrDefinition;
import com.rfid.def.RfidDef;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

/**
 * m201 模式
 * Created by jinxiangdong on 2017/10/22.
 */

public class M201Thread extends Thread {
    public static boolean m201ThreadRun_Flag=false;
    public static Object lockObject=new Object();

    public static void setM201ThreadRun_Flag(boolean flag ){
        synchronized (lockObject){
            m201ThreadRun_Flag = flag;
        }
    }

    public static boolean getM201ThreadRun_Flag(){
        synchronized (lockObject){
            return m201ThreadRun_Flag;
        }
    }

    //打开设备失败的消息代码
    public static final int MSG_INVENTORY_OPENDEVICE_FAIL=300;
    //寻找标签失败的消息代码
    public static final int MSG_INVENTORY_FAIL =400;
    //寻找标签成功的消息代码
    public static final int MSG_INVENTORY_SUCCESS=200;
    //线程结束的消息代码
    public static final int MSG_INVENTORY_END = 600;

    AnDeDeviceReader anDeDeviceReader;
    //Handler handler;

    public M201Thread(AnDeDeviceReader anDeDeviceReader ) {
        this.anDeDeviceReader=anDeDeviceReader;
        //this.handler = handler;
    }

    @Override
    public void run() {
        super.run();
        if(!anDeDeviceReader.isOpened()){
            int result =anDeDeviceReader.openDeviceByM201();
            if(result != ApiErrDefinition.NO_ERROR) {
                EventBus.getDefault().post(new InventoryEvent( MSG_INVENTORY_OPENDEVICE_FAIL , result));
                return;
            }

        }

        setM201ThreadRun_Flag(true);

        int failedCount = 0;//操作失败次数
        int loopCount = 0;

        //byte aiType = RfidDef.AI_TYPE_NEW;
        byte aiType = RfidDef.AI_TYPE_CONTINUE;
        byte[] antennaIds = null;
        int timeout= 0;

        Object inventoryParamSpecList = anDeDeviceReader.createInventoryParamSpecList();
        boolean isMatchAFI= false;
        byte afi = 0;
        Object iso15693_Param = anDeDeviceReader.iso15693_CreateInventoryParam(inventoryParamSpecList , isMatchAFI , afi );

        while ( getM201ThreadRun_Flag() ){

            //if(handler.hasMessages(MSG_INVENTORY_SUCCESS)) continue;

            loopCount++;

            List<ISO15693Tag> tagList =new ArrayList<>();
            int result = anDeDeviceReader.tagInventory(aiType, antennaIds ,  timeout, inventoryParamSpecList);
            if (result == ApiErrDefinition.NO_ERROR || result == -ApiErrDefinition.ERR_STOPTRRIGOCUR){
                //寻找标签成功
                Object tagReport= anDeDeviceReader.getTagDataReport( RfidDef.RFID_SEEK_FIRST );

                while (tagReport != null){
                    ISO15693Tag iso15693Tag = new ISO15693Tag();
                    result = anDeDeviceReader.parseTagDataReport( tagReport , iso15693Tag );
                    if(result== ApiErrDefinition.NO_ERROR){
                        tagList.add(iso15693Tag);
                        tagReport = anDeDeviceReader.getTagDataReport(RfidDef.RFID_SEEK_NEXT);
                    }else{
                        tagReport=null;
                    }
                }

//                Message message = handler.obtainMessage(MSG_INVENTORY_SUCCESS);
//                message.obj = tagList;
//                message.arg1= loopCount;
//                message.arg2= failedCount;
//                message.sendToTarget();
                EventBus.getDefault().post(new InventoryEvent(tagList, MSG_INVENTORY_SUCCESS));

            }else{//寻找标签失败
                failedCount++;
//                Message message = handler.obtainMessage(MSG_INVENTORY_FAIL);
//                message.arg1=loopCount;
//                message.arg2=failedCount;
//                message.sendToTarget();

                EventBus.getDefault().post(new InventoryEvent(null, MSG_INVENTORY_FAIL));
            }
        }

        setM201ThreadRun_Flag(false);
        anDeDeviceReader.reset();

//        Message message=handler.obtainMessage(MSG_INVENTORY_END);
//        message.sendToTarget();

    }

}
