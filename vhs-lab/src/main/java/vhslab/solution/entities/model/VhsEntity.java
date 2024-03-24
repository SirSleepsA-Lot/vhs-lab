package vhslab.solution.entities.model;

import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "VHS", schema = "public", catalog = "Vhs-Lab")
public class VhsEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "Id")
    private long id;
    @Basic
    @Column(name = "Title")
    private String title;
    @Basic
    @Column(name = "LateReturnFee")
    private BigDecimal lateReturnFee;
    @CreatedDate
    @Column(name = "DateCreated")
    private Timestamp dateCreated;
    @LastModifiedDate
    @Version
    @Column(name = "DateModified")
    private Timestamp dateModified;
    @OneToMany(mappedBy = "vhsByVhsId")
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
    @Column(name = "Title", nullable = false, length = 100)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Basic
    @Column(name = "LateReturnFee", nullable = false, precision = 3)
    public BigDecimal getLateReturnFee() {
        return lateReturnFee;
    }

    public void setLateReturnFee(BigDecimal lateReturnFee) {
        this.lateReturnFee = lateReturnFee;
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
