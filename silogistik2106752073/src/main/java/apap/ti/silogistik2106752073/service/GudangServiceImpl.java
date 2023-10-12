package apap.ti.silogistik2106752073.service;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import apap.ti.silogistik2106752073.model.Barang;
import apap.ti.silogistik2106752073.model.Gudang;
import apap.ti.silogistik2106752073.model.GudangBarang;
import apap.ti.silogistik2106752073.repository.GudangBarangDb;
import apap.ti.silogistik2106752073.repository.GudangDb;


@Service
public class GudangServiceImpl implements GudangService {
    @Autowired
    private GudangDb gudangDb;

    @Autowired
    private GudangBarangDb gudangBarangDb;

    @Override
    public List<Gudang> getAllGudang() {
        return gudangDb.findAll();
    }
    @Override
    public void saveGudang(Gudang gudang) {
        gudangDb.save(gudang);
    }
    @Override
    public Gudang getGudangById(Long id) {
        // Implement the logic to retrieve a Gudang by its ID from the repository.
        return gudangDb.findById(id).orElse(null); // You may need to handle the case where it's not found.
    }
    
@Override
public Gudang findGudangByBarangSku(String sku) {
    List<Gudang> allGudang = getAllGudang();
    for (Gudang gudang : allGudang) {
        for (GudangBarang gudangBarang : gudang.getGudangBarangList()) {
            if (gudangBarang.getBarang().getSku().equals(sku)) {
                return gudang;
            }
        }
    }
    return null; // Gudang tidak ditemukan
}

@Override
public void restockBarang(Gudang gudang, Barang barang, int jumlahStok) {
    // Cek apakah barang ada dalam gudang
    for (GudangBarang gudangBarang : gudang.getGudangBarangList()) {
        if (gudangBarang.getBarang().equals(barang)) {
            // Barang ditemukan dalam gudang, update stok
            int stokLama = gudangBarang.getStok();
            int stokBaru = stokLama + jumlahStok;
            gudangBarang.setStok(stokBaru);
            // Anda mungkin perlu menyimpan perubahan ke dalam basis data
            // dengan memanggil repository yang sesuai.
            return; // Keluar dari metode setelah update stok
        }
    }
    
    // Jika barang tidak ditemukan dalam gudang, Anda mungkin ingin
    // menangani kasus ini sesuai kebutuhan Anda.
}

@Override
public List<Barang> getBarangByGudangId(Long idGudang) {
    Gudang gudang = getGudangById(idGudang);
    if (gudang != null) {
        List<Barang> barangList = new ArrayList<>();
        for (GudangBarang gudangBarang : gudang.getGudangBarangList()) {
            barangList.add(gudangBarang.getBarang());
        }
        return barangList;
    }
    return Collections.emptyList();
}

}



