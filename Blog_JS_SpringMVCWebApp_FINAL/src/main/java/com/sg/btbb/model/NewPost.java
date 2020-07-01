package com.sg.btbb.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author brian russick
 */
public class NewPost {
    
    private int blogPostId;    
    @NotEmpty(message = "Must enter a title!")
    @Length(max = 75, message = "Input is 75 char max! Use less characters!")
    private String blogTitle;
    
    @NotEmpty(message = "Must enter some content!")
    @Length(max = 50000, message = "Input is 50K char max! Use less characters!")
    private String content;    
    
    private PostApprovalStatus postApprovalStatus;
    private LocalDateTime blogPostDate;
  
    @Length(max = 400, message = "Input is 400 char max! Use less characters!")
    private String imageLocation;  
    
    private User user;   
    private List<Tags> TagsList = new ArrayList<>();

    public int getBlogPostId() {
        return blogPostId;
    }

    public void setBlogPostId(int blogPostId) {
        this.blogPostId = blogPostId;
    }

    public String getBlogTitle() {
        return blogTitle;
    }

    public void setBlogTitle(String blogTitle) {
        this.blogTitle = blogTitle;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public PostApprovalStatus getPostApprovalStatus() {
        return postApprovalStatus;
    }

    public void setPostApprovalStatus(PostApprovalStatus postApprovalStatus) {
        this.postApprovalStatus = postApprovalStatus;
    }

    public LocalDateTime getBlogPostDate() {
        return blogPostDate;
    }

    public void setBlogPostDate(LocalDateTime blogPostDate) {
        this.blogPostDate = blogPostDate;
    }

    public String getImageLocation() {
        return imageLocation;
    }

    public void setImageLocation(String imageLocation) {
        this.imageLocation = imageLocation;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Tags> getTagsList() {
        return TagsList;
    }

    public void setTagsList(List<Tags> tagsList) {
        this.TagsList = tagsList;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + this.blogPostId;
        hash = 67 * hash + Objects.hashCode(this.blogTitle);
        hash = 67 * hash + Objects.hashCode(this.content);
        hash = 67 * hash + Objects.hashCode(this.postApprovalStatus);
        hash = 67 * hash + Objects.hashCode(this.blogPostDate);
        hash = 67 * hash + Objects.hashCode(this.imageLocation);
        hash = 67 * hash + Objects.hashCode(this.user);
        hash = 67 * hash + Objects.hashCode(this.TagsList);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final NewPost other = (NewPost) obj;
        if (this.blogPostId != other.blogPostId) {
            return false;
        }
        if (!Objects.equals(this.blogTitle, other.blogTitle)) {
            return false;
        }
        if (!Objects.equals(this.content, other.content)) {
            return false;
        }
        if (!Objects.equals(this.imageLocation, other.imageLocation)) {
            return false;
        }
        if (!Objects.equals(this.postApprovalStatus, other.postApprovalStatus)) {
            return false;
        }
        if (!Objects.equals(this.blogPostDate, other.blogPostDate)) {
            return false;
        }
        if (!Objects.equals(this.user, other.user)) {
            return false;
        }
        if (!Objects.equals(this.TagsList, other.TagsList)) {
            return false;
        }
        return true;
    }
}