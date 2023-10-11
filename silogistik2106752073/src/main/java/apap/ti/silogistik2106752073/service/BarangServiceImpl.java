package apap.ti.silogistik2106752073.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import apap.ti.silogistik2106752073.dto.ReadListBarangDTO;
import apap.ti.silogistik2106752073.dto.UpdateBarangRequestDTO;
import apap.ti.silogistik2106752073.model.Barang;
import apap.ti.silogistik2106752073.model.GudangBarang;
import apap.ti.silogistik2106752073.repository.BarangDb;
import org.springframework.data.domain.Sort;

@Service
public class BarangServiceImpl implements BarangService {
    @Autowired
    private BarangDb barangDb;

    @Override
    public List<Barang> getAllBarang() {
        return barangDb.findAll();
    }
    private GudangBarangService gudangBarangService;
    
    @Override
    public void saveBarang(Barang barang) {
        barangDb.save(barang);
    }

    @Override
    public Barang getBarangById(Long id) {
        return barangDb.findById(id).orElse(null);
    }

 private static final Map<Integer, String> TIPE_BARANG_MAP = Map.of(
        1, "ELEC",
        2, "CLOT",
        3, "FOOD",
        4, "COSM",
        5, "TOOL"
    );

    private final AtomicInteger nomorUnikCounter = new AtomicInteger(1);

    @Override
    public String generateSKU(String tipeBarangCode) {
        // Validasi dan mendapatkan kode tipe barang
        String kodeTipeBarang = TIPE_BARANG_MAP.getOrDefault(parseTipeBarang(tipeBarangCode), "OTHER");
 
        // Menghasilkan nomor unik yang diincrement secara otomatis
        int nomorUnik = nomorUnikCounter.getAndIncrement();
 
        // Menggabungkan kode tipe barang dengan nomor unik
        String sku = kodeTipeBarang + String.format("%03d", nomorUnik);
 
        return sku;
    }

       // Metode untuk memparse tipe barang menjadi integer
   private int parseTipeBarang(String tipeBarangCode) {
    try {
        return Integer.parseInt(tipeBarangCode);
    } catch (NumberFormatException e) {
        // Handle exception jika tipeBarangCode tidak dapat di-parse ke integer
        // Misalnya, lempar exception atau kembalikan nilai default
        return 0; // Nilai default jika terjadi kesalahan
    }
}

@Override
public Barang getBarangBySku(String sku) {
    // Implementasi logika untuk mengambil barang berdasarkan SKU
    // Anda dapat menggunakan repositori BarangDb untuk melakukannya
    return barangDb.findBySku(sku);
}
@Override
    public List<ReadListBarangDTO> getAllBarangWithStok() {
        List<Barang> listBarang = barangDb.findAll();
        List<ReadListBarangDTO> listBarangDTO = new ArrayList<>();

        for (Barang barang : listBarang) {
            ReadListBarangDTO barangDTO = new ReadListBarangDTO();
            barangDTO.setSku(barang.getSku());
            barangDTO.setMerk(barang.getMerk());
            int stok = 0;

            // Hitung total stok dari semua GudangBarang yang terkait dengan barang ini
            for (GudangBarang gudangBarang : barang.getGudangBarangList()) {
                stok += gudangBarang.getStok();
            }

            barangDTO.setStok(stok);
            barangDTO.setHargaBarang(barang.getHargaBarang());

            listBarangDTO.add(barangDTO);
        }

        return listBarangDTO;
    }
    @Override
    public List<Barang> findAllSortedByName() {
        return barangDb.findAll(Sort.by(Sort.Direction.ASC, "merk")); // Gantilah "merk" dengan properti yang sesuai
    }
    @Override
    public Barang updateBarang(UpdateBarangRequestDTO barangDTO) {
        // Mengambil data barang berdasarkan SKU dari database
        Barang barang = barangDb.findBySku(barangDTO.getSku());

        if (barang != null) {
            // Perbarui atribut-atribut yang ingin Anda ubah
            barang.setMerk(barangDTO.getMerk());
            barang.setHargaBarang(barangDTO.getHargaBarang());

            // Simpan barang yang telah diperbarui ke dalam basis data
            barangDb.save(barang);
        }

        return barang;
    }
}
