package com.example.btgkapptonghop;

import android.net.Uri;

import java.io.Serializable;

public class Item implements Serializable {


    private Uri linkimg;
    private int logo;
    private String hoTen;
    private String gioiThieu ;

    public Item() {
    }

    public Item(int logo, String hoTen, String gioiThieu) {
        this.logo = logo;
        this.hoTen = hoTen;
        this.gioiThieu = gioiThieu;
    }

    public Item(Uri linkimg, String hoTen, String gioiThieu) {
        this.linkimg = linkimg;
        this.hoTen = hoTen;
        this.gioiThieu = gioiThieu;
    }

    public int getLogo() {
        return logo;
    }

    public void setLogo(int logoTen) {
        this.logo = logoTen;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getGioiThieu() {
        return gioiThieu;
    }

    public void setGioiThieu(String gioiThieu) {
        this.gioiThieu = gioiThieu;
    }

    public Uri getLinkimg() {
        return linkimg;
    }

    public void setLinkimg(Uri linkimg) {
        this.linkimg = linkimg;
    }
}
