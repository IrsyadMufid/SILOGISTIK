package apap.ti.silogistik2106752073.response;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


import apap.ti.silogistik2106752073.dto.PermintaanPengirimanRequestDTO;
import apap.ti.silogistik2106752073.model.PermintaanPengiriman;
import apap.ti.silogistik2106752073.model.PermintaanPengirimanBarang;

@Mapper(componentModel = "spring")
public interface PermintaanPengirimanMapper {
    
    PermintaanPengiriman requestDtoToEntity(PermintaanPengirimanRequestDTO requestDTO);

}