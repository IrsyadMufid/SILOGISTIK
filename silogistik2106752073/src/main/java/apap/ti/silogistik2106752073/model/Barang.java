package apap.ti.silogistik2106752073.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "BARANG")
public class Barang {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "sku", nullable = false, unique = true, length = 7)
    private String sku;

    @Column(name = "tipe_barang", nullable = false)
    private Integer tipeBarang;

    @Column(name = "merk", nullable = false)
    private String merk;

    @Column(name = "harga_barang", nullable = false)
    private Long hargaBarang;

    @ManyToMany(mappedBy = "barangList", fetch = FetchType.LAZY)
    
    @JsonIgnoreProperties("barangList")
    private List<Gudang> gudangList;

    // Buat getter dan setter
}
