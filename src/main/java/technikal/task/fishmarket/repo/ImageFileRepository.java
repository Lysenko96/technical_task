package technikal.task.fishmarket.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import technikal.task.fishmarket.models.ImageFile;

import java.util.List;

@Repository
public interface ImageFileRepository extends JpaRepository<ImageFile, Integer> {

    List<ImageFile> findAllByFishId(int fishId);

    @Modifying
//    @Transactional
    @Query("DELETE FROM ImageFile i WHERE i.id IN (:ids)")
    void deleteAllByIdIn(List<Integer> ids);
}
