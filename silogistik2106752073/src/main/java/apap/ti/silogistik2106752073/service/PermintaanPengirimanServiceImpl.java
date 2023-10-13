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
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import apap.ti.silogistik2106752073.dto.BarangDetailDTO;
import apap.ti.silogistik2106752073.dto.DetailPermintaanPengirimanDTO;
import apap.ti.silogistik2106752073.dto.PermintaanPengirimanDTO;
import apap.ti.silogistik2106752073.dto.PermintaanPengirimanRequestDTO;
import apap.ti.silogistik2106752073.dto.ReadListPermintaanPengirimanDTO;
import apap.ti.silogistik2106752073.model.Karyawan;
import apap.ti.silogistik2106752073.model.PermintaanPengiriman;
import apap.ti.silogistik2106752073.model.PermintaanPengirimanBarang;
import apap.ti.silogistik2106752073.repository.PermintaanPengirimanDb;
import apap.ti.silogistik2106752073.response.BarangMapper;
import apap.ti.silogistik2106752073.response.PermintaanPengirimanMapper;

@Service
public class PermintaanPengirimanServiceImpl implements PermintaanPengirimanService {

    @Autowired
    private PermintaanPengirimanDb permintaanPengirimanDb;

    @Autowired
    private PermintaanPengirimanMapper permintaanPengirimanMapper;

    @Autowired
    private BarangMapper barangMapper;

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
    
    
    
    
public List<PermintaanPengiriman> getAllPermintaanPengiriman() {
    return permintaanPengirimanDb.findAll();
}

