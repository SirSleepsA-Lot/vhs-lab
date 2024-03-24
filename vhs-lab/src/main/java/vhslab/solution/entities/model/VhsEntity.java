package vhslab.solution.entities.model;

import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "vhs", schema = "public", catalog = "Vhs-Lab")
@EntityListeners(AuditingEntityListener.class)
public class VhsEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private long id;
    @Basic
    @Column(name = "Title")
    private String title;
    @Basic
    @Column(name = "late_return_fee")
    private BigDecimal lateReturnFee;
    @CreatedDate
    @Column(name = "date_created")
    private Timestamp dateCreated;

    @Version
    @LastModifiedDate
    @Column(name = "date_modified")
    private Timestamp dateModified;
    @OneToMany(mappedBy = "vhsByVhsId")
    private Collection<RentalEntity> rentalsById;

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
    @Column(name = "title", nullable = false, length = 100)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Basic
    @Column(name = "late_return_fee", nullable = false, precision = 3)
    public BigDecimal getLateReturnFee() {
        return lateReturnFee;
    }

    public void setLateReturnFee(BigDecimal lateReturnFee) {
        this.lateReturnFee = lateReturnFee;
    }

    @CreatedDate
    @Column(name = "date_created", nullable = false)
    public Timestamp getDateCreated() {
        return dateCreated;
    }
    public void setDateCreated(Timestamp dateCreated) {
        this.dateCreated = dateCreated;
    }


    @Version
    @LastModifiedDate
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
        VhsEntity vhsEntity = (VhsEntity) o;
        return id == vhsEntity.id && Objects.equals(title, vhsEntity.title) && Objects.equals(lateReturnFee, vhsEntity.lateReturnFee) && Objects.equals(dateCreated, vhsEntity.dateCreated) && Objects.equals(dateModified, vhsEntity.dateModified);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, lateReturnFee, dateCreated, dateModified);
    }

    @OneToMany(mappedBy = "vhsByVhsId")
    public Collection<RentalEntity> getRentalsById() {
        return rentalsById;
    }

    public void setRentalsById(Collection<RentalEntity> rentalsById) {
        this.rentalsById = rentalsById;
    }
}
