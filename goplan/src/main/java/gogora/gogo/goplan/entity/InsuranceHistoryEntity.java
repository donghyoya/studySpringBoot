package gogora.gogo.goplan.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "insurance_history", schema = "goPlan_KB", catalog = "")
public class InsuranceHistoryEntity {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private long id;

    @Basic
    @Column(name = "applicationNumber", nullable = true, length = 20)
    private String applicationNumber;

    @Basic
    @Column(name = "createdDate", nullable = true)
    private Timestamp createdDate;

    @Basic
    @Column(name = "policy_number", nullable = true, length = 20)
    private String policyNumber;

    @Basic
    @Column(name = "status", nullable = true, length = 20)
    private String status;

    @Basic
    @Column(name = "rider_id", nullable = true)
    private Long riderId;

    @Basic
    @Column(name = "effectiveEndDate", nullable = true)
    private Timestamp effectiveEndDate;

    @Basic
    @Column(name = "effectiveStartDate", nullable = true)
    private Timestamp effectiveStartDate;

    @Basic
    @Column(name = "until", nullable = true)
    private Timestamp until;

    @Basic
    @Column(name = "age21Yn", nullable = true, length = 255)
    private String age21Yn;

    @Basic
    @Column(name = "seller_id", nullable = true)
    private Long sellerId;

    @Basic
    @Column(name = "ageYn", nullable = true, length = 255)
    private String ageYn;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    public String getApplicationNumber() {
        return applicationNumber;
    }

    public void setApplicationNumber(String applicationNumber) {
        this.applicationNumber = applicationNumber;
    }


    public Timestamp getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }


    public String getPolicyNumber() {
        return policyNumber;
    }

    public void setPolicyNumber(String policyNumber) {
        this.policyNumber = policyNumber;
    }


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    public Long getRiderId() {
        return riderId;
    }

    public void setRiderId(Long riderId) {
        this.riderId = riderId;
    }


    public Timestamp getEffectiveEndDate() {
        return effectiveEndDate;
    }

    public void setEffectiveEndDate(Timestamp effectiveEndDate) {
        this.effectiveEndDate = effectiveEndDate;
    }


    public Timestamp getEffectiveStartDate() {
        return effectiveStartDate;
    }

    public void setEffectiveStartDate(Timestamp effectiveStartDate) {
        this.effectiveStartDate = effectiveStartDate;
    }


    public Timestamp getUntil() {
        return until;
    }

    public void setUntil(Timestamp until) {
        this.until = until;
    }


    public String getAge21Yn() {
        return age21Yn;
    }

    public void setAge21Yn(String age21Yn) {
        this.age21Yn = age21Yn;
    }


    public Long getSellerId() {
        return sellerId;
    }

    public void setSellerId(Long sellerId) {
        this.sellerId = sellerId;
    }


    public String getAgeYn() {
        return ageYn;
    }

    public void setAgeYn(String ageYn) {
        this.ageYn = ageYn;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InsuranceHistoryEntity that = (InsuranceHistoryEntity) o;
        return id == that.id && Objects.equals(applicationNumber, that.applicationNumber) && Objects.equals(createdDate, that.createdDate) && Objects.equals(policyNumber, that.policyNumber) && Objects.equals(status, that.status) && Objects.equals(riderId, that.riderId) && Objects.equals(effectiveEndDate, that.effectiveEndDate) && Objects.equals(effectiveStartDate, that.effectiveStartDate) && Objects.equals(until, that.until) && Objects.equals(age21Yn, that.age21Yn) && Objects.equals(sellerId, that.sellerId) && Objects.equals(ageYn, that.ageYn);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, applicationNumber, createdDate, policyNumber, status, riderId, effectiveEndDate, effectiveStartDate, until, age21Yn, sellerId, ageYn);
    }
}
