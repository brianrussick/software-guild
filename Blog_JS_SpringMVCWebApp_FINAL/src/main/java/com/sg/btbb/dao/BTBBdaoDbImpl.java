package com.sg.btbb.dao;

import com.sg.btbb.model.*;
import com.sg.btbb.model.Tags;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author brian russick
 */
public class BTBBdaoDbImpl implements BTBBdao {
    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // sql statements   
    // posts
    private static final String SELECT_POST_BY_ID
            = "select * from blog_post where blogPostId = ?";

    private static final String SELECT_ALL_POSTS_BY_USER_ID
            = "select * from blog_post where userId = ?";

    private static final String SELECT_ALL_POSTS
            = "select * from blog_post";

    private static final String SELECT_TOPTWOPOST
            ="SELECT * FROM blog_post where approvalStatusId = '2' order by blogPostId desc LIMIT 2";

    private static final String SELECT_APPROVAL_ID_BY_POST_ID
            = "select approvalStatusId "
            + "from blog_post "
            + "where blogPostId = ?";

    private static final String SELECT_USER_ID_BY_POST_ID
            = "select userId from blog_post where blogPostId = ?";

    private static final String SELECT_POSTS_BY_APPROVAL_STATUS_ID
            = "select * from blog_post "
            + "where approvalStatusId = ?";
    
    private static final String INSERT_POST
            = "insert into blog_post "
            + "(blogTitle, content, approvalStatusId, blogPostDate, "
            + "imageLocation, userId) "
            + "values (?, ?, ?, ?, ?, ?)";

    private static final String DELETE_POST
            = "delete from blog_post where blogPostId = ?";

    private static final String DELETE_ALL_POSTS_BY_USER_ID
            = "delete from blog_post where userId = ?";

    private static final String UPDATE_POST
            = "update blog_post "
            + "set blogTitle = ?, content = ?, approvalStatusId = ?, "
            + "blogPostDate = ?,  imageLocation = ?, userId = ? "
            + "where blogPostId = ?";

    // tags
    private static final String INSERT_TAGS
            = "insert into tags (name) values (?)";

    private static final String DELETE_TAGS
            = "delete from tags where tagId = ?";

    private static final String UPDATE_TAGS
            = "update tags "
            + "set name = ? "
            + "where tagId = ?";

    private static final String SELECT_TAGS_BY_ID
            = "select * from tags where tagId = ?";

    private static final String SELECT_ALL_TAGS
            = "select * from tags";

    private static final String INSERT_POST_TAGS
            = "insert into blog_post_tags (blogPostId, tagId) "
            + "values (?, ?)";

    private static final String SELECT_TAG_ID_BY_BLOG_POST_ID
            = "select tagId "
            + "from blog_post_tags "
            + "where blogPostId = ?";

    private static final String DELETE_TAGS_POST_BY_POST_ID
            = "delete from blog_post_tags where blogPostId = ?";

    private static final String DELETE_TAGS_POST_BY_TAG_ID
            = "delete from blog_post_tags where tagId = ?";

    // designation roles
    private static final String INSERT_ROLE
            = "insert into designation_role (Name) values (?)";

    private static final String DELETE_ROLE
            = "delete from designation_role where RoleId = ?";

    private static final String UPDATE_ROLE
            = "update designation_role "
            + "set Name = ? "
            + "where RoleId = ?";

    private static final String SELECT_ROLE_BY_ID
            = "select * from designation_role where RoleId = ?";

    private static final String SELECT_ALL_ROLES
            = "select * from designation_role";

    private static final String SELECT_ROLES_BY_USER_ID
            = "select roleId from user_designation_role where userId = ?";

    private static final String INSERT_USER_ROLE
            = "insert into user_designation_role (userId, roleId) "
            + "values (?, ?)";

    private static final String DELETE_USER_ROLE_BY_ROLE_ID
            = "delete from user_designation_role where roleId = ?";

    private static final String DELETE_USER_ROLE_BY_USER_ID
            = "delete from user_designation_role where userId = ?";     
    
