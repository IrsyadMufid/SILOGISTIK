package apap.ti.silogistik2106752073.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import apap.ti.silogistik2106752073.model.Barang;

@Repository
public interface BarangDb extends JpaRepository<Barang, Long> {
    Barang findBySku(String sku);
}
