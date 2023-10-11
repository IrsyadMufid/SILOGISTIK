package apap.ti.silogistik2106752073.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import apap.ti.silogistik2106752073.dto.CreateBarangRequestDTO;
import apap.ti.silogistik2106752073.dto.ReadListBarangDTO;
import apap.ti.silogistik2106752073.dto.UpdateBarangRequestDTO;
import apap.ti.silogistik2106752073.model.Barang;
import apap.ti.silogistik2106752073.model.Gudang;
import apap.ti.silogistik2106752073.model.GudangBarang;
import apap.ti.silogistik2106752073.response.BarangMapper;
import apap.ti.silogistik2106752073.service.BarangService;
import apap.ti.silogistik2106752073.service.GudangBarangService;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/barang")
public class BarangController {
    @Autowired
    private BarangService barangService;

    @Autowired
    private BarangMapper barangMapper;

    @Autowired
    private GudangBarangService gudangBarangService;



@GetMapping
public String listBarang(Model model) {
    List<ReadListBarangDTO> listBarangDTO = barangService.getAllBarangWithStok();
    model.addAttribute("listBarang", listBarangDTO);
    return "list-barang";
}


    @GetMapping("/tambah")
    public String formAddBarang(Model model) {
        // Membuat barangDTO baru untuk diisi di form
        var barangDTO = new CreateBarangRequestDTO();
        // Menambahkan barangDTO ke model Thymeleaf
        model.addAttribute("barangDTO", barangDTO);
        return "form-create-barang";
    }

    
    @PostMapping("/tambah")
    public String addBarang(@ModelAttribute("barangDTO") CreateBarangRequestDTO barangDTO) {
        // Mendapatkan tipe barang dalam bentuk String
        String tipeBarangCode = String.valueOf(barangDTO.getTipeBarang());
    
        // Menghasilkan SKU menggunakan metode generateSKU dari BarangService
        String sku = barangService.generateSKU(tipeBarangCode);
    
        // Set nilai SKU ke dalam DTO
        barangDTO.setSku(sku);
    
        // Konversi CreateBarangRequestDTO menjadi entitas Barang
        Barang barang = barangMapper.toEntity(barangDTO);
    
        // Simpan barang ke dalam basis data
        barangService.saveBarang(barang);
    
        // Redirect ke halaman Daftar Barang atau halaman lain yang sesuai
        return "redirect:/barang";
    }

    @GetMapping("/{sku}")
    public String detailBarang(@PathVariable("sku") String sku, Model model) {
        // Mengambil informasi barang berdasarkan sku
        Barang barang = barangService.getBarangBySku(sku);

        if (barang != null) {
            // Mendapatkan daftar gudang yang memiliki barang tersebut
            List<Gudang> listGudang = gudangBarangService.findGudangByBarangSku(sku);

            // Menyiapkan data untuk ditampilkan pada halaman detail barang
            model.addAttribute("barang", barang);
            model.addAttribute("listGudang", listGudang);

            // Mengambil stok untuk setiap gudang dan menambahkannya ke model
            Map<Long, Integer> stokMap = new HashMap<>();
            for (Gudang gudang : listGudang) {
                Integer stok = gudangBarangService.findStokByBarangAndGudang(barang, gudang);
                stokMap.put(gudang.getId(), stok);
            }
            model.addAttribute("stokMap", stokMap);

            return "detail-barang"; // Sesuaikan dengan nama tampilan yang sesuai
        } else {
            // Handle jika barang tidak ditemukan
            String errorMessage = "Barang tidak ditemukan dengan SKU: " + sku;
            model.addAttribute("errorMessage", errorMessage);

            return "error-page"; // Sesuaikan dengan nama tampilan yang sesuai untuk halaman error
        }
    }
@GetMapping("/{sku}/ubah")
public String formUpdateBarang(@PathVariable("sku") String sku, Model model) {
    // Mendapatkan barang berdasarkan SKU
    Barang barang = barangService.getBarangBySku(sku);

    // Memindahkan data barang ke DTO untuk diedit oleh pengguna
    UpdateBarangRequestDTO barangDTO = barangMapper.barangToUpdateBarangRequestDTO(barang);

    // Tambahkan variabel barangDTO ke model untuk dirender di template Thymeleaf
    model.addAttribute("barangDTO", barangDTO);

    // Redirect ke halaman edit barang
    return "form-update-barang";
}
@PostMapping("/{sku}/ubah")
public String updateBarang(@Valid @ModelAttribute UpdateBarangRequestDTO barangDTO, BindingResult bindingResult, Model model) {
    if (bindingResult.hasErrors()) {
        var errorMessage = "Data tidak valid";
        model.addAttribute("errorMessage", errorMessage);
        return "error-view";
    }
    
    // Mengambil barang dari database berdasarkan SKU
    Barang barang = barangService.getBarangBySku(barangDTO.getSku());
    
    // Update harga dan merek barang sesuai dengan data yang diterima dari form
    barang.setHargaBarang(barangDTO.getHargaBarang());
    barang.setMerk(barangDTO.getMerk());
    
    UpdateBarangRequestDTO barangToUpdateDTO = barangMapper.barangToUpdateBarangRequestDTO(barang);
    // Simpan perubahan ke dalam database
    barangService.updateBarang(barangToUpdateDTO);
    
    // Redirect ke halaman detail barang atau halaman lain yang sesuai
    return "redirect:/barang/" + barang.getSku();
}


}