    // approval status
    private static final String INSERT_APPROVAL_STATUS
            = "insert into post_approval_status (description) "
            + "values (?)";

    private static final String DELETE_APPROVAL_STATUS
            = "delete from post_approval_status where approvalStatusId = ?";

    private static final String UPDATE_APPROVAL_STATUS
            = "update post_approval_status "
            + "set description = ? "
            + "where approvalStatusId = ?";

    private static final String SELECT_APPROVAL_STATUS_BY_ID
            = "select * from post_approval_status where approvalStatusId = ?";

    private static final String SELECT_ALL_APPROVAL_STATUS
            = "select * from post_approval_status";
    
    private static final String SELECT_APPROVAL_STATUS_BY_DESCRIPTION 
            = "select * from post_approval_status where description like ?";    

    // user queries
    private static final String INSERT_USER
            = "insert into user (name, userName, password, email) "
            + "values (?, ?, ?, ?)";

    private static final String DELETE_USER
            = "delete from user where userId = ?";

    private static final String UPDATE_USER
            = "update user "
            + "set name = ?, userName = ?, password = ?, email = ? "
            + "where userId = ?";

    private static final String SELECT_USER_BY_ID
            = "select * from user where UserId = ?";

    private static final String SELECT_ALL_USERS
            = "select * from user";

    private static final String SELECT_ALL_USERS_BY_ROLE_ID
            = "select * from user u "
            + "join user_role ur on u.userId = ur.userId "
            + "where ur.roleId = ?";
    
    // methods
    
    @Override
    @Transactional
    public NewPost addPost(NewPost newPost) throws BTBBPersistenceException {
        try {
            jdbcTemplate.update(INSERT_POST,
                    newPost.getBlogTitle(),
                    newPost.getContent(),
                    newPost.getPostApprovalStatus().getApprovalStatusId(),
                    newPost.getBlogPostDate().toString(),
                    newPost.getImageLocation(),
                    newPost.getUser().getUserId());

            newPost.setBlogPostId(jdbcTemplate.queryForObject("select LAST_INSERT_ID()",
                    Integer.class));

            insertTags(newPost.getTagsList());
            insertPostTagsEntries(newPost);

            return newPost;

        } catch (DataAccessException e) {
            throw new BTBBPersistenceException(e.getMessage());
        }
    }

    private void insertTags(List<Tags> tagsList)
            throws BTBBPersistenceException {
        try {
            for (Tags currentTag : tagsList) {
                this.addTags(currentTag);
            }
        } catch (DataAccessException e) {
            throw new BTBBPersistenceException(e.getMessage());
        }
    }

    private void insertPostTagsEntries(NewPost newPost)
            throws BTBBPersistenceException {
        List<Tags> tagsList = newPost.getTagsList();

        if (tagsList.size() <= 0) {
        } else {
            for (Tags currentTag : tagsList) {
                try {
                    jdbcTemplate.update(INSERT_POST_TAGS,
                            newPost.getBlogPostId(),
                            currentTag.getTagId());
                } catch (DataAccessException e) {
                    throw new BTBBPersistenceException(e.getMessage());
                }
            }
        }
    }

    @Override
    @Transactional
    public int deletePost(int blogPostId) throws BTBBPersistenceException {
        try {        
            this.deletePostTagByPostId(blogPostId);

            return jdbcTemplate.update(DELETE_POST, blogPostId);
        } catch (DataAccessException e) {
            throw new BTBBPersistenceException(e.getMessage());
        }
    }

    @Override
    @Transactional
    public int deleteAllPostsByUserId(int userId) throws BTBBPersistenceException {
        try {

            List<NewPost> newPosts = this.getAllPostsByUserId(userId);
            for (NewPost currentNewPost : newPosts) {
                this.deletePostTagByPostId(currentNewPost.getBlogPostId());
            }
       
            return jdbcTemplate.update(DELETE_ALL_POSTS_BY_USER_ID, userId);

        } catch (DataAccessException e) {
            throw new BTBBPersistenceException(e.getMessage());
        }
    }

