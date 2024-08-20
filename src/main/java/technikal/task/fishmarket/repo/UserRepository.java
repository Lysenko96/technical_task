package technikal.task.fishmarket.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import technikal.task.fishmarket.models.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    User findUserByUsername(String name);
}
