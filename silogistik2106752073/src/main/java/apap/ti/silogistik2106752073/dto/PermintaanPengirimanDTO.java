package apap.ti.silogistik2106752073.dto;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import apap.ti.silogistik2106752073.model.PermintaanPengirimanBarang;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PermintaanPengirimanDTO {
    private Long id;
    private String nomorPengiriman;
    private Date tanggalPengiriman;
    private LocalDateTime waktuPermintaan;
    private String namaPenerima;
    private String alamatPenerima;
    private List<PermintaanPengirimanBarang> barangList;
}
