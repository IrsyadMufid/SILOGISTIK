package apap.ti.silogistik2106752073.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RestockForm {
    private List<RestockItem> restockItems = new ArrayList<>();
}
