package com.kaankln.dao;

import com.kaankln.base.BaseDao;
import com.kaankln.dto.CommantCountsOfProductsDto;
import com.kaankln.dto.UrunDetayDto;
import com.kaankln.entity.Urun;
import org.hibernate.query.Query;

import java.math.BigDecimal;
import java.util.List;

public class UrunDao extends BaseDao {

    public List<Urun> findAll(){

        String sql = "select urun from Urun urun";

        Query query = getCurrentSession().createQuery(sql);

        return query.list();
    }

    public Urun findById(Long id){

        String sql = "select urun from Urun urun where urun.id = :givenId";

        Query query = getCurrentSession().createQuery(sql);
        query.setParameter("givenId", id);

        return (Urun) query.uniqueResult();
    }

    public List<Urun> findAllUrunListByFiyatGeAndFiyatLe(BigDecimal fiyatGe, BigDecimal fiyatLe){

        String sql = "select urun from Urun urun where 1=1 ";

        if (fiyatGe != null){
            sql = sql + " and urun.fiyat >= :fiyatGe ";
        }

        if (fiyatLe != null){
            sql = sql + " and urun.fiyat <= :fiyatLe ";
        }

        Query query = getCurrentSession().createQuery(sql);
        query.setParameter("fiyatLe", fiyatLe);
        query.setParameter("fiyatGe", fiyatGe);

        return query.list();
    }

    public List<Urun> findAllUrunListByFiyatBetween(BigDecimal fiyatGe, BigDecimal fiyatLe){

        String sql = "select urun from Urun urun where 1=1 ";

        if (fiyatGe != null && fiyatLe != null){
            sql = sql + " and urun.fiyat between :fiyatGe and :fiyatLe ";
        }

        Query query = getCurrentSession().createQuery(sql);
        query.setParameter("fiyatLe", fiyatLe);
        query.setParameter("fiyatGe", fiyatGe);

        return query.list();
    }

    public List<Urun> findAllUrunByKategoriKirilim(Long kirilim) {

        String sql = " select urun from Urun urun " +
                " left join Kategori kategori  on urun.kategori.id = kategori.id " +
                " where kategori.kirilim = :kirilim ";

        Query query = getCurrentSession().createQuery(sql);
        query.setParameter("kirilim", kirilim);

        return query.list();
    }

    public List<UrunDetayDto> findAllUrunDetayDtoByKategoriKirilim(Long kirilim) {

        String sql = " select " +
                " new com.kaankln.dto.UrunDetayDto( urun.adi, kategori.adi, urun.fiyat ) " +
                " from Urun urun " +
                " left join Kategori kategori  on urun.kategori.id = kategori.id " +
                " where kategori.kirilim = :kirilim ";

        Query query = getCurrentSession().createQuery(sql);
        query.setParameter("kirilim", kirilim);

        return query.list();
    }

    public List<CommantCountsOfProductsDto> findAllCommandsCountsOfProducts() {
        String sql=" select " +
                "new com.kaankln.dto.CommantCountsOfProductsDto(urun.id, urun.adi, urun.fiyat, COUNT(comment.productId)) " +
                "from Urun urun " +
                "left join ProductComment comment on urun.id = comment.productId " +
                "group by urun.id";

        Query query = getCurrentSession().createQuery(sql);
        return query.list();
    }
}