package vhslab.solution.entities.dto;

import vhslab.solution.entities.model.VhsEntity;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Objects;

public class VhsEntityDto {
    private long id;
    private String title;
    private BigDecimal lateReturnFee;
    private Timestamp dateCreated;
    private Timestamp dateModified;

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public BigDecimal getLateReturnFee() {
        return lateReturnFee;
    }
    public void setLateReturnFee(BigDecimal lateReturnFee) {
        this.lateReturnFee = lateReturnFee;
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
        VhsEntity vhsEntity = (VhsEntity) o;
        return id == vhsEntity.getId() && Objects.equals(title, vhsEntity.getTitle()) && Objects.equals(lateReturnFee, vhsEntity.getLateReturnFee()) && Objects.equals(dateCreated, vhsEntity.getDateCreated()) && Objects.equals(dateModified, vhsEntity.getDateModified());
    }
    @Override
    public int hashCode() {
        return Objects.hash(id, title, lateReturnFee, dateCreated, dateModified);
    }

}
