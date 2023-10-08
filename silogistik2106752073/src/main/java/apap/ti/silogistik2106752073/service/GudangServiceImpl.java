package apap.ti.silogistik2106752073.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import apap.ti.silogistik2106752073.model.Gudang;
import apap.ti.silogistik2106752073.repository.GudangDb;


@Service
public class GudangServiceImpl implements GudangService {
    @Autowired
    private GudangDb gudangDb;

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
    public List<Gudang> findGudangBySkuBarang(String skuBarang) {
        // Implementasi pencarian gudang berdasarkan SKU barang
        return gudangDb.findByBarangSku(skuBarang);
    }
}
