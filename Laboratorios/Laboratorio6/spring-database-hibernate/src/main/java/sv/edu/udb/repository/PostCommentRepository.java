package sv.edu.udb.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import sv.edu.udb.repository.domain.PostComment;

import java.util.List;

@Repository
public class PostCommentRepository {

    private final SessionFactory sessionFactory;

    public PostCommentRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    private Session session() {
        return sessionFactory.getCurrentSession();
    }

    @Transactional(readOnly = true)
    public List<PostComment> findAll() {
        return session().createQuery("from PostComment pc", PostComment.class).getResultList();
    }

    @Transactional(readOnly = true)
    public PostComment findById(Long id) {
        return session().find(PostComment.class, id);
    }

    @Transactional
    public void save(PostComment comment) {
        session().persist(comment);
    }

    @Transactional
    public void delete(PostComment comment) {
        session().remove(comment);
    }
}
