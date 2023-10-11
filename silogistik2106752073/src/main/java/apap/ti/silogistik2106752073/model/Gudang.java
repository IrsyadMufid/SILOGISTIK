package apap.ti.silogistik2106752073.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "GUDANG")
public class Gudang {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "nama", nullable = false)
    private String nama;

    @Column(name = "alamat_gudang", nullable = false)
    private String alamatGudang;

    @OneToMany(mappedBy = "gudang", cascade = CascadeType.ALL)
    private List<GudangBarang> gudangBarangList;

    // Getter and Setter for all fields
}