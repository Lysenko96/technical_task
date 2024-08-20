package technikal.task.fishmarket.models;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FishDto {

    @NotEmpty(message = "потрібна назва рибки")
    private String name;
    @Min(0)
    private double price;
    private List<MultipartFile> imageFiles;

}
