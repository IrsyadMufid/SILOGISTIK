package apap.ti.silogistik2106752073.service;

import java.util.List;

import apap.ti.silogistik2106752073.dto.ReadListBarangDTO;
import apap.ti.silogistik2106752073.dto.UpdateBarangRequestDTO;
import apap.ti.silogistik2106752073.model.Barang;

public interface BarangService {
    List<Barang> getAllBarang();
    Barang getBarangById(Long id);
    void saveBarang(Barang barang);
    String generateSKU(String tipeBarangCode);
    Barang getBarangBySku(String sku);
    List<ReadListBarangDTO> getAllBarangWithStok();
    List<Barang> findAllSortedByName();
    Barang updateBarang(UpdateBarangRequestDTO barangDTO);
}
