package vhslab.solution.entities.dto;

import vhslab.solution.entities.model.UserEntity;

import java.sql.Timestamp;
import java.util.Objects;

public class UserEntityDto {

    private long id;

    private String username;

    private Timestamp dateCreated;

    private Timestamp dateModified;

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

    public Timestamp getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Timestamp dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Timestamp getDateModified() {
        return dateModified;
    }

    public void setDateModified(Timestamp dateModified) {
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
