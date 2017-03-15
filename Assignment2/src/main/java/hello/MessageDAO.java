package hello;
import org.springframework.data.repository.CrudRepository;

public interface MessageDAO extends CrudRepository<Greeting,Integer > {

}
