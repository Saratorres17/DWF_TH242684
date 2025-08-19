package sv.edu.udb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sv.edu.udb.domain.Post;

public interface PostRepository extends JpaRepository<Post, Long> {
}
