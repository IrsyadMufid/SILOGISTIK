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
    private Long id;
    private String nomorPengiriman;
    private String waktuPermintaan;
    private Karyawan karyawan;
    private String tanggalPengiriman;
    private String namaPenerima;
    private String alamatPenerima;
    private String jenisLayanan;
    private Integer biayaPengiriman;
    private List<BarangDetailDTO> daftarBarang;

}
    

