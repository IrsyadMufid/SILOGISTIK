package apap.ti.silogistik2106752073.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.persistence.*;


import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "PERMINTAAN_PENGIRIMAN")
public class PermintaanPengiriman {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "nomor_pengiriman", nullable = false, unique = true, length = 16)
    private String nomorPengiriman;

    @Column(name = "is_cancelled", nullable = false)
    private Boolean isCancelled;

    @Column(name = "nama_penerima", nullable = false)
    private String namaPenerima;

    @Column(name = "alamat_penerima", nullable = false)
    private String alamatPenerima;

    @Column(name = "tanggal_pengiriman", nullable = false)
    private Date tanggalPengiriman;

    @Column(name = "biaya_pengiriman", nullable = false)
    private Integer biayaPengiriman;

    @Column(name = "jenis_layanan", nullable = false)
    private Integer jenisLayanan;

    @Column(name = "waktu_permintaan", nullable = false)
    private Date waktuPermintaan;

    @ManyToOne
    @JoinColumn(name = "id_karyawan", nullable = false)
    private Karyawan karyawan;

    // Buat getter dan setter
}

