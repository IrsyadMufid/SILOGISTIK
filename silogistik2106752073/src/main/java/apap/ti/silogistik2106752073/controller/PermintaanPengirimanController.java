package apap.ti.silogistik2106752073.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import apap.ti.silogistik2106752073.dto.DetailPermintaanPengirimanDTO;
import apap.ti.silogistik2106752073.dto.PermintaanPengirimanRequestDTO;
import apap.ti.silogistik2106752073.dto.ReadListPermintaanPengirimanDTO;
import apap.ti.silogistik2106752073.model.Barang;
import apap.ti.silogistik2106752073.model.Karyawan;
import apap.ti.silogistik2106752073.model.PermintaanPengiriman;
import apap.ti.silogistik2106752073.model.PermintaanPengirimanBarang;
import apap.ti.silogistik2106752073.response.PermintaanPengirimanMapper;
import apap.ti.silogistik2106752073.service.BarangService;
import apap.ti.silogistik2106752073.service.KaryawanService;
import apap.ti.silogistik2106752073.service.PermintaanPengirimanService;

@Controller
@RequestMapping("/permintaan-pengiriman")
public class PermintaanPengirimanController {
    
    @Autowired
    private BarangService barangService;
    
    @Autowired
    private KaryawanService karyawanService;
    // Add a GET mapping to show the form for creating a new Permintaan Pengiriman

    @Autowired
    private PermintaanPengirimanService permintaanPengirimanService;

    @Autowired
    private PermintaanPengirimanMapper permintaanPengirimanMapper;

    @Autowired
    private PermintaanPengirimanService detailPermintaanPengirimanService;
    
// Perbaikan di method showForm
// @GetMapping
//     public String listPermintaanPengiriman(Model model) {
//         List<PermintaanPengiriman> listPermintaanPengiriman = permintaanPengirimanService.getAllPermintaanPengirimans();
//         model.addAttribute("listPermintaanPengiriman", listPermintaanPengiriman);
//         return "list-permintaan-pengiriman";
//     }
@GetMapping
public String listPermintaanPengiriman(Model model) {
    List<ReadListPermintaanPengirimanDTO> listPermintaanPengirimanDTO = permintaanPengirimanService.getAllPermintaanPengirimanAsDTO();
    model.addAttribute("listPermintaanPengiriman", listPermintaanPengirimanDTO);
    return "list-permintaan-pengiriman";
}


@GetMapping("/tambah")
public String showFormTambahPermintaanPengiriman(Model model) {
    // Inisialisasi objek PermintaanPengirimanRequestDTO dan set data yang diperlukan

    PermintaanPengirimanRequestDTO permintaanPengirimanRequestDTO = new PermintaanPengirimanRequestDTO();
    model.addAttribute("permintaanPengirimanRequestDTO", permintaanPengirimanRequestDTO);
    
    // Ambil daftar karyawan
    List<Karyawan> listKaryawan = karyawanService.getAllKaryawan();
    model.addAttribute("listKaryawan", listKaryawan);
    
    // Ambil daftar barang yang dapat dipilih
    List<Barang> listBarangExisting = barangService.getAllBarang();
    model.addAttribute("listBarangExisting", listBarangExisting);
    
    return "form-tambah-permintaan-pengiriman";
}

@PostMapping("/tambah")
public String submitFormTambahPermintaanPengiriman(@ModelAttribute PermintaanPengirimanRequestDTO permintaanPengirimanRequestDTO, Model model) {
    // Generate nomor pengiriman
    
    
    String nomorPengiriman = permintaanPengirimanService.generateUniqueNomorPengiriman(permintaanPengirimanRequestDTO);
    permintaanPengirimanRequestDTO.setNomorPengiriman(nomorPengiriman);
    
    // Simpan permintaan pengiriman
    PermintaanPengiriman permintaanPengiriman = permintaanPengirimanMapper.requestDtoToEntity(permintaanPengirimanRequestDTO);
    permintaanPengirimanService.savePermintaanPengiriman(permintaanPengiriman);

    // Redirect ke halaman daftar permintaan pengiriman
    return "redirect:/permintaan-pengiriman/tambah";
}

@PostMapping(value = "/tambah", params = {"addRow"})
public String addRowBarang(@ModelAttribute PermintaanPengirimanRequestDTO permintaanPengirimanRequestDTO, Model model) {
    if (permintaanPengirimanRequestDTO.getBarangList() == null) {
        permintaanPengirimanRequestDTO.setBarangList(new ArrayList<>());
    }

    // Tambahkan objek PermintaanPengirimanBarang baru ke dalam daftar barang
    permintaanPengirimanRequestDTO.getBarangList().add(new PermintaanPengirimanBarang());

    List<Karyawan> listKaryawan = karyawanService.getAllKaryawan();
    model.addAttribute("listKaryawan", listKaryawan);
    
    // Ambil daftar barang yang dapat dipilih
    List<Barang> listBarangExisting = barangService.getAllBarang();
    model.addAttribute("listBarangExisting", listBarangExisting);
    // Kirim daftar barang yang dapat dipilih ke tampilan (dropdown)
    model.addAttribute("listBarangExisting", barangService.getAllBarang());

    return "form-tambah-permintaan-pengiriman";
} 


@GetMapping("/{idPermintaanPengiriman}")
public String detailPermintaanPengiriman(@PathVariable Long idPermintaanPengiriman, Model model) {
    DetailPermintaanPengirimanDTO detailDTO = detailPermintaanPengirimanService.getDetailPermintaanPengirimanById(idPermintaanPengiriman);
    model.addAttribute("detailPermintaanPengiriman", detailDTO);
    return "detail-permintaan-pengiriman"; // Sesuaikan dengan nama tampilan yang sesuai
}
}

