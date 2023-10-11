package apap.ti.silogistik2106752073.dto;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReadListBarangDTO {
    private String sku;
    private String merk;
    private Integer stok;
    private Long hargaBarang;

    // Getter and Setter
}

