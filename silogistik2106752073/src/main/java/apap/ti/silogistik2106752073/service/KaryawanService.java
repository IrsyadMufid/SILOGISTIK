package apap.ti.silogistik2106752073.service;

import java.util.List;


import apap.ti.silogistik2106752073.model.Karyawan;

public interface KaryawanService {
    List<Karyawan> getAllKaryawan();
    void saveKaryawan(Karyawan karyawan);
}
