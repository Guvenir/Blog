package com.entity;
// Generated 08.Oca.2015 15:39:20 by Hibernate Tools 4.3.1


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Blog generated by hbm2java
 */
public class Blog  implements java.io.Serializable {


     private Integer bid;
     private User user;
     private String blogAd;
     private String yaziAd;
     private String konu;
     private Integer shows;
     private Date tarih;
     private Integer yorum;
     private Set<Blogetiket> blogetikets = new HashSet<Blogetiket>(0);

    public Blog() {
    }

	
    public Blog(String blogAd, String yaziAd, String konu) {
        this.blogAd = blogAd;
        this.yaziAd = yaziAd;
        this.konu = konu;
    }
    public Blog(User user, String blogAd, String yaziAd, String konu, Integer shows, Date tarih, Integer yorum, Set<Blogetiket> blogetikets) {
       this.user = user;
       this.blogAd = blogAd;
       this.yaziAd = yaziAd;
       this.konu = konu;
       this.shows = shows;
       this.tarih = tarih;
       this.yorum = yorum;
       this.blogetikets = blogetikets;
    }
   
    public Integer getBid() {
        return this.bid;
    }
    
    public void setBid(Integer bid) {
        this.bid = bid;
    }
    public User getUser() {
        return this.user;
    }
    
    public void setUser(User user) {
        this.user = user;
    }
    public String getBlogAd() {
        return this.blogAd;
    }
    
    public void setBlogAd(String blogAd) {
        this.blogAd = blogAd;
    }
    public String getYaziAd() {
        return this.yaziAd;
    }
    
    public void setYaziAd(String yaziAd) {
        this.yaziAd = yaziAd;
    }
    public String getKonu() {
        return this.konu;
    }
    
    public void setKonu(String konu) {
        this.konu = konu;
    }
    public Integer getShows() {
        return this.shows;
    }
    
    public void setShows(Integer shows) {
        this.shows = shows;
    }
    public Date getTarih() {
        return this.tarih;
    }
    
    public void setTarih(Date tarih) {
        this.tarih = tarih;
    }
    public Integer getYorum() {
        return this.yorum;
    }
    
    public void setYorum(Integer yorum) {
        this.yorum = yorum;
    }
    public Set<Blogetiket> getBlogetikets() {
        return this.blogetikets;
    }
    
    public void setBlogetikets(Set<Blogetiket> blogetikets) {
        this.blogetikets = blogetikets;
    }




}


