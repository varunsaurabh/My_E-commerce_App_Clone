package com.example.project2.Model;

import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class HomepageModel {
    public static final int BANNER_SLIDER = 0;
    public static final int STRIP_AD_BANNER = 1;
    public static final int HORIZONTAL_PRODUCT_VIEW = 2;
    public static final int GRID_PRODUCT_VIEW = 3;


    private int type;


    ////////////////////Banner slider
    private List<Slider> sliderModelList;

    public HomepageModel() {
    }
    public HomepageModel(int type, List<Slider> sliderModelList) {
        this.type = type;
        this.sliderModelList = sliderModelList;
    }
    public int getType() {
        return type;
    }
    public void setType(int type) {
        this.type = type;
    }
    public List<Slider> getSliderModelList() {
        return sliderModelList;
    }
    public void setSliderModelList(List<Slider> sliderModelList) {
        this.sliderModelList = sliderModelList;
    }
    ////////////////////Banner slider

    ////////////////////strip ad banner
    private String resource;
    private String backgroundColor;

    public HomepageModel(int type, String resource, String backgroundColor) {
        this.type = type;
        this.resource = resource;
        this.backgroundColor = backgroundColor;
    }
    public String getResource() {
        return resource;
    }
    public void setResource(String resource) {
        this.resource = resource;
    }
    public String getBackgroundColor() {
        return backgroundColor;
    }
    public void setBackgroundColor(String backgroundColor) {
        this.backgroundColor = backgroundColor;
    }
    ////////////////////strip ad banner

    //////////////////// Horizontal product view  &&  grid product view
    private String title;
    private List<HorizontalProductModel> horizontalProductModelList;
    private List<ShowAllProductRecyclerViewModel> showAllProductRecyclerViewModelList;

    ///////////////////////////////////////////// show all product view recycleView
    public HomepageModel(int type, String title, List<HorizontalProductModel> horizontalProductModelList
    ,List<ShowAllProductRecyclerViewModel> showAllProductRecyclerViewModelList) {
        this.type = type;
        this.title = title;
        this.horizontalProductModelList = horizontalProductModelList;
        this.showAllProductRecyclerViewModelList = showAllProductRecyclerViewModelList;
    }
    public List<ShowAllProductRecyclerViewModel> getShowAllProductRecyclerViewModelList() {
        return showAllProductRecyclerViewModelList;
    }
    public void setShowAllProductRecyclerViewModelList(List<ShowAllProductRecyclerViewModel> showAllProductRecyclerViewModelList) {
        this.showAllProductRecyclerViewModelList = showAllProductRecyclerViewModelList;
    }
    ///////////////////////////////////////////// show all product view recycleView

    ///////////////////////////////////////////// grid product show view
    public HomepageModel(int type, String title, List<HorizontalProductModel> horizontalProductModelList) {
        this.type = type;
        this.title = title;
        this.horizontalProductModelList = horizontalProductModelList;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public List<HorizontalProductModel> getHorizontalProductModelList() {
        return horizontalProductModelList;
    }
    public void setHorizontalProductModelList(List<HorizontalProductModel> horizontalProductModelList) {
        this.horizontalProductModelList = horizontalProductModelList;
    }
    ///////////////////////////////////////////// grid product show view

    //////////////////// Horizontal product view  &&  grid product view

}
