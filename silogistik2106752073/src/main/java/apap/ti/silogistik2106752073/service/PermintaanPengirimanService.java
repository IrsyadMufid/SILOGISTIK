package apap.ti.silogistik2106752073.service;


import java.time.LocalDateTime;
import java.util.List;

import apap.ti.silogistik2106752073.dto.DetailPermintaanPengirimanDTO;
import apap.ti.silogistik2106752073.dto.PermintaanPengirimanDTO;
import apap.ti.silogistik2106752073.dto.PermintaanPengirimanRequestDTO;
import apap.ti.silogistik2106752073.dto.ReadListPermintaanPengirimanDTO;
import apap.ti.silogistik2106752073.model.PermintaanPengiriman;

public interface PermintaanPengirimanService {
String generateUniqueNomorPengiriman(PermintaanPengirimanRequestDTO requestDTO);
void savePermintaanPengiriman(PermintaanPengiriman permintaanPengiriman);
List<PermintaanPengiriman> getAllPermintaanPengiriman();
List<ReadListPermintaanPengirimanDTO> getAllPermintaanPengirimanAsDTO();
DetailPermintaanPengirimanDTO getDetailPermintaanPengirimanById(Long id);
String cancelPermintaanPengiriman(Long idPermintaanPengiriman);
List<PermintaanPengirimanDTO> getPermintaanPengirimanByDateRangeAndSKU(LocalDateTime startDate, LocalDateTime endDate, String sku);
}
