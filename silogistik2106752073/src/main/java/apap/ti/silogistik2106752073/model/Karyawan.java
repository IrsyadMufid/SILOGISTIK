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
@Table(name = "KARYAWAN")
public class Karyawan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "nama", nullable = false)
    private String nama;

    @Column(name = "jenis_kelamin", nullable = false)
    private Integer jenisKelamin;

    @Column(name = "tanggal_lahir", nullable = false)
    private Date tanggalLahir;
}

