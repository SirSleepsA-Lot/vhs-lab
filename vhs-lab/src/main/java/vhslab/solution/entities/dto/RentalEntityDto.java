package vhslab.solution.entities.dto;

import jakarta.persistence.*;
import vhslab.solution.entities.model.RentalEntity;
import vhslab.solution.entities.model.UserEntity;
import vhslab.solution.entities.model.VhsEntity;

import java.sql.Date;
import java.util.Objects;

public class RentalEntityDto {
    private long id;
    private Date dateRented;
    private Date dateDue;
    private Date dateReturned;
    private Date dateCreated;
    private Date dateModified;
    private long userId;
    private long vhsId;

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    public Date getDateRented() {
        return dateRented;
    }
    public void setDateRented(Date dateRented) {
        this.dateRented = dateRented;
    }

    public Date getDateDue() {
        return dateDue;
    }
    public void setDateDue(Date dateDue) {
        this.dateDue = dateDue;
    }

    public Date getDateReturned() {
        return dateReturned;
    }
    public void setDateReturned(Date dateReturned) {
        this.dateReturned = dateReturned;
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

    public long getUserId() {
        return userId;
    }
    public void setUserId(long userByUserId) {
        this.userId = userByUserId;
    }

    public long getVhsByVhsId() {
        return vhsId;
    }
    public void setVhsByVhsId(long vhsByVhsId) {
        this.vhsId = vhsByVhsId;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RentalEntity that = (RentalEntity) o;
        return id == that.getId() && Objects.equals(dateRented, that.getDateRented()) && Objects.equals(dateDue, that.getDateDue()) && Objects.equals(dateReturned, that.getDateReturned()) && Objects.equals(dateCreated, that.getDateCreated()) && Objects.equals(dateModified, that.getDateModified());
    }
    @Override
    public int hashCode() {
        return Objects.hash(id, dateRented, dateDue, dateReturned, dateCreated, dateModified);
    }


}
