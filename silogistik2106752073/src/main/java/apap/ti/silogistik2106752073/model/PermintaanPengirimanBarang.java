package apap.ti.silogistik2106752073.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "PERMINTAAN_PENGIRIMAN_BARANG")
public class PermintaanPengirimanBarang {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_permintaan_pengiriman", nullable = false)
    private PermintaanPengiriman permintaanPengiriman;

    @ManyToOne
    @JoinColumn(name = "sku_barang", nullable = false)
    private Barang barang;

    @Column(name = "kuantitas_pesanan", nullable = false)
    private Integer kuantitasPesanan;
}
