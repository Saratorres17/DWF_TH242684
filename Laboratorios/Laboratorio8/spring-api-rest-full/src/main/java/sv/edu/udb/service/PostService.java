package sv.edu.udb.service;

import sv.edu.udb.controller.request.PostRequest;
import sv.edu.udb.controller.response.PostResponse;

import java.util.List;

public interface PostService {
    List<PostResponse> findAll();
    PostResponse findById(Long id);
    PostResponse save(PostRequest postRequest);
    PostResponse update(Long id, PostRequest postRequest);
    void delete(Long id);
}
