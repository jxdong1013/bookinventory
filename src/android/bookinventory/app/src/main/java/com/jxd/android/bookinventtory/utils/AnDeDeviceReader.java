package com.jxd.android.bookinventtory.utils;

import com.rfid.api.ADReaderInterface;
import com.rfid.api.BluetoothCfg;
import com.rfid.api.ISO15693Interface;
import com.rfid.api.ISO15693Tag;
import com.rfid.def.ApiErrDefinition;
import com.rfid.def.RfidDef;

import java.util.List;

/**
 * 安的手持读写设备，操作类
 * Created by jinxiangdong on 2017/10/22.
 */

public class AnDeDeviceReader {


    public enum ErrorCodeEnum{
        SUCCESS(0,"SUCCESS");

        private int code;
        private String message;

        public int getCode() {
            return code;
        }

        public String getMessage() {
            return message;
        }

        private ErrorCodeEnum(int code , String message){
            this.code=code;
            this.message=message;
        }

    }



    ADReaderInterface adReaderInterface;

    public AnDeDeviceReader(ADReaderInterface adReaderInterface){
        this.adReaderInterface=adReaderInterface;
    }


    /**
     * 获取已经配对的蓝牙设备列表
     * 如果成功，蓝牙设备列表；失败时返回null
     * 对BluetoothCfg实例，可以通过String GetName()和String GetAddr()方法获取蓝牙设备的名称和地址。
     */
    public List<BluetoothCfg> getBlueToothDevices(){
        List<BluetoothCfg> bluetoothList = ADReaderInterface.GetPairBluetooth();
        return bluetoothList;
    }

    /**
     * 获取设备的天线数量
     * @return
     */
    public int getAntennaInterfaceCount(){
        return adReaderInterface.RDR_GetAntennaInterfaceCount();
    }

    /**
     *
     * @return
     */
    public boolean isOpened(){
        return adReaderInterface.isReaderOpen();
    }

    /**
     * 采用蓝牙方式连接RPAN设备
     * @param blueDeviceName
     * @return
     */
    public int openDeviceByBlueTooth(String blueDeviceName ){
        String conStr = String.format("RDType=RPAN;CommType=BLUETOOTH;Name=%s" , blueDeviceName);

        int result = adReaderInterface.RDR_Open(conStr);
        return result;
    }

    /**
     * 采用串口连接M201设备
     * @return
     */
    public int openDeviceByM201(){
        String conString = "RDType=M201;CommType=COM;ComPath=/dev/ttyMT1;Baund=38400;Frame=8E1;Addr=255";

        int result= adReaderInterface.RDR_Open(conString);
        return result;
    }

    /**
     *
     * 关闭设备连接
     * @return
     */
    public int closeDevice(){
        if(adReaderInterface.isReaderOpen()){
            return adReaderInterface.RDR_Close();
        }
        return ErrorCodeEnum.SUCCESS.getCode();
    }

    /**
     * 检测是否支持 ISO15693空中协议操作类
     * @return
     */
    public boolean isSupport_ISO15693(){
        return adReaderInterface.RDR_IsAirProtocolSupport(RfidDef.RFID_APL_ISO15693_ID);
    }

    /**
     * 使与读卡器通信的API马上超时退出
     * @return
     */
    public int stopInventory(){
        return adReaderInterface.RDR_SetCommuImmeTimeout();
    }

    /**
     * 如果应用程序为使线程马上退出调用了API RDR_SetCommuImmeTimeout，
     * 那么在线程退出后马上调用该API，否则下一次命令操作有可能会产生-5的错误。
     * @return
     */
    public int reset(){
        return adReaderInterface.RDR_ResetCommuImmeTimeout();
    }

    /**
     * 创建空中接口协议参数列表数据节点
     * 如果成功，则返回数据节点句柄。
     */
    public Object createInventoryParamSpecList(){
        Object hInvenParamSpecList = ADReaderInterface.RDR_CreateInvenParamSpecList();
        return hInvenParamSpecList;
    }

    /**
     * 创建ISO15693_Inventory参数数据节点，用于RDR_TagInventory函数
     * @param hInvenParamSpecList
     * @return 成功时，节点内存。失败时，返回null
     */
    public Object iso15693_CreateInventoryParam(Object hInvenParamSpecList , boolean isMatchAFI , byte afi){
        return ISO15693Interface.ISO15693_CreateInvenParam(
                hInvenParamSpecList,//Inventory参数内存，由RDR_CreateInvenParamSpecList创建。
                (byte) 0,//天线ID号，0表示所有的天线接口都要读取ISO15693标准的标签，其他值为对应的天线口。
                isMatchAFI ,//是否匹配AFI
                afi,//匹配的AFI值
                (byte) 0//时隙类型。0：读卡器默认时隙;1：1个时隙;16：16个时隙
                );
    }

    /**
     * 寻找标签，该函数支持多天线接口和多空中接口协议 。找到标签读取到的信息以数据节点的形式保存起来，
     * inventory 完成后，应用程序通过RDR_GetTagDataReport获得所有标签的信息。
     * @param aiType
     * @param antennaIDs
     * @param timeout
     * @param inventoryParamSpecList
     * @return
     */
    public int tagInventory( byte aiType , byte[] antennaIDs, int timeout , Object inventoryParamSpecList){
        int result = adReaderInterface.RDR_TagInventory(aiType, antennaIDs, timeout, inventoryParamSpecList);
        return result;
    }

    /**
     * 获取缓冲区的标签记录。
     * @param seek
     * @return 1:第一条记录；2:下一条记录 3:最后一条记录
     */
    public Object getTagDataReport(byte seek ){
        Object tagReport = adReaderInterface.RDR_GetTagDataReport(seek);
        return tagReport;
    }

    /**
     *
     * @param tagReport
     * @return
     */
    public int parseTagDataReport( Object tagReport , ISO15693Tag iso15693Tag ){
        //iso15693Tag = new ISO15693Tag();
//        if(iso15693Tag==null){
//            return
//        }
        int result = ISO15693Interface.ISO15693_ParseTagDataReport( tagReport, iso15693Tag );
        //if (result == ApiErrDefinition.NO_ERROR)
        //{
            // ISO15693 TAG
            //tagList.add(ISO15693TagData);
            //tagReport = m_reader.RDR_GetTagDataReport(RfidDef.RFID_SEEK_NEXT);
        //}
        return result;

    }

}
