package vhslab.solution.entities.model;

import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "user", schema = "public", catalog = "Vhs-Lab")
public class UserEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "Id")
    private long id;
    @Basic
    @Column(name = "Username")
    private String username;
    @CreatedDate
    @Column(name = "DateCreated")
    private Timestamp dateCreated;
    @LastModifiedDate
    @Version
    @Column(name = "DateModified")
    private Timestamp dateModified;
    @OneToMany(mappedBy = "userByUserId")
    private Collection<RentalEntity> rentalsById;

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "Id", nullable = false)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "Username", nullable = false, length = 100)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Basic
    @Column(name = "DateCreated", nullable = false)
    public Timestamp getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Timestamp dateCreated) {
        this.dateCreated = dateCreated;
    }

    @Basic
    @Column(name = "DateModified", nullable = false)
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
        return id == that.id && Objects.equals(username, that.username) && Objects.equals(dateCreated, that.dateCreated) && Objects.equals(dateModified, that.dateModified);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, dateCreated, dateModified);
    }

    @OneToMany(mappedBy = "userByUserId")
    public Collection<RentalEntity> getRentalsById() {
        return rentalsById;
    }

    public void setRentalsById(Collection<RentalEntity> rentalsById) {
        this.rentalsById = rentalsById;
    }
}
