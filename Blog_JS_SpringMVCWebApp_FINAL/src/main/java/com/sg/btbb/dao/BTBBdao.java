package com.sg.btbb.dao;

import com.sg.btbb.model.*;
import com.sg.btbb.model.Tags;
import java.util.List;

/**
 *
 * @author brian russick
 */
public interface BTBBdao {
    
    // get methods 
    public User getUserById(int userId) throws BTBBPersistenceException;
    
    public List<User> getAllUsers() throws BTBBPersistenceException;
    
    public List<User> getAllUsersByRoleId(int roleId) throws BTBBPersistenceException;
    
    public Tags getTagById(int categoryId) throws BTBBPersistenceException;

    public List<Tags> getAllTags() throws BTBBPersistenceException;
    
    public PostApprovalStatus getApprovalStatusById(int id)
            throws BTBBPersistenceException;

    public List<PostApprovalStatus> getAllApprovalStatus() throws BTBBPersistenceException;

    public PostApprovalStatus getApprovalStatusByDescription(String description)
            throws BTBBPersistenceException;

    public DesignationRole getRoleById(int roleId) throws BTBBPersistenceException;

    public List<DesignationRole> getAllRoles() throws BTBBPersistenceException;

    public NewPost getPostById(int postId) throws BTBBPersistenceException;

    public List<NewPost> getAllPosts() throws BTBBPersistenceException;

    public List<NewPost> getTopPosts() throws BTBBPersistenceException;

    public List<NewPost> getAllPostsByUserId(int userId)
            throws BTBBPersistenceException;

    public List<NewPost> getAllPostsByApprovalStatusId(int statusId)
            throws BTBBPersistenceException;

    // add methods  
    public Tags addTags(Tags tags) throws BTBBPersistenceException;

    public User addUser(User user) throws BTBBPersistenceException;

    public DesignationRole addRole(DesignationRole designationRole) throws BTBBPersistenceException;

    public NewPost addPost(NewPost newPost) throws BTBBPersistenceException;

    public PostApprovalStatus addApprovalStatus(PostApprovalStatus status)
            throws BTBBPersistenceException;

    // delete methods

    public int deleteUser(int userId) throws BTBBPersistenceException;

    public int deletePost(int postId) throws BTBBPersistenceException;
    
    public int deleteAllPostsByUserId(int userId) throws BTBBPersistenceException;

    public int deleteRole(int roleId) throws BTBBPersistenceException;

    public int deleteTags(int categoryId)throws BTBBPersistenceException;

    public int deleteApprovalStatus(int id) throws BTBBPersistenceException;

    // update methods 

    public User updateUser(User user) throws BTBBPersistenceException;

    public NewPost updatePost(NewPost newPost) throws BTBBPersistenceException;

    public Tags updateTags(Tags tags) throws BTBBPersistenceException;

    public PostApprovalStatus updateApprovalStatus(PostApprovalStatus status)
            throws BTBBPersistenceException;

    public DesignationRole updateRole(DesignationRole designationRole) throws BTBBPersistenceException; 
}