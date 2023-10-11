package apap.ti.silogistik2106752073.repository;

import apap.ti.silogistik2106752073.model.Barang;
import apap.ti.silogistik2106752073.model.Gudang;
import apap.ti.silogistik2106752073.model.GudangBarang;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GudangBarangDb extends JpaRepository<GudangBarang, Long> {
    List<GudangBarang> findAllByGudangId(Long idGudang);
    List<GudangBarang> findByBarangSku(String skuBarang);
    GudangBarang findByBarangAndGudang(Barang barang, Gudang gudang);
    
}
