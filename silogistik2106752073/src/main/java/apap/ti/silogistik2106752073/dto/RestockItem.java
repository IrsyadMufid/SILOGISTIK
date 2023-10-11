package apap.ti.silogistik2106752073.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
  


@Data
@AllArgsConstructor
@NoArgsConstructor
public class RestockItem {
    private String barangSku;
    private int stok;

    // Getter dan setter akan di-generate oleh Lombok
}
