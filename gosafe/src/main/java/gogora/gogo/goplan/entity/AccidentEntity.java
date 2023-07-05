package gogora.gogo.goplan.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "accident", schema = "goPlan_KB", catalog = "")
public class AccidentEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private long id;

    @Basic
    @Column(name = "createdDate", nullable = true)
    private Timestamp createdDate;

    @Basic
    @Column(name = "deletedDate", nullable = true)
    private Timestamp deletedDate;

    @Basic
    @Column(name = "modifiedDate", nullable = true)
    private Timestamp modifiedDate;

    @Basic
    @Column(name = "accident_time", nullable = true)
    private Timestamp accidentTime;

    @Basic
    @Column(name = "claim_number", nullable = true, length = 64)
    private String claimNumber;

    @Basic
    @Column(name = "claim_time", nullable = true)
    private Timestamp claimTime;

    @Basic
    @Column(name = "call_id", nullable = true)
    private Long callId;

    @Basic
    @Column(name = "compensation", nullable = true)
    private Integer compensation;

    @Basic
    @Column(name = "driver_id", nullable = true, length = 20)
    private String driverId;

    @Basic
    @Column(name = "policy_number", nullable = true, length = 20)
    private String policyNumber;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    public Timestamp getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }


    public Timestamp getDeletedDate() {
        return deletedDate;
    }

    public void setDeletedDate(Timestamp deletedDate) {
        this.deletedDate = deletedDate;
    }


    public Timestamp getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Timestamp modifiedDate) {
        this.modifiedDate = modifiedDate;
    }


    public Timestamp getAccidentTime() {
        return accidentTime;
    }

    public void setAccidentTime(Timestamp accidentTime) {
        this.accidentTime = accidentTime;
    }


    public String getClaimNumber() {
        return claimNumber;
    }

    public void setClaimNumber(String claimNumber) {
        this.claimNumber = claimNumber;
    }


    public Timestamp getClaimTime() {
        return claimTime;
    }

    public void setClaimTime(Timestamp claimTime) {
        this.claimTime = claimTime;
    }


    public Long getCallId() {
        return callId;
    }

    public void setCallId(Long callId) {
        this.callId = callId;
    }


    public Integer getCompensation() {
        return compensation;
    }

    public void setCompensation(Integer compensation) {
        this.compensation = compensation;
    }


    public String getDriverId() {
        return driverId;
    }

    public void setDriverId(String driverId) {
        this.driverId = driverId;
    }


    public String getPolicyNumber() {
        return policyNumber;
    }

    public void setPolicyNumber(String policyNumber) {
        this.policyNumber = policyNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AccidentEntity that = (AccidentEntity) o;
        return id == that.id && Objects.equals(createdDate, that.createdDate) && Objects.equals(deletedDate, that.deletedDate) && Objects.equals(modifiedDate, that.modifiedDate) && Objects.equals(accidentTime, that.accidentTime) && Objects.equals(claimNumber, that.claimNumber) && Objects.equals(claimTime, that.claimTime) && Objects.equals(callId, that.callId) && Objects.equals(compensation, that.compensation) && Objects.equals(driverId, that.driverId) && Objects.equals(policyNumber, that.policyNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, createdDate, deletedDate, modifiedDate, accidentTime, claimNumber, claimTime, callId, compensation, driverId, policyNumber);
    }
}
