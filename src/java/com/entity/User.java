package com.entity;
// Generated 08.Oca.2015 15:39:20 by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;

/**
 * User generated by hbm2java
 */
public class User  implements java.io.Serializable {


     private Integer uid;
     private String ad;
     private String soyad;
     private String sifre;
     private Set<Blog> blogs = new HashSet<Blog>(0);

    public User() {
    }

	
    public User(String ad, String soyad, String sifre) {
        this.ad = ad;
        this.soyad = soyad;
        this.sifre = sifre;
    }
    public User(String ad, String soyad, String sifre, Set<Blog> blogs) {
       this.ad = ad;
       this.soyad = soyad;
       this.sifre = sifre;
       this.blogs = blogs;
    }
   
    public Integer getUid() {
        return this.uid;
    }
    
    public void setUid(Integer uid) {
        this.uid = uid;
    }
    public String getAd() {
        return this.ad;
    }
    
    public void setAd(String ad) {
        this.ad = ad;
    }
    public String getSoyad() {
        return this.soyad;
    }
    
    public void setSoyad(String soyad) {
        this.soyad = soyad;
    }
    public String getSifre() {
        return this.sifre;
    }
    
    public void setSifre(String sifre) {
        this.sifre = sifre;
    }
    public Set<Blog> getBlogs() {
        return this.blogs;
    }
    
    public void setBlogs(Set<Blog> blogs) {
        this.blogs = blogs;
    }




}


