package technikal.task.fishmarket.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import technikal.task.fishmarket.models.Fish;

import java.util.List;

@Repository
public interface FishRepository extends JpaRepository<Fish, Integer> {

    @Override
    @Query("SELECT f FROM Fish f JOIN FETCH f.imageFileNameList l ORDER BY f.id DESC")
    List<Fish> findAll();
}
