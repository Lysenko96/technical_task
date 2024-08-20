package technikal.task.fishmarket.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.BatchSize;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "fish")
@Data
@NoArgsConstructor
@AllArgsConstructor
@BatchSize(size = 20)
public class Fish {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private double price;
    private Date catchDate;
    @OneToMany(mappedBy = "fish")
    private List<ImageFile> imageFileNameList = new ArrayList<>();

    public void addImageFile(ImageFile imageFile) {
        imageFile.setFish(this);
        imageFileNameList.add(imageFile);
    }
}
