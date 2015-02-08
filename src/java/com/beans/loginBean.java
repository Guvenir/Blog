package com.beans;

import com.controller.loginController;
import com.entity.Blog;
import com.util.Util;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import org.primefaces.event.RowEditEvent;

@ManagedBean
@SessionScoped
public class loginBean {

    private String ad;
    private String sifre;
    private String yad;
    private String ysifre;
    private String ysoyad;
    private loginController lc = new loginController();
    private boolean sonuc;
    private int id;
    //Yeni yazı için değişkenler
    private String blogAd;
    private String yaziAd;
    private String konu;
    private Integer shows;
    private String tarih;
    private Integer yorum;
    //
    private Blog blogg;

    public Blog getBlogg() {
        return blogg;
    }

    public void setBlogg(Blog blogg) {
        this.blogg = blogg;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBlogAd() {
        return blogAd;
    }

    public void setBlogAd(String blogAd) {
        this.blogAd = blogAd;
    }

    public String getYaziAd() {
        return yaziAd;
    }

    public void setYaziAd(String yaziAd) {
        this.yaziAd = yaziAd;
    }

    public String getKonu() {
        return konu;
    }

    public void setKonu(String konu) {
        this.konu = konu;
    }

    public Integer getShows() {
        return shows;
    }

    public void setShows(Integer shows) {
        this.shows = shows;
    }

    public String getTarih() {
        return tarih;
    }

    public void setTarih(String tarih) {
        this.tarih = tarih;
    }

    public Integer getYorum() {
        return yorum;
    }

    public void setYorum(Integer yorum) {
        this.yorum = yorum;
    }

    public boolean isSonuc() {
        return sonuc;
    }

    public void setSonuc(boolean sonuc) {
        this.sonuc = sonuc;
    }

    public String getYad() {
        return yad;
    }

    public void setYad(String yad) {
        this.yad = yad;
    }

    public String getYsifre() {
        return ysifre;
    }

    public void setYsifre(String ysifre) {
        this.ysifre = ysifre;
    }

    public String getYsoyad() {
        return ysoyad;
    }

    public void setYsoyad(String ysoyad) {
        this.ysoyad = ysoyad;
    }

    public String getAd() {
        return ad;
    }

    public void setAd(String ad) {
        this.ad = ad;
    }

    public String getSifre() {
        return sifre;
    }

    public void setSifre(String sifre) {
        this.sifre = sifre;
    }

    public loginBean() {
    }

    public String login() {

        sonuc = lc.login(this.ad, this.sifre);
        if (sonuc) {
            HttpSession hs = Util.getSession();
            hs.setAttribute("username", ad);
            return "anasayfa.xhtml";
        } else {
            return "login.xhtml";
        }

    }

    public String logout() {
        sonuc = false;
        HttpSession hs = Util.getSession();
        hs.invalidate();
        return "login.xhtml";
    }

    public void save() {
        boolean sonuc;
        sonuc = lc.saveUser(this.yad, this.ysoyad, this.ysifre);
    }

    public List<Blog> writes() {
        List<Blog> blog = new ArrayList<>();
        id = lc.userId(ad, sifre);
        blog = lc.blogGetir(id);
        return blog;
    }

    public void yaziKaydet() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        Date parseDate = sdf.parse(tarih);
        id = lc.userId(this.ad, this.sifre);
        lc.yeniYazi(this.blogAd, this.yaziAd, this.konu, this.shows, parseDate, this.yorum, id);
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage("Başarılı", "Kayıt İşleminiz Tamamlandı!"));

    }

    public void yaziSil() {
        int bid = blogg.getBid();
        lc.yaziSil(bid);
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage("Başarılı", "Silme İşleminiz Tamamlandı!"));
    }

    public void temizle() {
        this.blogAd = "";
        this.yaziAd = "";
        this.konu = "";
        this.shows = null;
        this.tarih = null;
        this.yorum = null;
    }

    public void onRowEdit(RowEditEvent event) throws ParseException {
        //sonuc = lc.yUpdate(this.blogg,this.tarih);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date parseDate = sdf.parse(tarih);
        sonuc = lc.yUpdate(blogAd, yaziAd, konu, shows, parseDate, yorum, blogg.getBid());
        if (sonuc) {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage("Başarılı", "Güncelleme İşleminiz Tamamlandı!"));
        } else {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage("Başarısız", "Güncelleme İşleminiz Tamamlanamadı!"));
        }
    }

    public void onRowCancel(RowEditEvent event) {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage("İptal", "Güncelleme İşleminiz İptal Edildi!"));
    }

}
