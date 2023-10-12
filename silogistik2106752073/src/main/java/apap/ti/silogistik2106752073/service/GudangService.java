package apap.ti.silogistik2106752073.service;
import java.util.List;
import apap.ti.silogistik2106752073.model.Barang;
import apap.ti.silogistik2106752073.model.Gudang;

public interface GudangService {
    List<Gudang> getAllGudang();
    Gudang getGudangById(Long id);
    void saveGudang(Gudang gudang);
    Gudang findGudangByBarangSku(String sku);
    void restockBarang(Gudang gudang, Barang barang, int jumlahStok);
    List<Barang> getBarangByGudangId(Long idGudang);
    
}
