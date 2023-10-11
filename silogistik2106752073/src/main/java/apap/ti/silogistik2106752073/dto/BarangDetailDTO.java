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
    private double totalHarga;
    private String namaBarang; // Informasi yang diambil dari entitas Barang
    private double hargaBarang; // Informasi yang diambil dari entitas Barang
}
