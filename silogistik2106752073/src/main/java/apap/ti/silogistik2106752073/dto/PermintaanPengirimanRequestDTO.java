package apap.ti.silogistik2106752073.dto;
import java.security.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import apap.ti.silogistik2106752073.model.Karyawan;
import apap.ti.silogistik2106752073.model.PermintaanPengirimanBarang;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PermintaanPengirimanRequestDTO {
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date tanggalPengiriman;
    private String namaPenerima;
    private Integer jenisLayanan;
    private String alamatPenerima;
    private Integer biayaPengiriman;
    private LocalDateTime waktuPermintaan;
    private Karyawan karyawan;
    private String nomorPengiriman;
    private List<PermintaanPengirimanBarang> barangList;
}
