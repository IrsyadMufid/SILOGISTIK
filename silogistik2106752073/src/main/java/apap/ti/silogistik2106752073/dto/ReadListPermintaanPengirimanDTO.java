package apap.ti.silogistik2106752073.dto;
import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReadListPermintaanPengirimanDTO {
    private Long id;
    private String nomorPengiriman;
    private String waktuPermintaan;
    private String namaPenerima;
    private String alamatPenerima;
    private String tanggalPengiriman;
}
