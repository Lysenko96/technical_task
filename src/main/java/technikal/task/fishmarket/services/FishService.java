package technikal.task.fishmarket.services;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import technikal.task.fishmarket.models.Fish;
import technikal.task.fishmarket.models.FishDto;
import technikal.task.fishmarket.models.ImageFile;
import technikal.task.fishmarket.repo.FishRepository;
import technikal.task.fishmarket.repo.ImageFileRepository;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class FishService {

    private static final Logger log = LoggerFactory.getLogger(FishService.class);
    private final FishRepository repo;
    private final ImageFileRepository imageFileRepository;

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

        List<ImageFile> imageFiles = new ArrayList<>();
        for (String storageFileName : storageFileNameList) {
            ImageFile imageFile = new ImageFile(storageFileName);
            fish.addImageFile(imageFile);
            imageFiles.add(imageFile);
        }

        fish.setCatchDate(catchDate);
        fish.setImageFileNameList(imageFiles);
        fish.setPrice(fishDto.getPrice());
        fish.setName(fishDto.getName());

        repo.save(fish);
        imageFileRepository.saveAll(imageFiles);

    }

    public void deleteFish(int id) {
        try {

            Fish fish = repo.findById(id).orElse(null);

            if (fish == null) return;

            for(ImageFile file : fish.getImageFileNameList()) {
                Path imagePath = Paths.get("public/images/" + file.getFileName());
                Files.delete(imagePath);
            }
            List<ImageFile> imageFiles = imageFileRepository.findAllByFishId(fish.getId());
            List<Integer> imageFilesId = imageFiles.stream().map(ImageFile::getId).collect(Collectors.toList());
            imageFileRepository.deleteAllByIdIn(imageFilesId);
            repo.deleteById(fish.getId());

        } catch (Exception ex) {
            System.out.println("Exception: " + ex.getMessage());
        }
    }

    @Transactional(readOnly = true)
    public List<Fish> findAll() {
        return repo.findAll();
    }
}
