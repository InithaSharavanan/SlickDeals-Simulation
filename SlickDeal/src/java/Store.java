/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author asini
 */
public class Store {
    private String storeId;
    private String storeName;
    private String storeDesc;
    private int offPerct;
    private String couponCode;

    public Store(String storeId, String storeName, String storeDesc, int offPerct, String couponCode) {
        this.storeId = storeId;
        this.storeName = storeName;
        this.storeDesc = storeDesc;
        this.offPerct = offPerct;
        this.couponCode = couponCode;
    }

    public String getStoreId() {
        return storeId;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getStoreDesc() {
        return storeDesc;
    }

    public void setStoreDesc(String storeDesc) {
        this.storeDesc = storeDesc;
    }

    public int getOffPerct() {
        return offPerct;
    }

    public void setOffPerct(int offPerct) {
        this.offPerct = offPerct;
    }

    public String getCouponCode() {
        return couponCode;
    }

    public void setCouponCode(String couponCode) {
        this.couponCode = couponCode;
    }
    
    
    
}
