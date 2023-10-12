package apap.ti.silogistik2106752073.controller;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;

import apap.ti.silogistik2106752073.service.BarangService;
import apap.ti.silogistik2106752073.service.GudangService;
import apap.ti.silogistik2106752073.service.KaryawanService;
import apap.ti.silogistik2106752073.service.PermintaanPengirimanService;

@Controller
public class HomeController {

    @Autowired
    private BarangService barangService;

    @Autowired
    private GudangService gudangService;

    @Autowired
    private PermintaanPengirimanService permintaanPengirimanService;

    @Autowired
    private KaryawanService karyawanService;

    @GetMapping("/")
    public String home(Model model) {
        // Mengambil jumlah data dari masing-masing fitur
        int jumlahBarang = barangService.getAllBarang().size();
        int jumlahGudang = gudangService.getAllGudang().size();
        int jumlahPermintaan = permintaanPengirimanService.getAllPermintaanPengiriman().size();
        int jumlahKaryawan = karyawanService.getAllKaryawan().size();

        // Menambahkan data ke model
        model.addAttribute("jumlahBarang", jumlahBarang);
        model.addAttribute("jumlahGudang", jumlahGudang);
        model.addAttribute("jumlahPermintaan", jumlahPermintaan);
        model.addAttribute("jumlahKaryawan", jumlahKaryawan);

        return "home"; // Nama template HTML
    }
}

