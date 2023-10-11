package apap.ti.silogistik2106752073.service;


import java.util.List;

import apap.ti.silogistik2106752073.dto.PermintaanPengirimanRequestDTO;
import apap.ti.silogistik2106752073.dto.ReadListPermintaanPengirimanDTO;
import apap.ti.silogistik2106752073.model.PermintaanPengiriman;

public interface PermintaanPengirimanService {
String generateUniqueNomorPengiriman(PermintaanPengirimanRequestDTO requestDTO);
void savePermintaanPengiriman(PermintaanPengiriman permintaanPengiriman);
List<PermintaanPengiriman> getAllPermintaanPengirimans();
List<ReadListPermintaanPengirimanDTO> getAllPermintaanPengirimanAsDTO();
}