    private int deletePostTagByPostId(int blogPostId)
            throws BTBBPersistenceException {
        try {
            return jdbcTemplate.update(DELETE_TAGS_POST_BY_POST_ID, blogPostId);
        } catch (DataAccessException e) {
            throw new BTBBPersistenceException(e.getMessage());
        }
    }

    @Override
    @Transactional
    public NewPost updatePost(NewPost newPost) throws BTBBPersistenceException {
        try {
            jdbcTemplate.update(UPDATE_POST,
                    newPost.getBlogTitle(),
                    newPost.getContent(),
                    newPost.getPostApprovalStatus().getApprovalStatusId(),
                    newPost.getBlogPostDate().toString(),
                    newPost.getImageLocation(),
                    newPost.getUser().getUserId(),
                    newPost.getBlogPostId());

            return this.getPostById(newPost.getBlogPostId());
        } catch (DataAccessException e) {
            throw new BTBBPersistenceException(e.getMessage());
        }
    }

    @Override
    @Transactional
    public NewPost getPostById(int blogPostId) throws BTBBPersistenceException {
        try {
            NewPost newPost = jdbcTemplate.queryForObject(SELECT_POST_BY_ID,
                    new PostMapper(),
                    blogPostId);

            associateApprovalStatusWithPost(newPost);
            associateUserWithPost(newPost);
            associateTagsWithPost(newPost);

            return newPost;
        } catch (DataAccessException e) {
            throw new BTBBPersistenceException(e.getMessage());
        }
    }

    @Override
    @Transactional
    public List<NewPost> getAllPostsByUserId(int userId) throws BTBBPersistenceException {
        try {
            List<NewPost> newPosts = jdbcTemplate.query(SELECT_ALL_POSTS_BY_USER_ID,
                    new PostMapper(),
                    userId);

            if (newPosts.size() <= 0) {
            } else {
                for (NewPost currentNewPost : newPosts) {
                    this.associateApprovalStatusWithPost(currentNewPost);
                    this.associateUserWithPost(currentNewPost);
                    this.associateTagsWithPost(currentNewPost);
                }
            }
            return newPosts;

        } catch (DataAccessException e) {
            throw new BTBBPersistenceException(e.getMessage());
        }
    }

    @Override
    @Transactional
    public List<NewPost> getAllPostsByApprovalStatusId(int statusId)
            throws BTBBPersistenceException {

        try {
            List<NewPost> newPosts = jdbcTemplate.query(SELECT_POSTS_BY_APPROVAL_STATUS_ID,
                    new PostMapper(),
                    statusId);

            if (newPosts.size() <= 0) {
            } else {
                for (NewPost currentNewPost : newPosts) {
                    this.associateApprovalStatusWithPost(currentNewPost);
                    this.associateUserWithPost(currentNewPost);
                    this.associateTagsWithPost(currentNewPost);
                }
            }
            return newPosts;

        } catch (DataAccessException e) {
            throw new BTBBPersistenceException(e.getMessage());
        }
    }


    @Override
    @Transactional
    public List<NewPost> getAllPosts() throws BTBBPersistenceException {
        try {
            List<NewPost> newPosts = jdbcTemplate.query(SELECT_ALL_POSTS,
                    new PostMapper());

            if (newPosts.size() <= 0) {
            } else {
                for (NewPost currentNewPost : newPosts) {
                    this.associateApprovalStatusWithPost(currentNewPost);
                    this.associateUserWithPost(currentNewPost);
                    this.associateTagsWithPost(currentNewPost);
                }
            }

            return newPosts;

        } catch (DataAccessException e) {
            throw new BTBBPersistenceException(e.getMessage());
        }
    }

