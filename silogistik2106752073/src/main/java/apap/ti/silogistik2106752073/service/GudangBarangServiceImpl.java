package apap.ti.silogistik2106752073.service;

import apap.ti.silogistik2106752073.model.Barang;
import apap.ti.silogistik2106752073.model.Gudang;
import apap.ti.silogistik2106752073.model.GudangBarang;
import apap.ti.silogistik2106752073.repository.GudangBarangDb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GudangBarangServiceImpl implements GudangBarangService {

    private final GudangBarangDb gudangBarangDb;

    @Autowired
    public GudangBarangServiceImpl(GudangBarangDb gudangBarangDb) {
        this.gudangBarangDb = gudangBarangDb;
    }

    @Override
    public List<GudangBarang> findAllByGudangId(Long idGudang) {
        return gudangBarangDb.findAllByGudangId(idGudang);
    }

    @Override
    public GudangBarang findById(Long id) {
        return gudangBarangDb.findById(id).orElse(null);
    }

    @Override
    public void saveGudangBarang(GudangBarang gudangBarang) {
        gudangBarangDb.save(gudangBarang);
    }

    @Override
    public void deleteGudangBarang(GudangBarang gudangBarang) {
        gudangBarangDb.delete(gudangBarang);
    }

    @Override
    public List<GudangBarang> getAllGudangBarang() {
        return gudangBarangDb.findAll();
    }

    @Override
    public List<Gudang> findGudangByBarangSku(String skuBarang) {
        List<GudangBarang> gudangBarangList = gudangBarangDb.findByBarangSku(skuBarang);
        List<Gudang> gudangList = new ArrayList<>();

        for (GudangBarang gudangBarang : gudangBarangList) {
            Gudang gudang = gudangBarang.getGudang();
            gudangList.add(gudang);
        }

        return gudangList;
}

@Override
public Integer findStokByBarangAndGudang(Barang barang, Gudang gudang) {
    GudangBarang gudangBarang = gudangBarangDb.findByBarangAndGudang(barang, gudang);
    if (gudangBarang != null) {
        return gudangBarang.getStok();
    }
    return 0; // Jika tidak ditemukan, kembalikan stok 0
}

@Override
public Integer findStokByGudangIdAndBarangSku(Long gudangId, String sku) {
    GudangBarang gudangBarang = gudangBarangDb.findByGudangIdAndBarangSku(gudangId, sku);
    if (gudangBarang != null) {
        return gudangBarang.getStok();
    }
    return 0; // Jika tidak ditemukan, kembalikan stok 0
}


    
    

}
