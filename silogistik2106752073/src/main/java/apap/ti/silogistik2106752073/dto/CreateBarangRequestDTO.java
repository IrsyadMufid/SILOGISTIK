package apap.ti.silogistik2106752073.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateBarangRequestDTO {
    private String sku;
    private Integer tipeBarang; // Gunakan Integer untuk tipe barang
    private String merk;
    private Integer hargaBarang;
}
