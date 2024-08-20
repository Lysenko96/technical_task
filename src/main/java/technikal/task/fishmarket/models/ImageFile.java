package technikal.task.fishmarket.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "image_file")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ImageFile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String fileName;
    @ToString.Exclude
    @ManyToOne(optional = false)
    @JoinColumn(name = "fish_id")
    private Fish fish;

    public ImageFile(String fileName) {
        this.fileName = fileName;
    }
}
