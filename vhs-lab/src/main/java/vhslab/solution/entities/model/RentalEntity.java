package vhslab.solution.entities.model;

import jakarta.persistence.*;

import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "Rental", schema = "public", catalog = "Vhs-Lab")
public class RentalEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "Id")
    private long id;
    @Basic
    @Column(name = "DateRented")
    private Date dateRented;
    @Basic
    @Column(name = "DateDue")
    private Date dateDue;
    @Basic
    @Column(name = "DateReturned")
    private Date dateReturned;
    @Basic
    @Column(name = "DateCreated")
    private Date dateCreated;
    @Basic
    @Column(name = "DateModified")
    private Date dateModified;
    @ManyToOne
    @JoinColumn(name = "UserId", referencedColumnName = "Id", nullable = false)
    private UserEntity userByUserId;
    @ManyToOne
    @JoinColumn(name = "VHSId", referencedColumnName = "Id", nullable = false)
    private VhsEntity vhsByVhsId;

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
    @Column(name = "DateRented", nullable = false)
    public Date getDateRented() {
        return dateRented;
    }

    public void setDateRented(Date dateRented) {
        this.dateRented = dateRented;
    }

    @Basic
    @Column(name = "DateDue", nullable = false)
    public Date getDateDue() {
        return dateDue;
    }

    public void setDateDue(Date dateDue) {
        this.dateDue = dateDue;
    }

    @Basic
    @Column(name = "DateReturned", nullable = true)
    public Date getDateReturned() {
        return dateReturned;
    }

    public void setDateReturned(Date dateReturned) {
        this.dateReturned = dateReturned;
    }

    @Basic
    @Column(name = "DateCreated", nullable = false)
    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    @Basic
    @Column(name = "DateModified", nullable = false)
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
        RentalEntity that = (RentalEntity) o;
        return id == that.id && Objects.equals(dateRented, that.dateRented) && Objects.equals(dateDue, that.dateDue) && Objects.equals(dateReturned, that.dateReturned) && Objects.equals(dateCreated, that.dateCreated) && Objects.equals(dateModified, that.dateModified);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, dateRented, dateDue, dateReturned, dateCreated, dateModified);
    }

    @ManyToOne
    @JoinColumn(name = "UserId", referencedColumnName = "Id", nullable = false)
    public UserEntity getUserByUserId() {
        return userByUserId;
    }

    public void setUserByUserId(UserEntity userByUserId) {
        this.userByUserId = userByUserId;
    }

    @ManyToOne
    @JoinColumn(name = "VHSId", referencedColumnName = "Id", nullable = false)
    public VhsEntity getVhsByVhsId() {
        return vhsByVhsId;
    }

    public void setVhsByVhsId(VhsEntity vhsByVhsId) {
        this.vhsByVhsId = vhsByVhsId;
    }
}
