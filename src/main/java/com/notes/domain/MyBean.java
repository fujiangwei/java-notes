package com.notes.domain;

/**
 * 文件描述
 **/
public class MyBean {

    private Double bidRatio;
    private Integer beginBidDate;
//    private Integer beginBidDate = 0;

    public Double getBidRatio(){
        return this.bidRatio;
    }
    public void setBidRatio( Double bidRatio ){
        this.bidRatio = bidRatio == null?0.0 : bidRatio;
    }

    public Integer getBeginBidDate(){
        return this.beginBidDate;
    }
    public void setBeginBidDate( Integer beginBidDate ){
        this.beginBidDate = beginBidDate == null? 0 : beginBidDate;
    }

    @Override
    public String toString() {
        return "getBidRatio = " + this.getBidRatio() + " &bidRatio = " + bidRatio +
                " \ngetBeginBidDate = " + this.getBeginBidDate() + " &beginBidDate = " + beginBidDate;
    }
}