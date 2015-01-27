package com.controller;

import com.entity.Blog;
import com.entity.User;
import com.util.HibernateUtil;
import com.util.Util;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.hibernate.Query;
import org.hibernate.Session;

public class loginController {

    private HibernateUtil hu;
    private Session ses;
    private List<User> user = new ArrayList<User>();
    private int uid;

    public loginController() {

    }

    public boolean login(String ad, String sifre) {

        ses = hu.getSessionFactory().openSession();
        ses.beginTransaction();
        Query q = ses.createQuery("from User u where u.ad=:ad and u.sifre=:sifre")
                .setParameter("ad", ad)
                .setParameter("sifre", sifre);
        user = q.list();
        ses.getTransaction().commit();
        ses.close();
        if (user.get(0).getSifre() == sifre) {
            //HttpSession ses = Util.getSession();
            //ses.setAttribute("username", user.get(0).getAd());
            return true;
        } else {
            return false;
        }
    }

    public boolean saveUser(String ad, String soyad, String sifre) {
        ses = hu.getSessionFactory().openSession();
        ses.beginTransaction();
        Query q = ses.createSQLQuery("insert into user(ad,soyad,sifre) values(:ad,:soyad,:sifre)")
                .setParameter("ad", ad)
                .setParameter("soyad", soyad)
                .setParameter("sifre", sifre);
        int i = q.executeUpdate();
        ses.getTransaction().commit();
        ses.close();
        return true;
    }

    public List<Blog> blogGetir(int id) {
        List<Blog> blog = new ArrayList<>();
        Blog blg = new Blog();
        ses = hu.getSessionFactory().openSession();
        ses.beginTransaction();
        Query q = ses.createQuery("from Blog b join b.user where b.user.uid=:uid")
                .setParameter("uid", id);
        Iterator iter = q.list().iterator();
        Object[] obje;
        while (iter.hasNext()) {
            obje = (Object[]) iter.next();
            blg = (Blog) obje[0];
            blog.add(blg);
        }

        ses.getTransaction().commit();
        ses.close();
        return blog;
    }

    public void yeniYazi(String blogAd, String yaziAd, String konu,
            Integer shows, Date tarih, Integer yorum, int id) {
        ses = hu.getSessionFactory().openSession();
        ses.beginTransaction();
        Query q = ses.createSQLQuery("insert into blog(uid,blog_ad,yazi_ad,konu,tarih,shows,yorum) "
                + "values(:uid,:blog_ad,:yazi_ad,:konu,:tarih,:shows,:yorum)")
                .setParameter("uid", id)
                .setParameter("blog_ad", blogAd)
                .setParameter("yazi_ad", yaziAd)
                .setParameter("konu", konu)
                .setParameter("tarih", tarih)
                .setParameter("shows", shows)
                .setParameter("yorum", yorum);
        int a = q.executeUpdate();
        ses.getTransaction().commit();
        ses.close();
    }

    public int userId(String ad, String sifre) {
        ses = hu.getSessionFactory().openSession();
        ses.beginTransaction();
        Query q = ses.createQuery("from User u where u.ad=:ad and u.sifre=:sifre")
                .setParameter("ad", ad)
                .setParameter("sifre", sifre);
        user = q.list();
        ses.getTransaction().commit();
        ses.close();
        uid = user.get(0).getUid();
        return uid;
    }

    public void yaziSil(int id) {
        ses = hu.getSessionFactory().openSession();
        try {
            ses.beginTransaction();
            Query q = ses.createQuery("delete from Blog b where b.bid=:bid")
                    .setParameter("bid", id);
            int a = q.executeUpdate();
            ses.getTransaction().commit();
        } catch (Exception e) {
            throw e;
        } finally {
            ses.close();
        }
    }

    public boolean yUpdate(String blogAd, String yaziAd, String konu,
            Integer shows, Date tarih, Integer yorum, int id)  throws ParseException {
        boolean sonuc;
        ses = hu.getSessionFactory().openSession();
        try {
            ses.beginTransaction();
            Query q = ses.createSQLQuery("update blog b set blog_ad=:blog_ad , "
                    + "yazi_ad=:yazi_ad , konu=:konu , shows=:shows , tarih=:tarih , yorum=:yorum where b.bid=:bid")
                    .setParameter("blog_ad", blogAd)
                    .setParameter("konu",konu)
                    .setParameter("shows", shows)
                    .setParameter("tarih",tarih)
                    .setParameter("yazi_ad",yaziAd)
                    .setParameter("yorum",yorum)
                    .setParameter("bid",id);
            int a = q.executeUpdate();
            ses.getTransaction().commit();
            sonuc = true;
        } catch (Exception e) {
            sonuc = false;
        } finally {
            ses.close();
        }
        return sonuc;
    }

}
