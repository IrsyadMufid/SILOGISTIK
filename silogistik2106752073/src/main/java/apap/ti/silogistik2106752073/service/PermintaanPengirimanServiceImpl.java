package apap.ti.silogistik2106752073.service;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import apap.ti.silogistik2106752073.dto.PermintaanPengirimanRequestDTO;
import apap.ti.silogistik2106752073.dto.ReadListPermintaanPengirimanDTO;
import apap.ti.silogistik2106752073.model.Karyawan;
import apap.ti.silogistik2106752073.model.PermintaanPengiriman;
import apap.ti.silogistik2106752073.model.PermintaanPengirimanBarang;
import apap.ti.silogistik2106752073.repository.PermintaanPengirimanDb;

@Service
public class PermintaanPengirimanServiceImpl implements PermintaanPengirimanService {

    @Autowired
    private PermintaanPengirimanDb permintaanPengirimandDb;


    @Override
public String generateUniqueNomorPengiriman(PermintaanPengirimanRequestDTO requestDTO) {
    // Mengambil jumlah barang
    int jumlahBarang = 0;
    for (PermintaanPengirimanBarang barang : requestDTO.getBarangList()) {
        jumlahBarang += barang.getKuantitasPesanan();
    }

    // Mengambil jenis layanan (1 = SAM, 2 = KIL, 3 = REG, 4 = HEM)
    String jenisLayanan = "";
    switch (requestDTO.getJenisLayanan()) {
        case 1:
            jenisLayanan = "SAM";
            break;
        case 2:
            jenisLayanan = "KIL";
            break;
        case 3:
            jenisLayanan = "REG";
            break;
        case 4:
            jenisLayanan = "HEM";
            break;
        default:
            // Handle if jenis layanan is not valid
            break;
    }

    // Mengambil waktu pembuatan permintaan pengiriman
    LocalDateTime waktuPembuatan = LocalDateTime.now();
    DateTimeFormatter formatter = new DateTimeFormatterBuilder()
            .appendPattern("HH:mm:ss")
            .parseDefaulting(ChronoField.DAY_OF_MONTH, waktuPembuatan.getDayOfMonth())
            .parseDefaulting(ChronoField.MONTH_OF_YEAR, waktuPembuatan.getMonthValue())
            .parseDefaulting(ChronoField.YEAR, waktuPembuatan.getYear())
            .toFormatter();

    String waktuPembuatanString = waktuPembuatan.format(formatter);

    // Menggabungkan semua komponen
    String nomorPengiriman = "REQ";
    nomorPengiriman += String.format("%02d", jumlahBarang % 100); // Mengambil 2 digit terakhir dari jumlah barang
    nomorPengiriman += jenisLayanan;
    nomorPengiriman += waktuPembuatanString;

    return nomorPengiriman;
}
    
    
    
    
      @Override
    public List<PermintaanPengiriman> getAllPermintaanPengirimans() {
        return permintaanPengirimandDb.findAll();
    }

    @Override
    public void savePermintaanPengiriman(PermintaanPengiriman permintaanPengiriman) {
        permintaanPengiriman.setWaktuPermintaan(LocalDateTime.now());
        for(PermintaanPengirimanBarang permintaanPengirimanBarang : permintaanPengiriman.getBarangList()){
            permintaanPengirimanBarang.setPermintaanPengiriman(permintaanPengiriman);
        }
        permintaanPengirimandDb.save(permintaanPengiriman);
    }

    
@Override
public List<ReadListPermintaanPengirimanDTO> getAllPermintaanPengirimanAsDTO() {
    List<PermintaanPengiriman> listPermintaanPengiriman = permintaanPengirimandDb.findAll();
    List<ReadListPermintaanPengirimanDTO> listDTO = new ArrayList<>();

    DateTimeFormatter waktuPermintaanFormatter = DateTimeFormatter.ofPattern("d-MM-yyyy, HH:mm:ss");
    DateTimeFormatter tanggalPengirimanFormatter = DateTimeFormatter.ofPattern("d-MM-yyyy");

    for (PermintaanPengiriman permintaan : listPermintaanPengiriman) {
        ReadListPermintaanPengirimanDTO dto = new ReadListPermintaanPengirimanDTO();
        dto.setNomorPengiriman(permintaan.getNomorPengiriman());
        
        // Format waktuPermintaan with date and time
        dto.setWaktuPermintaan(permintaan.getWaktuPermintaan().format(waktuPermintaanFormatter));

        dto.setNamaPenerima(permintaan.getNamaPenerima());
        dto.setAlamatPenerima(permintaan.getAlamatPenerima());

        LocalDateTime localDateTime = permintaan.getTanggalPengiriman().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();

        // Format tanggalPengiriman with date only
        dto.setTanggalPengiriman(localDateTime.format(tanggalPengirimanFormatter));

        listDTO.add(dto);
    }

    return listDTO;
}






    
    
}
