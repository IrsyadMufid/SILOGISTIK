package apap.ti.silogistik2106752073.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.persistence.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "PERMINTAAN_PENGIRIMAN")
public class PermintaanPengiriman {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "nomor_pengiriman", nullable = false, unique = true, length = 16)
    private String nomorPengiriman;

    @Column(name = "is_cancelled", nullable = false)
    private Boolean isCancelled = false;

    @Column(name = "nama_penerima", nullable = false)
    private String namaPenerima;

    @Column(name = "alamat_penerima", nullable = false)
    private String alamatPenerima;

    @Column(name = "tanggal_pengiriman", nullable = false)
    private Date tanggalPengiriman;

    @Column(name = "biaya_pengiriman", nullable = false)
    private Double biayaPengiriman;

    @Column(name = "jenis_layanan", nullable = false)
    private Integer jenisLayanan;

    @Column(name = "waktu_permintaan", nullable = false)
    private LocalDateTime waktuPermintaan;

    @ManyToOne
    @JoinColumn(name = "id_karyawan", nullable = false)
    private Karyawan karyawan;

    @OneToMany(mappedBy = "permintaanPengiriman", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<PermintaanPengirimanBarang> barangList;
}

