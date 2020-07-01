package com.sg.btbb.controller;

import com.sg.btbb.dao.BTBBdao;
import com.sg.btbb.dao.BTBBPersistenceException;
import com.sg.btbb.model.Tags;
import com.sg.btbb.model.PostApprovalStatus;
import com.sg.btbb.model.NewPost;
import com.sg.btbb.model.User;
import java.security.Principal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author brian russick
 */
@Controller
public class NewPostController {
    
    @Inject
    private BTBBdao dao;
    
    @PostMapping("/createnewpost")
    public String createPost(HttpServletRequest request, Principal userPrincipal)
            throws BTBBPersistenceException {
        
        String un = userPrincipal.getName(); // username = principal
        int currentUserId = 1;
        
        for (User currentUser : dao.getAllUsers()) {
            if (un.equals(currentUser.getUserName())) {
                currentUserId = currentUser.getUserId();
                break;
            }
        }
        
        NewPost newPost = new NewPost();
        String blogTitle = request.getParameter("blogTitle");
        String content = request.getParameter("postHtml");
        String stringDate = request.getParameter("date");
        stringDate += " 00:00";
        String imageLocation = request.getParameter("thumbnail");
        String tags = request.getParameter("tag");
        Tags tag = new Tags(tags);
        List<Tags> tagslist = new ArrayList<>();
        tagslist.add(tag);
        
        PostApprovalStatus as = dao.getApprovalStatusById(1);
        
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime blogPostDate = LocalDateTime.parse(stringDate, df);
        
        newPost.setBlogTitle(blogTitle);
        newPost.setContent(content);
        newPost.setPostApprovalStatus(as);
        newPost.setBlogPostDate(blogPostDate);
        newPost.setImageLocation(imageLocation);
        newPost.setUser(dao.getUserById(currentUserId));
        newPost.setTagsList(tagslist);
        
        dao.addPost(newPost);
        
        return "redirect:/newPost.html";
    }
}