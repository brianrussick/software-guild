package com.sg.btbb.controller;

import com.sg.btbb.dao.BTBBdao;
import com.sg.btbb.dao.BTBBPersistenceException;
import com.sg.btbb.model.*;
import com.sg.btbb.model.Tags;
import java.util.List;
import javax.inject.Inject;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author brian russick
 */
@CrossOrigin
@RestController
public class BTBBRestController {

    @Inject
    private BTBBdao dao;

    // designation role methods
    @GetMapping("/roles")
    public List<DesignationRole> getAllRoles() throws BTBBPersistenceException {
	return dao.getAllRoles();
    }

    @PostMapping("/role")
    @ResponseStatus(HttpStatus.CREATED)
    public DesignationRole createRole(@Valid @RequestBody DesignationRole designationRole) throws BTBBPersistenceException {
	return dao.addRole(designationRole);
    }

    @GetMapping("/role/{id}")
    public DesignationRole getRoleById(@PathVariable("id") int id) throws BTBBPersistenceException {
	return dao.getRoleById(id);
    }

    @PutMapping("/role/{id}")
    public DesignationRole udpateRole(@PathVariable("id") int id, @Valid @RequestBody DesignationRole designationRole) 
            throws BTBBPersistenceException {
	return dao.updateRole(designationRole);
    }

    @DeleteMapping("/role/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public int deleteRole(@PathVariable("id") int id) throws BTBBPersistenceException {
	return dao.deleteRole(id);
    }

    // tag methods
    @GetMapping("/tags")
    public List<Tags> getAllTags() throws BTBBPersistenceException {
	return dao.getAllTags();
    }

    @PostMapping("/tag")
    @ResponseStatus(HttpStatus.CREATED)
    public Tags createTag(@Valid @RequestBody Tags tags) throws BTBBPersistenceException {      
        return dao.addTags(tags);
    }

    @GetMapping("/tag/{id}")
    public Tags getTagById(@PathVariable("id") int id) throws BTBBPersistenceException {
	return dao.getTagById(id);
    }

    @DeleteMapping("/tag/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public int deleteTag(@PathVariable("id") int id) throws BTBBPersistenceException {
        return dao.deleteTags(id);
    }

    @PutMapping("/tag/{id}")
    public void udpateTag(@PathVariable("id") int id, @Valid @RequestBody Tags tags) throws BTBBPersistenceException {
	dao.updateTags(tags);
    }

    // post methods
    @GetMapping("/posts")
    public List<NewPost> getAllPosts() throws BTBBPersistenceException {
	return dao.getAllPosts();
    }
    
    @GetMapping("/posts/{userId}")
    public List<NewPost> getPostsByUserId(@PathVariable("userId") int id) throws BTBBPersistenceException {
        return dao.getAllPostsByUserId(id);
    }

    @GetMapping("/topPost")
    public List<NewPost> getTopPosts() throws BTBBPersistenceException {
        return dao.getTopPosts();
    }

    @GetMapping("/post/{id}")
    public NewPost getPostById(@PathVariable("id") int id) throws BTBBPersistenceException {
        return dao.getPostById(id);
    }

    @PutMapping("/post/{id}")
    public NewPost udpatePost(@PathVariable("id") int id, @Valid @RequestBody NewPost newPost) throws BTBBPersistenceException {	
	return dao.updatePost(newPost);
    }

    @DeleteMapping("/post/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public int deletePost(@PathVariable("id") int id) throws BTBBPersistenceException {
        return dao.deletePost(id);
    }

    @PostMapping("/approvePost/{id}")
    public NewPost approvePost(@PathVariable("id") int postId) throws BTBBPersistenceException {
	NewPost newPost = dao.getPostById(postId);
	PostApprovalStatus as = dao.getApprovalStatusById(2);
	newPost.setPostApprovalStatus(as);
	return dao.updatePost(newPost);
    }


    @GetMapping("/getUnapprovedPosts")
    public List<NewPost> getUnapprovedPosts() throws BTBBPersistenceException {
	return dao.getAllPostsByApprovalStatusId(1);
    }

    // approval status methods
    @GetMapping("/approvals")
    public List<PostApprovalStatus> getAllApprovalStatus() throws BTBBPersistenceException {
	return dao.getAllApprovalStatus();
    }

    @PostMapping("/approval")
    @ResponseStatus(HttpStatus.CREATED)
    public PostApprovalStatus createApprovalStatus( @Valid @RequestBody PostApprovalStatus status) throws BTBBPersistenceException {	
	return dao.addApprovalStatus(status);
    }

    @GetMapping("/approval/{id}")
    public PostApprovalStatus getApprovalStatusById(@PathVariable("id") int id) throws BTBBPersistenceException {
	return dao.getApprovalStatusById(id);
    }

    @DeleteMapping("/approval/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public int deleteApprovalStatus(@PathVariable("id") int id)
            throws BTBBPersistenceException {
        return dao.deleteApprovalStatus(id);
    }

    @PutMapping("/approval/{id}")
    public PostApprovalStatus udpateApprovalStatus(@PathVariable("id") int id, @Valid @RequestBody PostApprovalStatus status) 
            throws BTBBPersistenceException {
	return dao.updateApprovalStatus(status);
    }
    
    // user methods
    @GetMapping("/users")
    public List<User> getAllUsers() throws BTBBPersistenceException {
	return dao.getAllUsers();
    }

    @GetMapping("/user/{id}")
    public User getUserById(@PathVariable("id") int id) throws BTBBPersistenceException {
        return dao.getUserById(id);
    }

    @DeleteMapping("/user/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUserById(@PathVariable("id") int id) throws BTBBPersistenceException {
        dao.deleteUser(id);
    }

    @PutMapping("/user/{id}")
    public void udpateUser(@PathVariable("id") int id, @Valid @RequestBody User user) throws BTBBPersistenceException {	
	dao.updateUser(user);
    }
}