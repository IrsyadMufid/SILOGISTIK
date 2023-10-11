package apap.ti.silogistik2106752073.response;

import apap.ti.silogistik2106752073.dto.BarangDetailDTO;
import apap.ti.silogistik2106752073.dto.CreateBarangRequestDTO;
import apap.ti.silogistik2106752073.dto.UpdateBarangRequestDTO;
import apap.ti.silogistik2106752073.model.Barang;
import apap.ti.silogistik2106752073.model.PermintaanPengirimanBarang;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BarangMapper {

    Barang toEntity(CreateBarangRequestDTO createBarangRequestDTO);

    BarangDetailDTO toBarangDetailDTO(PermintaanPengirimanBarang barang);
    
    UpdateBarangRequestDTO barangToUpdateBarangRequestDTO(Barang barang);
    

    // Add any other mapping methods if needed
}


