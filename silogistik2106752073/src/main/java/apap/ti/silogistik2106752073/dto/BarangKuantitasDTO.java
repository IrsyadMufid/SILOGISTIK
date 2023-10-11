package apap.ti.silogistik2106752073.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BarangKuantitasDTO {
    private String sku;
    private Integer kuantitas;
    private Integer totalHarga;

    // Getter dan Setter
}
