package com.sg.btbb.model;

import java.util.Objects;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author brian russick
 */
public class PostApprovalStatus {
    
    private int approvalStatusId;
    
    @NotEmpty(message = "Must enter a description!")
    @Length(max = 50, message = "Input is 50 char max! Use less characters!")
    private String description;

    public PostApprovalStatus() {
    }

    public PostApprovalStatus(String description) {
        this.description = description;
    }

    public int getApprovalStatusId() {
        return approvalStatusId;
    }

    public void setApprovalStatusId(int approvalStatusId) {
        this.approvalStatusId = approvalStatusId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 17 * hash + this.approvalStatusId;
        hash = 17 * hash + Objects.hashCode(this.description);
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
        final PostApprovalStatus other = (PostApprovalStatus) obj;
        if (this.approvalStatusId != other.approvalStatusId) {
            return false;
        }
        if (!Objects.equals(this.description, other.description)) {
            return false;
        }
        return true;
    }  
}