package sv.edu.udb.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import sv.edu.udb.domain.Post;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class PostRepositoryTest {

    @Autowired
    private PostRepository postRepository;

    @Test
    void save_shouldPersistAndReturnId() {
        Post newPost = Post.builder()
                .title("Anything you want to write")
                .postDate(LocalDate.of(2024, 8, 24))
                .build();

        Post saved = postRepository.save(newPost);

        assertNotNull(saved.getId());
        Post found = postRepository.findById(saved.getId()).orElse(null);
        assertNotNull(found);
        assertEquals("Anything you want to write", found.getTitle());
        assertEquals(LocalDate.of(2024, 8, 24), found.getPostDate());
    }

    @Test
    void findAll_shouldReturnExpectedCount() {
        Post p1 = Post.builder().title("First").postDate(LocalDate.of(2024, 8, 24)).build();
        Post p2 = Post.builder().title("Second").postDate(LocalDate.of(2024, 8, 24)).build();
        postRepository.save(p1);
        postRepository.save(p2);

        List<Post> all = postRepository.findAll();
        assertNotNull(all);
        assertEquals(2, all.size());
    }

    @Test
    void findById_shouldReturnRecord() {
        Post p = Post.builder().title("Find me").postDate(LocalDate.of(2024, 8, 24)).build();
        Post saved = postRepository.save(p);

        Post found = postRepository.findById(saved.getId()).orElse(null);
        assertNotNull(found);
        assertEquals(saved.getId(), found.getId());
        assertEquals("Find me", found.getTitle());
    }

    @Test
    void delete_shouldRemoveRecord() {
        Post p = Post.builder().title("To delete").postDate(LocalDate.of(2024, 8, 24)).build();
        Post saved = postRepository.save(p);
        Long id = saved.getId();

        postRepository.delete(saved);

        assertTrue(postRepository.findById(id).isEmpty());
    }
}