    @Override
    @Transactional
    public List<NewPost> getTopPosts() throws BTBBPersistenceException {
        try{
            List<NewPost> topNewPost = jdbcTemplate.query(SELECT_TOPTWOPOST, new PostMapper());
            if (topNewPost.size() <= 0) {
            } else {
                for (NewPost currentNewPost : topNewPost) {
                    this.associateApprovalStatusWithPost(currentNewPost);
                    this.associateUserWithPost(currentNewPost);
                    this.associateTagsWithPost(currentNewPost);
                }
            }

            return topNewPost;
        }
        catch (DataAccessException e) {
            throw new BTBBPersistenceException(e.getMessage());
        }
    }

    private void associateApprovalStatusWithPost(NewPost newPost)
            throws BTBBPersistenceException {
        try {
            int approvalStatusId
                    = jdbcTemplate.queryForObject(SELECT_APPROVAL_ID_BY_POST_ID,
                            new ApprovalStatusIdMapper(),
                            newPost.getBlogPostId());

            PostApprovalStatus as = this.getApprovalStatusById(approvalStatusId);

            newPost.setPostApprovalStatus(as);
        } catch (DataAccessException e) {
            throw new BTBBPersistenceException(e.getMessage());
        }

    }

    private void associateUserWithPost(NewPost newPost) throws BTBBPersistenceException {
        try {
            int userId = jdbcTemplate.queryForObject(SELECT_USER_ID_BY_POST_ID,
                    new UserIdMapper(),
                    newPost.getBlogPostId());

            User user = this.getUserById(userId);

            newPost.setUser(user);
        } catch (DataAccessException e) {
            throw new BTBBPersistenceException(e.getMessage());
        }
    }

    private void associateTagsWithPost(NewPost newPost)
            throws BTBBPersistenceException {
        try {
            List<Tags> tagsList = new ArrayList<>();

            List<Integer> TagsIds
                    = jdbcTemplate.query(SELECT_TAG_ID_BY_BLOG_POST_ID,
                            new TagIdMapper(),
                            newPost.getBlogPostId());

            if (TagsIds.size() <= 0) {
            } else {
                TagsIds.forEach((currentId) -> {
                    tagsList.add(jdbcTemplate.queryForObject(
                            SELECT_TAGS_BY_ID,
                            new TagMapper(),
                            currentId));
                });
            }
            newPost.setTagsList(tagsList);
        } catch (DataAccessException e) {
            throw new BTBBPersistenceException(e.getMessage());
        }
    }

    @Override
    @Transactional
    public Tags addTags(Tags tags) throws BTBBPersistenceException {
        try {
            jdbcTemplate.update(INSERT_TAGS, tags.getName());

            tags.setTagId(jdbcTemplate.queryForObject("select LAST_INSERT_ID()",
                    Integer.class));

            return tags;
        } catch (DataAccessException e) {
            throw new BTBBPersistenceException(e.getMessage());
        }
    }

    @Override
    @Transactional
    public int deleteTags(int tagId) throws BTBBPersistenceException {
        try {
            jdbcTemplate.update(DELETE_TAGS_POST_BY_TAG_ID, tagId);

            return jdbcTemplate.update(DELETE_TAGS, tagId);
        } catch (DataAccessException e) {
            throw new BTBBPersistenceException(e.getMessage());
        }
    }

    @Override
    @Transactional
    public Tags updateTags(Tags tags)
            throws BTBBPersistenceException {
        try {
            jdbcTemplate.update(UPDATE_TAGS,
                    tags.getName(),
                    tags.getTagId());

            return this.getTagById(tags.getTagId());
        } catch (DataAccessException e) {
            throw new BTBBPersistenceException(e.getMessage());
        }
    }

    @Override
    public Tags getTagById(int tagId) throws BTBBPersistenceException {
        try {
            return jdbcTemplate.queryForObject(SELECT_TAGS_BY_ID,
                    new TagMapper(),
                    tagId);
        } catch (DataAccessException e) {
            throw new BTBBPersistenceException(e.getMessage());
        }
    }

    @Override
    public List<Tags> getAllTags() throws BTBBPersistenceException {
        try {
            return jdbcTemplate.query(SELECT_ALL_TAGS,
                    new TagMapper());
        } catch (DataAccessException e) {
            throw new BTBBPersistenceException(e.getMessage());
        }
    }

