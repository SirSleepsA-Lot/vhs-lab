package vhslab.solution.entities.model;

import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "rental", schema = "public", catalog = "Vhs-Lab")
@EntityListeners(AuditingEntityListener.class)
public class RentalEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private long id;
    @Basic
    @Column(name = "date_rented")
    private Date dateRented;
    @Basic
    @Column(name = "date_due")
    private Date dateDue;
    @Basic
    @Column(name = "date_returned")
    private Date dateReturned;
    @CreatedDate
    @Column(name = "date_created")
    private Timestamp dateCreated;
    @LastModifiedDate
    @Version
    @Column(name = "date_modified")
    private Timestamp dateModified;
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "Id", nullable = false)
    private UserEntity userByUserId;
    @ManyToOne
    @JoinColumn(name = "vhs_id", referencedColumnName = "Id", nullable = false)
    private VhsEntity vhsByVhsId;
    @Basic
    @Column(name = "fee")
    private BigDecimal fee;
    @Basic
    @Column(name = "fee_paid")
    private Boolean feePaid;

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }


    @Basic
    @Column(name = "date_rented", nullable = false)
    public Date getDateRented() {
        return dateRented;
    }

    public void setDateRented(Date dateRented) {
        this.dateRented = dateRented;
    }

    @Basic
    @Column(name = "date_due", nullable = false)
    public Date getDateDue() {
        return dateDue;
    }

    public void setDateDue(Date dateDue) {
        this.dateDue = dateDue;
    }

    @Basic
    @Column(name = "date_returned", nullable = true)
    public Date getDateReturned() {
        return dateReturned;
    }

    public void setDateReturned(Date dateReturned) {
        this.dateReturned = dateReturned;
    }

    @CreatedDate
    @Column(name = "date_created", nullable = false, updatable = false)
    public Timestamp getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Timestamp dateCreated) {
        this.dateCreated = dateCreated;
    }

    @LastModifiedDate
    @Version
    @Column(name = "date_modified", nullable = false)
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
        RentalEntity that = (RentalEntity) o;
        return id == that.id && Objects.equals(dateRented, that.dateRented) && Objects.equals(dateDue, that.dateDue) && Objects.equals(dateReturned, that.dateReturned) && Objects.equals(dateCreated, that.dateCreated) && Objects.equals(dateModified, that.dateModified);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, dateRented, dateDue, dateReturned, dateCreated, dateModified);
    }

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "Id", nullable = false)
    public UserEntity getUserByUserId() {
        return userByUserId;
    }

    public void setUserByUserId(UserEntity userByUserId) {
        this.userByUserId = userByUserId;
    }

    @ManyToOne
    @JoinColumn(name = "vhs_id", referencedColumnName = "Id", nullable = false)
    public VhsEntity getVhsByVhsId() {
        return vhsByVhsId;
    }

    public void setVhsByVhsId(VhsEntity vhsByVhsId) {
        this.vhsByVhsId = vhsByVhsId;
    }

    @Basic
    @Column(name = "fee")
    public BigDecimal getFee() {
        return fee;
    }

    public void setFee(BigDecimal fee) {
        this.fee = fee;
    }

    @Basic
    @Column(name = "fee_paid")
    public Boolean getFeePaid() {
        return feePaid;
    }

    public void setFeePaid(Boolean feePaid) {
        this.feePaid = feePaid;
    }
}
