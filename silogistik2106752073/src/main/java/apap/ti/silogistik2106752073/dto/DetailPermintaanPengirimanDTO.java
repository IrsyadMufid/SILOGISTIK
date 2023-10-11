package apap.ti.silogistik2106752073.dto;

import java.time.LocalDateTime;
import java.util.List;

import apap.ti.silogistik2106752073.model.Karyawan;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DetailPermintaanPengirimanDTO {
    private String nomorPermintaanPengiriman;
    private LocalDateTime waktuPermintaan;
    private Karyawan karyawan;
    private LocalDateTime tanggalPengiriman;
    private String namaPenerima;
    private String alamatPenerima;
    private String jenisLayanan;
    private Double biayaPengiriman;
    private List<BarangDetailDTO> daftarBarang;

}
    