    @Override
    @Transactional
    public PostApprovalStatus addApprovalStatus(PostApprovalStatus status)
            throws BTBBPersistenceException {
        try {
            jdbcTemplate.update(INSERT_APPROVAL_STATUS,
                    status.getDescription());

            status.setApprovalStatusId(
                    jdbcTemplate.queryForObject("select LAST_INSERT_ID()",
                            Integer.class));

            return status;
        } catch (DataAccessException e) {
            throw new BTBBPersistenceException(e.getMessage());
        }
    }

    @Override
    public int deleteApprovalStatus(int id) throws BTBBPersistenceException {
        try {
            return jdbcTemplate.update(DELETE_APPROVAL_STATUS, id);
        } catch (DataAccessException e) {
            throw new BTBBPersistenceException(e.getMessage());
        }
    }

    @Override
    @Transactional
    public PostApprovalStatus updateApprovalStatus(PostApprovalStatus status)
            throws BTBBPersistenceException {
        try {
            jdbcTemplate.update(UPDATE_APPROVAL_STATUS,
                    status.getDescription(),
                    status.getApprovalStatusId());

            return this.getApprovalStatusById(status.getApprovalStatusId());
        } catch (DataAccessException e) {
            throw new BTBBPersistenceException(e.getMessage());
        }
    }

    @Override
    public PostApprovalStatus getApprovalStatusById(int id) throws BTBBPersistenceException {
        try {
            return jdbcTemplate.queryForObject(SELECT_APPROVAL_STATUS_BY_ID,
                    new ApprovalStatusMapper(),
                    id);
        } catch (DataAccessException e) {
            throw new BTBBPersistenceException(e.getMessage());
        }
    }

    @Override
    public List<PostApprovalStatus> getAllApprovalStatus() throws BTBBPersistenceException {
        try {
            return jdbcTemplate.query(SELECT_ALL_APPROVAL_STATUS,
                    new ApprovalStatusMapper());
        } catch (DataAccessException e) {
            throw new BTBBPersistenceException(e.getMessage());
        }
    }
    
    @Override
    public PostApprovalStatus getApprovalStatusByDescription(String description)
            throws BTBBPersistenceException {
        
            try {
                return jdbcTemplate.queryForObject(SELECT_APPROVAL_STATUS_BY_DESCRIPTION, 
                        new ApprovalStatusMapper(), 
                        description);
            } catch (DataAccessException e) {
                throw new BTBBPersistenceException(e.getMessage());
            }
    }
    
    @Override
    @Transactional
    public User addUser(User user) throws BTBBPersistenceException {
        try {
            jdbcTemplate.update(INSERT_USER,
                    user.getName(),
                    user.getUserName(),
                    user.getPassword(),
                    user.getEmail());

            user.setUserId(jdbcTemplate.queryForObject("select LAST_INSERT_ID()",
                    Integer.class));

            insertUserRoleEntries(user);
            return user;
        } catch (DataAccessException e) {
            throw new BTBBPersistenceException(e.getMessage());
        }
    }

    private void insertUserRoleEntries(User user) throws BTBBPersistenceException {
        try {
            List<DesignationRole> designationRoles = user.getDesignationRoles();

            if (designationRoles == null) {
            } else {
                designationRoles.forEach((currentDesignationRole) -> {
                    jdbcTemplate.update(INSERT_USER_ROLE,
                            user.getUserId(),
                            currentDesignationRole.getRoleId());
                });
            }
        } catch (DataAccessException e) {
            throw new BTBBPersistenceException(e.getMessage());
        }
    }

    @Override
    @Transactional
    public User getUserById(int userId) throws BTBBPersistenceException {
        try {
            User user = jdbcTemplate.queryForObject(SELECT_USER_BY_ID,
                    new UserMapper(),
                    userId);

            associateRolesWithUser(user);
            return user;
        } catch (DataAccessException e) {
            throw new BTBBPersistenceException(e.getMessage());
        }
    }

