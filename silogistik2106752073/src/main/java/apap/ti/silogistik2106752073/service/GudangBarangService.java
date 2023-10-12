package apap.ti.silogistik2106752073.service;


import apap.ti.silogistik2106752073.model.Barang;
import apap.ti.silogistik2106752073.model.Gudang;
import apap.ti.silogistik2106752073.model.GudangBarang;

import java.util.List;

public interface GudangBarangService {
    List<GudangBarang> findAllByGudangId(Long idGudang);
    GudangBarang findById(Long id);
    void saveGudangBarang(GudangBarang gudangBarang);
    void deleteGudangBarang(GudangBarang gudangBarang);
    List<GudangBarang> getAllGudangBarang();
    List<Gudang> findGudangByBarangSku(String skuBarang);
    Integer findStokByBarangAndGudang(Barang barang, Gudang gudang);
    Integer findStokByGudangIdAndBarangSku(Long gudangId, String sku);


}
