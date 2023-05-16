package com.driver.services;

import com.driver.models.Blog;
import com.driver.models.User;
import com.driver.repositories.BlogRepository;
import com.driver.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BlogService {
    @Autowired
    BlogRepository blogRepository1;

    @Autowired
    UserRepository userRepository1;

    public Blog createAndReturnBlog(Integer userId, String title, String content) {
        //create a blog at the current time

        Blog blog = new Blog(title,content);

        User user = userRepository1.findById(userId).get();
        blog.setUser(user);
        user.getBlogList().add(blog);
        userRepository1.save(user);
        return blog;
    }

    public void deleteBlog(int blogId){
        //delete blog and corresponding images

        Optional<Blog> optionalBlog = blogRepository1.findById(blogId);
//        if(!optionalBlog.isPresent()) return;
        Blog blog = optionalBlog.get();
        User user = blog.getUser();
        user.getBlogList().remove(blog);
        userRepository1.save(user);

        blogRepository1.deleteById(blogId);
        return;
    }
}