    @Override
    @Transactional
    public List<User> getAllUsers() throws BTBBPersistenceException {
        try {
            List<User> users = jdbcTemplate.query(SELECT_ALL_USERS, new UserMapper());

            if (users.size() <= 0) {
            } else {
                users.forEach((currentUser) -> {
                    this.associateRolesWithUser(currentUser);
                });
            }
            return users;
        } catch (DataAccessException e) {
            throw new BTBBPersistenceException(e.getMessage());
        }
    }

    @Override
    @Transactional
    public List<User> getAllUsersByRoleId(int roleId)
            throws BTBBPersistenceException {
        try {
            List<User> users = jdbcTemplate.query(SELECT_ALL_USERS_BY_ROLE_ID,
                    new UserMapper(),
                    roleId);

            if (users.size() <= 0) {
            } else {
                users.forEach((currentUser) -> {
                    this.associateRolesWithUser(currentUser);
                });
            }
            return users;

        } catch (DataAccessException e) {
            throw new BTBBPersistenceException(e.getMessage());
        }
    }

    private void associateRolesWithUser(User user) {
        List<DesignationRole> designationRoles = new ArrayList<>();
        List<Integer> roleIds = jdbcTemplate.query(SELECT_ROLES_BY_USER_ID,
                new RoleIdMapper(),
                user.getUserId());

        if (roleIds.size() <= 0) {
        } else {
            roleIds.forEach((currentId) -> {
                designationRoles.add(jdbcTemplate.queryForObject(SELECT_ROLE_BY_ID,
                        new RoleMapper(),
                        currentId));
            });
        }
        user.setDesignationRoles(designationRoles);
    }

    @Override
    @Transactional
    public int deleteUser(int userId) throws BTBBPersistenceException {
        try {   
            this.deleteAllPostsByUserId(userId);    
            jdbcTemplate.update(DELETE_USER_ROLE_BY_USER_ID, userId);

            return jdbcTemplate.update(DELETE_USER, userId);
        } catch (DataAccessException e) {
            throw new BTBBPersistenceException(e.getMessage());
        }
    }

    @Override
    @Transactional
    public User updateUser(User user) throws BTBBPersistenceException {
        try {
            jdbcTemplate.update(UPDATE_USER,
                    user.getName(),
                    user.getUserName(),
                    user.getPassword(),
                    user.getEmail(),
                    user.getUserId());

            List<DesignationRole> designationRoles = user.getDesignationRoles();

            if (designationRoles.size() <= 0) {
            } else {
                for (DesignationRole currentDesignationRole : designationRoles) {
                    this.updateRole(currentDesignationRole);
                }
            }
            return this.getUserById(user.getUserId());
        } catch (DataAccessException e) {
            throw new BTBBPersistenceException(e.getMessage());
        }

    }

    @Override
    @Transactional
    public DesignationRole addRole(DesignationRole designationRole) throws BTBBPersistenceException {
        try {
            jdbcTemplate.update(INSERT_ROLE,
                designationRole.getName());
                designationRole.setRoleId(jdbcTemplate.queryForObject("select LAST_INSERT_ID()",
                    Integer.class));

            return designationRole;
        } catch (DataAccessException e) {
            throw new BTBBPersistenceException(e.getMessage());
        }
    }

    @Override
    @Transactional
    public int deleteRole(int roleId) throws BTBBPersistenceException {
        try {
            jdbcTemplate.update(DELETE_USER_ROLE_BY_ROLE_ID, roleId);

            return jdbcTemplate.update(DELETE_ROLE, roleId);
        } catch (DataAccessException e) {
            throw new BTBBPersistenceException(e.getMessage());
        }
    }

    @Override
    @Transactional
    public DesignationRole updateRole(DesignationRole designationRole) throws BTBBPersistenceException {
        try {
            jdbcTemplate.update(UPDATE_ROLE,
                    designationRole.getName(),
                    designationRole.getRoleId());

            return getRoleById(designationRole.getRoleId());
        } catch (DataAccessException e) {
            throw new BTBBPersistenceException(e.getMessage());
        }
    }