    @Override
    public void savePermintaanPengiriman(PermintaanPengiriman permintaanPengiriman) {
        permintaanPengiriman.setWaktuPermintaan(LocalDateTime.now());
        for(PermintaanPengirimanBarang permintaanPengirimanBarang : permintaanPengiriman.getBarangList()){
            permintaanPengirimanBarang.setPermintaanPengiriman(permintaanPengiriman);
        }
        permintaanPengirimanDb.save(permintaanPengiriman);
    }

// Testing cancel
//     @Override
// public void savePermintaanPengiriman(PermintaanPengiriman permintaanPengiriman) {
//     // Simulate the creation time to be more than 24 hours ago (e.g., 2 days ago)
//     LocalDateTime waktuPermintaan = LocalDateTime.now().minusDays(4);
//     permintaanPengiriman.setWaktuPermintaan(waktuPermintaan);

//     for (PermintaanPengirimanBarang permintaanPengirimanBarang : permintaanPengiriman.getBarangList()) {
//         permintaanPengirimanBarang.setPermintaanPengiriman(permintaanPengiriman);
//     }
//     permintaanPengirimanDb.save(permintaanPengiriman);
// }


    
@Override
public List<ReadListPermintaanPengirimanDTO> getAllPermintaanPengirimanAsDTO() {
    List<PermintaanPengiriman> listPermintaanPengiriman = permintaanPengirimanDb.findAll();
    List<ReadListPermintaanPengirimanDTO> listDTO = new ArrayList<>();

    DateTimeFormatter waktuPermintaanFormatter = DateTimeFormatter.ofPattern("d-MM-yyyy, HH:mm:ss");
    DateTimeFormatter tanggalPengirimanFormatter = DateTimeFormatter.ofPattern("d-MM-yyyy");

    for (PermintaanPengiriman permintaan : listPermintaanPengiriman) {
        ReadListPermintaanPengirimanDTO dto = new ReadListPermintaanPengirimanDTO();
        dto.setId(permintaan.getId());
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




@Override
public DetailPermintaanPengirimanDTO getDetailPermintaanPengirimanById(Long idPermintaanPengiriman) {
    PermintaanPengiriman permintaanPengiriman = permintaanPengirimanDb.findById(idPermintaanPengiriman).orElse(null);
    if (permintaanPengiriman != null) {
        DetailPermintaanPengirimanDTO detailDTO = permintaanPengirimanMapper.entityToDTO(permintaanPengiriman);

        // Format waktuPermintaan
        DateTimeFormatter waktuPermintaanFormatter = DateTimeFormatter.ofPattern("d-MM-yyyy, HH:mm:ss");
        String formattedWaktuPermintaan = permintaanPengiriman.getWaktuPermintaan().format(waktuPermintaanFormatter);
        detailDTO.setWaktuPermintaan(formattedWaktuPermintaan);

        // Format tanggalPengiriman
        DateTimeFormatter tanggalPengirimanFormatter = DateTimeFormatter.ofPattern("d-MM-yyyy");
        LocalDateTime localDateTime = permintaanPengiriman.getTanggalPengiriman().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        String formattedTanggalPengiriman = localDateTime.format(tanggalPengirimanFormatter);
        detailDTO.setTanggalPengiriman(formattedTanggalPengiriman);

        // Menggunakan mapper untuk mengubah entitas PermintaanPengirimanBarang menjadi BarangDetailDTO
        List<BarangDetailDTO> daftarBarangDTO = new ArrayList<>();
        for (PermintaanPengirimanBarang barang : permintaanPengiriman.getBarangList()) {
            BarangDetailDTO barangDTO = barangMapper.toBarangDetailDTO(barang);
            // Hitung totalHarga berdasarkan hargaBarang dan kuantitasPesanan
            barangDTO.setTotalHarga(barang.getBarang().getHargaBarang() * barang.getKuantitasPesanan());
            daftarBarangDTO.add(barangDTO);
        }

        // Set daftar barang dalam objek DetailPermintaanPengirimanDTO
        detailDTO.setDaftarBarang(daftarBarangDTO);

        return detailDTO;
    }
    return null; // Handle jika permintaan pengiriman tidak ditemukan
}

@Override
public String cancelPermintaanPengiriman(Long idPermintaanPengiriman) {
    PermintaanPengiriman permintaanPengiriman = permintaanPengirimanDb.findById(idPermintaanPengiriman).orElse(null);
    if (permintaanPengiriman != null) {
        // Cek apakah permintaan ini dibuat dalam 24 jam terakhir
        LocalDateTime waktuPembuatan = permintaanPengiriman.getWaktuPermintaan();
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime limit = now.minusHours(24);

        if (waktuPembuatan.isAfter(limit)) {
            // Hapus barang-barang terkait (optional)
            // permintaanPengiriman.getBarangList().clear();

            // Set isCancelled menjadi true
            permintaanPengiriman.setIsCancelled(true);
            permintaanPengirimanDb.save(permintaanPengiriman);
            return permintaanPengiriman.getNomorPengiriman();
        } else {
            // Handle jika permintaan tidak bisa dibatalkan karena sudah melewati 24 jam
            // Misalnya dengan melempar pengecualian atau memberikan pesan kesalahan
        }
    }
    return null; // Handle jika permintaan tidak ditemukan
}





@Override
public List<PermintaanPengirimanDTO> getPermintaanPengirimanByDateRangeAndSKU(LocalDateTime startDate, LocalDateTime endDate, String sku) {
    List<PermintaanPengiriman> listPermintaanPengiriman = permintaanPengirimanDb.findAllByWaktuPermintaanBetweenAndBarangList_Barang_Sku(startDate, endDate, sku);

    // Buat daftar DTO untuk menyimpan hasil yang sesuai
    List<PermintaanPengirimanDTO> result = new ArrayList<>();

    for (PermintaanPengiriman permintaan : listPermintaanPengiriman) {
        for (PermintaanPengirimanBarang ppb : permintaan.getBarangList()) {
            // Cek apakah SKU dari entitas Barang cocok dengan SKU yang dicari
            if (ppb.getBarang().getSku().equals(sku)) {
                PermintaanPengirimanDTO dto = new PermintaanPengirimanDTO();
                dto.setId(permintaan.getId());
                dto.setTanggalPengiriman(permintaan.getTanggalPengiriman());
                dto.setNomorPengiriman(permintaan.getNomorPengiriman());
                dto.setWaktuPermintaan(permintaan.getWaktuPermintaan());
                dto.setNamaPenerima(permintaan.getNamaPenerima());
                dto.setAlamatPenerima(permintaan.getAlamatPenerima());

                // Anda dapat mengakses barangList dari PermintaanPengirimanDTO yang sesuai
                dto.setBarangList(permintaan.getBarangList());

                result.add(dto);
            }
        }
    }

    return result;
}


}