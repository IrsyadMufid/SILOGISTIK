package apap.ti.silogistik2106752073.dto;

import apap.ti.silogistik2106752073.model.Barang;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BarangDetailDTO {
    private Barang barang;
    private int kuantitasPesanan;
    private int totalHarga;
    private String namaBarang; // Informasi yang diambil dari entitas Barang
    private int hargaBarang; // Informasi yang diambil dari entitas Barang
}
