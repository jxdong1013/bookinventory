package com.jxd.android.bookinventtory.shelfarrage;

import com.jxd.android.bookinventtory.base.BaseApplication;
import com.jxd.android.bookinventtory.bean.ShelfBookScanBean;
import com.jxd.android.bookinventtory.bean.ShelfScanBean;
import com.jxd.android.bookinventtory.bean.UpdateInventory;
import com.jxd.android.bookinventtory.bean.UpdateInventoryBook;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/11/4.
 */
public class InventoryUpload {
    private List<ShelfScanBean> data;
    private int CountPerUplaod = 1;
    IShelfArragePresenter iShelfArragePresenter;
    int userId = 0;
    String userName;
    IShelfArrageView iShelfArrageView;

    public InventoryUpload(List<ShelfScanBean> data, IShelfArragePresenter iShelfArragePresenter , IShelfArrageView iShelfArrageView) {
        this.data = data;
        this.iShelfArragePresenter = iShelfArragePresenter;
        this.iShelfArrageView =iShelfArrageView;
        this.userId = BaseApplication.single.getUserBean() == null ? 0 : BaseApplication.single.getUserBean().getUserid();
        this.userName = BaseApplication.single.getUserBean() == null ? "" : BaseApplication.single.getUserBean().getUsername();
    }

    public void upload(int uploadIndex) {
        if (data == null || data.size() < 1) return;
        int totalCount = data.size();
        int totalUpdateCount = totalCount / CountPerUplaod + (totalCount % CountPerUplaod == 0 ? 0 : 1);

        if (uploadIndex < totalUpdateCount) {
            List<UpdateInventory> data = getUploadData(uploadIndex , totalCount);
            iShelfArragePresenter.upload( uploadIndex , data, userId, userName);
        } else {
            iShelfArrageView.uploadFinishCallback();
        }
    }

    private List<UpdateInventory> getUploadData(int pageIndex, int totalCount) {
        List<UpdateInventory> updateData = new ArrayList<>();
        for (int k = pageIndex * CountPerUplaod; k < totalCount && k < (pageIndex + 1) * CountPerUplaod; k++) {
            ShelfScanBean item = data.get(k);
            UpdateInventory shelf = new UpdateInventory();
            shelf.setShelfno(item.getShelfno());
            List<UpdateInventoryBook> updateBooks = new ArrayList<>();
            shelf.setBooks(updateBooks);
            for (ShelfBookScanBean book : item.getBooks()) {
                UpdateInventoryBook updateBook = new UpdateInventoryBook();
                updateBook.setBarcode(book.getBarcode());
                updateBook.setScanStatus(book.getScanStatus());
                updateBooks.add(updateBook);
            }

            updateData.add(shelf);
        }
        return updateData;
    }

}
