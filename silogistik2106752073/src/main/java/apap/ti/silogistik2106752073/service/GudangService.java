package apap.ti.silogistik2106752073.service;

import java.util.List;

import apap.ti.silogistik2106752073.model.Gudang;

public interface GudangService {
    List<Gudang> getAllGudang();
    Gudang getGudangById(Long id);
    void saveGudang(Gudang gudang);
    List<Gudang> findGudangBySkuBarang(String skuBarang);
}
