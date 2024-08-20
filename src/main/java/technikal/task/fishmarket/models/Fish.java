package technikal.task.fishmarket.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "fish")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Fish {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	private String name;
	private double price;
	private Date catchDate;
	@OneToMany(mappedBy = "fish", cascade = CascadeType.ALL)
	private List<ImageFile> imageFileNameList = new ArrayList<>();

	public void addImageFile(ImageFile imageFile) {
		imageFile.setFish(this);
		imageFileNameList.add(imageFile);
	}
}
