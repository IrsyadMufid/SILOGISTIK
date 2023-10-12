package apap.ti.silogistik2106752073.controller;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import apap.ti.silogistik2106752073.dto.RestockForm;
import apap.ti.silogistik2106752073.dto.RestockItem;
import apap.ti.silogistik2106752073.model.Barang;
import apap.ti.silogistik2106752073.model.Gudang;
import apap.ti.silogistik2106752073.model.GudangBarang;
import apap.ti.silogistik2106752073.service.BarangService;
import apap.ti.silogistik2106752073.service.GudangBarangService;
import apap.ti.silogistik2106752073.service.GudangService;

@Controller
@RequestMapping("/gudang")
public class GudangController {
    @Autowired
    private GudangService gudangService;

    @Autowired
    private BarangService barangService;

    @Autowired
    private GudangBarangService gudangBarangService;

    


    @GetMapping
    public String listGudang(Model model) {
        List<Gudang> listGudang = gudangService.getAllGudang();
        model.addAttribute("listGudang", listGudang);
        return "list-gudang";
    }

    @GetMapping("/{id}")
    public String detailGudang(@PathVariable("id") Long id, Model model) {
        Gudang gudang = gudangService.getGudangById(id);
        if (gudang != null) {
            List<Barang> barangList = gudangService.getBarangByGudangId(id);
            model.addAttribute("gudang", gudang);
            model.addAttribute("barangList", barangList);
        }
        return "detail-gudang";
    }
    
@GetMapping("/{idGudang}/restock-barang")
public String restockBarangForm(@PathVariable("idGudang") Long idGudang, Model model) {
    Gudang gudang = gudangService.getGudangById(idGudang);

    // Create a new RestockForm object using Lombok-generated no-args constructor
    RestockForm restockForm = new RestockForm();
    restockForm.getRestockItems().add(new RestockItem());

    model.addAttribute("gudang", gudang);
    model.addAttribute("restockForm", restockForm);
    List<Barang> listBarang = barangService.getAllBarang(); // Change this method based on your service
    model.addAttribute("listBarang", listBarang);

    return "restock-barang";
}
@GetMapping("/cari-barang")
public String cariBarang(@RequestParam(value = "sku", required = false) String sku, Model model) {
    List<Barang> daftarBarang = barangService.findAllSortedByName(); // Mendapatkan daftar barang terurut alfabetik
    model.addAttribute("daftarBarang", daftarBarang);

    if (sku != null) {
        List<Gudang> listGudang = gudangBarangService.findGudangByBarangSku(sku); // Gantilah ini dengan logika yang sesuai
        model.addAttribute("listGudang", listGudang);

        // Mengambil stok untuk setiap gudang dan menyimpannya dalam Map
        Map<Long, Integer> stokMap = new HashMap<>();
        for (Gudang gudang : listGudang) {
            Integer stok = gudangBarangService.findStokByGudangIdAndBarangSku(gudang.getId(), sku);
            stokMap.put(gudang.getId(), stok);
        }
        model.addAttribute("stokMap", stokMap);
    }

    return "cari-barang"; // Nama template Thymeleaf untuk halaman pencarian dan hasil pencarian
}




@PostMapping("/{idGudang}/restock-barang")
public String restockBarang(@PathVariable("idGudang") Long idGudang, @ModelAttribute RestockForm restockForm, Model model) {
    Gudang gudang = gudangService.getGudangById(idGudang);
    for (RestockItem restockItem : restockForm.getRestockItems()) {
        if (restockItem.getBarangSku() != null && restockItem.getStok() > 0) {
            // Ambil objek Barang berdasarkan SKU
            Barang barang = barangService.getBarangBySku(restockItem.getBarangSku());

            if (barang != null) {
                // Cek apakah barang sudah ada dalam daftar GudangBarang
                boolean barangFound = false;
                for (GudangBarang gudangBarang : gudang.getGudangBarangList()) {
                    if (gudangBarang.getBarang().getSku().equals(restockItem.getBarangSku())) {
                        // Jika barang sudah ada dalam daftar GudangBarang, tambahkan jumlah stok
                        int jumlahStok = restockItem.getStok();
                        int stokBaru = gudangBarang.getStok() + jumlahStok;
                        gudangBarang.setStok(stokBaru);
                        barangFound = true;
                        break;
                    }
                }
                
                if (!barangFound) {
                    // Jika barang belum ada dalam daftar GudangBarang, tambahkan GudangBarang baru
                    GudangBarang newGudangBarang = new GudangBarang();
                    newGudangBarang.setBarang(barang);
                    newGudangBarang.setStok(restockItem.getStok());
                    
                    // Set the gudang property of GudangBarang
                    newGudangBarang.setGudang(gudang);
                    
                    // Save the new GudangBarang using the appropriate service
                    gudangBarangService.saveGudangBarang(newGudangBarang);
                }
            }
        }
    }

    // Redirect ke halaman detail gudang
    return "redirect:/gudang/{idGudang}";
}





@PostMapping(value = "/{idGudang}/restock-barang", params = {"addRow"})
public String addRowRestockItem(@PathVariable("idGudang") Long idGudang, @ModelAttribute RestockForm restockForm, Model model) {
    restockForm.getRestockItems().add(new RestockItem());

    Gudang gudang = gudangService.getGudangById(idGudang);
    model.addAttribute("gudang", gudang);
    model.addAttribute("restockForm", restockForm);
    List<Barang> listBarang = barangService.getAllBarang();
    model.addAttribute("listBarang", listBarang);

    return "restock-barang";
}

@PostMapping(value = "/{idGudang}/restock-barang", params = {"deleteRow"})
public String deleteRowRestockItem(@PathVariable("idGudang") Long idGudang, @ModelAttribute RestockForm restockForm, @RequestParam("deleteRow") int row, Model model) {
    restockForm.getRestockItems().remove(row); // Remove the specified RestockItem

    Gudang gudang = gudangService.getGudangById(idGudang);
    model.addAttribute("gudang", gudang);
    model.addAttribute("restockForm", restockForm);

    return "restock-barang";
}


}