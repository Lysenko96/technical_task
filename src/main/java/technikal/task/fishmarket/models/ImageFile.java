package technikal.task.fishmarket.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

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
