package apap.ti.silogistik2106752073.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "GUDANG_BARANG")
public class GudangBarang {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_gudang", referencedColumnName = "id")
    @NotNull
    private Gudang gudang;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "sku_barang", referencedColumnName = "sku")
    @NotNull
    private Barang barang;

    @Column(name = "stok", nullable = false)
    @NotNull
    private Integer stok = 0;

    // Getter and Setter for all fields
}
