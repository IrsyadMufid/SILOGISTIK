package apap.ti.silogistik2106752073.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import apap.ti.silogistik2106752073.model.PermintaanPengiriman;
import apap.ti.silogistik2106752073.model.PermintaanPengirimanBarang;

@Repository
public interface PermintaanPengirimanDb extends JpaRepository <PermintaanPengiriman, Long>{
    List<PermintaanPengiriman> findAllByWaktuPermintaanBetweenAndBarangList_Barang_Sku(
        LocalDateTime startDate, LocalDateTime endDate, String sku);
}
