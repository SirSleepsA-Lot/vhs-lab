package vhslab.solution.entities.model;
import jakarta.persistence.*;

import java.util.Date;

@Entity
public class VHS {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private float lateReturnFee;

    private Date createdDate;
    private Date lastModifiedDate;
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public float getLateReturnFee() {
        return lateReturnFee;
    }

    public void setLateReturnFee(float lateReturnFee) {
        this.lateReturnFee = lateReturnFee;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }
// Getters and setters
    // Constructors
}