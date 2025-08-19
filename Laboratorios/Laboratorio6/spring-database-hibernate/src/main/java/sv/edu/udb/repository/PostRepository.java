package sv.edu.udb.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import sv.edu.udb.repository.domain.Post;

import java.util.List;

@Repository
public class PostRepository {

    private final SessionFactory sessionFactory;

    public PostRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    private Session session() {
        return sessionFactory.getCurrentSession();
    }

    @Transactional(readOnly = true)
    public List<Post> findAll() {
        return session().createQuery("from Post p", Post.class).getResultList();
    }

    @Transactional(readOnly = true)
    public Post findById(Long id) {
        return session().find(Post.class, id);
    }

    @Transactional
    public void save(Post post) {
        session().persist(post);
    }

    @Transactional
    public void delete(Post post) {
        session().remove(post);
    }

    @Transactional
    public void deleteById(Long id) {
        session().createMutationQuery("delete from Post p where p.id = :id")
                .setParameter("id", id)
                .executeUpdate();
    }
}
