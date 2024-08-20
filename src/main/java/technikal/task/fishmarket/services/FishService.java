package technikal.task.fishmarket.services;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import technikal.task.fishmarket.models.Fish;
import technikal.task.fishmarket.models.FishDto;
import technikal.task.fishmarket.models.ImageFile;
import technikal.task.fishmarket.repo.FishRepository;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class FishService {

    private static final Logger log = LoggerFactory.getLogger(FishService.class);
    private final FishRepository repo;

    public void save(FishDto fishDto) {

        List<MultipartFile> images = fishDto.getImageFiles();
        Date catchDate = new Date();

        List<String> storageFileNameList = new ArrayList<>();
        for (MultipartFile image : images) {
            String storageFileName = catchDate.getTime() + "_" + image.getOriginalFilename();
            storageFileNameList.add(storageFileName);

            try {
                String uploadDir = "public/images/";
                Path uploadPath = Paths.get(uploadDir);

                if (!Files.exists(uploadPath)) {
                    Files.createDirectories(uploadPath);
                }


                try (InputStream inputStream = image.getInputStream()) {
                    Files.copy(inputStream, Paths.get(uploadDir + storageFileName), StandardCopyOption.REPLACE_EXISTING);
                }
            } catch (Exception ex) {
                System.out.println("Exception: " + ex.getMessage());
            }
        }

        Fish fish = new Fish();

        for (String storageFileName : storageFileNameList) {
            ImageFile imageFile = new ImageFile(storageFileName);
            fish.addImageFile(imageFile);
        }

        fish.setCatchDate(catchDate);
        fish.setPrice(fishDto.getPrice());
        fish.setName(fishDto.getName());

        repo.save(fish);

    }

    public void deleteFish(int id) {
        try {

            Fish fish = repo.findById(id).orElse(null);
            if (fish == null) return;

            Path imagePath = Paths.get("public/images/" + fish.getImageFileNameList());
            Files.delete(imagePath);
            repo.delete(fish);

        } catch (Exception ex) {
            System.out.println("Exception: " + ex.getMessage());
        }
    }

    public List<Fish> findAll() {
        return repo.findAll();
    }
}
