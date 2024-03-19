package vhslab.solution.entities.dto;

import jakarta.persistence.*;
import vhslab.solution.entities.model.RentalEntity;
import vhslab.solution.entities.model.UserEntity;

import java.sql.Date;
import java.util.Collection;
import java.util.Objects;

public class UserEntityDto {

    private long id;

    private String username;

    private Date dateCreated;

    private Date dateModified;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Date getDateModified() {
        return dateModified;
    }

    public void setDateModified(Date dateModified) {
        this.dateModified = dateModified;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserEntity that = (UserEntity) o;
        return id == that.getId() && Objects.equals(username, that.getUsername()) && Objects.equals(dateCreated, that.getDateCreated()) && Objects.equals(dateModified, that.getDateModified());
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, dateCreated, dateModified);
    }
}
