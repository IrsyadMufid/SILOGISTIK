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
@Table(name = "GUDANG")
public class Gudang {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "nama", nullable = false)
    private String nama;

    @Column(name = "alamat_gudang", nullable = false)
    private String alamatGudang;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "GUDANG_BARANG",
        joinColumns = @JoinColumn(name = "id_gudang"),
        inverseJoinColumns = @JoinColumn(name = "sku_barang")
    )
    @JsonIgnoreProperties("gudangList")
    private List<Barang> barangList;

    // Buat getter dan setter
}
