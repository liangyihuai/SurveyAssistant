package com.huai.assist.pojo;

/**
 * Created by liangyh on 11/12/16.
 */
public class Page<T> {

    private int pageSize = 10;
    private int currentPage;
    private int start; //the start num to search
    private int itemCount; //the total item count
    private int pageCount = 1;
    private T data;

    public int getItemCount() {
        return itemCount;
    }

    public void setItemCount(int itemCount) {
        this.itemCount = itemCount;
    }

    public int getPageCount() {
        if(pageSize == 0||itemCount == 0)return 1;
        if(itemCount%pageSize == 0){
            return itemCount/pageSize;
        }else{
            return itemCount/pageSize+1;
        }
    }

    public int getStart() {
        return getCurrentPage()*getPageSize();
    }

    public int getPageSize() {
        if(this.pageSize <= 0)return 10;
        return this.pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getCurrentPage() {
        if(this.currentPage <= 0)return 0;
        return this.currentPage-1;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
