package com.kaankln.uygulamalar;

import com.kaankln.entity.Urun;
import com.kaankln.entityservice.UrunEntityService;

import java.math.BigDecimal;
import java.util.List;

public class FindGeLeApp {

    public static void main(String[] args) {

        UrunEntityService service = new UrunEntityService();
        List<Urun> urunList = service.findAllUrunListByFiyatGeAndFiyatLe(BigDecimal.valueOf(100), BigDecimal.valueOf(1000));

        for (Urun urun : urunList) {
            System.out.println(urun);
        }
    }
}
