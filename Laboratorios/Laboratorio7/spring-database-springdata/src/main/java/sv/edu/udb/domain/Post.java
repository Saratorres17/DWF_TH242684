package sv.edu.udb.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "post")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor @Builder
@ToString(exclude = "comments")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "post_date", nullable = false)
    private LocalDate postDate;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<PostComment> comments = new ArrayList<>();

    // Helpers para mantener ambos lados de la relación
    public void addComment(PostComment comment) {
        comment.setPost(this);
        comments.add(comment);
    }

    public void removeComment(PostComment comment) {
        comment.setPost(null);
        comments.remove(comment);
    }
}
