package com.paul9537.memo.post.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.paul9537.memo.post.dao.PostDAO;
import com.paul9537.memo.post.model.Post;

@Service
public class PostBO {

	@Autowired
	private PostDAO postDAO;
	
	public int addPost(int userId, String subject, String content, MultipartFile file) {
		
		// 파일을 저장하고, 경로를 만들어 낸다.
		
		
		return postDAO.insertPost(userId, subject, content);
	}
	
	public List<Post> getPostList(int userId) {
		return postDAO.selectPostList(userId);
	}
	
	public Post getPost(int id) {
		return postDAO.selectPost(id);
	}
	
}
