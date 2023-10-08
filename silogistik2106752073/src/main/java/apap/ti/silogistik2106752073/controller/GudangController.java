package apap.ti.silogistik2106752073.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import apap.ti.silogistik2106752073.model.Gudang;
import apap.ti.silogistik2106752073.service.GudangService;

@Controller
@RequestMapping("/gudang")
public class GudangController {
    @Autowired
    private GudangService gudangService;

    @GetMapping
    public String listGudang(Model model) {
        List<Gudang> listGudang = gudangService.getAllGudang();
        model.addAttribute("listGudang", listGudang);
        return "list-gudang";
    }

    @GetMapping("/{id}")
    public String detailGudang(@PathVariable("id") Long id, Model model) {
        // Mendapatkan gudang melalui ID
        Gudang gudang = gudangService.getGudangById(id);

        // Menambahkan gudang ke model
        model.addAttribute("gudang", gudang);

        return "detail-gudang";
    }
}