    @Override
    public DesignationRole getRoleById(int roleId) throws BTBBPersistenceException {
        try {
            return jdbcTemplate.queryForObject(SELECT_ROLE_BY_ID,
                    new RoleMapper(),
                    roleId);
        } catch (DataAccessException e) {
            throw new BTBBPersistenceException(e.getMessage());
        }
    }

    @Override
    public List<DesignationRole> getAllRoles() throws BTBBPersistenceException {
        try {
            return jdbcTemplate.query(SELECT_ALL_ROLES, new RoleMapper());
        } catch (DataAccessException e) {
            throw new BTBBPersistenceException(e.getMessage());
        }
    }

    // mappers
    private static final class UserMapper implements RowMapper<User> {

        @Override
        public User mapRow(ResultSet rs, int i) throws SQLException {
            User user = new User();
            user.setUserId(rs.getInt("userId"));
            user.setName(rs.getString("name"));
            user.setUserName(rs.getString("userName"));
            user.setEmail(rs.getString("email"));
            user.setPassword(rs.getString("password"));
            return user;
        }
    }

    private static final class UserIdMapper implements RowMapper<Integer> {

        @Override
        public Integer mapRow(ResultSet rs, int i) throws SQLException {
            return rs.getInt("userId");
        }
    }

    private static final class RoleMapper implements RowMapper<DesignationRole> {

        @Override
        public DesignationRole mapRow(ResultSet rs, int i) throws SQLException {
            DesignationRole designationRole = new DesignationRole();
            designationRole.setRoleId(rs.getInt("roleId"));
            designationRole.setName(rs.getString("name"));
            return designationRole;
        }
    }

    private static final class RoleIdMapper implements RowMapper<Integer> {

        @Override
        public Integer mapRow(ResultSet rs, int i) throws SQLException {
            Integer integer;
            integer = rs.getInt("roleId");
            return integer;
        }
    }

    private static final class PostMapper implements RowMapper<NewPost> {

        @Override
        public NewPost mapRow(ResultSet rs, int i) throws SQLException {
            NewPost newPost = new NewPost();
            newPost.setBlogPostId(rs.getInt("blogPostId"));
            newPost.setBlogTitle(rs.getString("blogTitle"));
            newPost.setContent(rs.getString("content"));
            newPost.setBlogPostDate(rs.getTimestamp("blogPostDate").toLocalDateTime());
            newPost.setImageLocation(rs.getString("imageLocation"));
            return newPost;
        }
    }

    private static final class PostIdMapper implements RowMapper<Integer> {

        @Override
        public Integer mapRow(ResultSet rs, int i) throws SQLException {
            return rs.getInt("blogPostId");
        }
    }


    private static final class TagMapper implements RowMapper<Tags> {

        @Override
        public Tags mapRow(ResultSet rs, int i) throws SQLException {
            Tags tag = new Tags();
            tag.setTagId(rs.getInt("tagId"));
            tag.setName(rs.getString("name"));
            return tag;
        }
    }

    private static final class TagIdMapper implements RowMapper<Integer> {

        @Override
        public Integer mapRow(ResultSet rs, int i) throws SQLException {
            return rs.getInt("tagId");
        }
    }

    private static final class ApprovalStatusMapper implements RowMapper<PostApprovalStatus> {

        @Override
        public PostApprovalStatus mapRow(ResultSet rs, int i) throws SQLException {
            PostApprovalStatus status = new PostApprovalStatus();
            status.setApprovalStatusId(rs.getInt("approvalStatusId"));
            status.setDescription(rs.getString("description"));
            return status;
        }
    }

    private static final class ApprovalStatusIdMapper implements RowMapper<Integer> {

        @Override
        public Integer mapRow(ResultSet rs, int i) throws SQLException {
            return rs.getInt("approvalStatusId");
        }
    }
}