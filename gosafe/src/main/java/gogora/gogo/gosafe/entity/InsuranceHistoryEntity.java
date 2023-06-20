package gogora.gogo.gosafe.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "insurance_history", schema = "goPlan_KB", catalog = "")
public class InsuranceHistoryEntity {
    private long id;
    private String applicationNumber;
    private Timestamp createdDate;
    private String policyNumber;
    private String status;
    private Long riderId;
    private Timestamp effectiveEndDate;
    private Timestamp effectiveStartDate;
    private Timestamp until;
    private String age21Yn;
    private Long sellerId;
    private String ageYn;

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
    @Column(name = "applicationNumber", nullable = true, length = 20)
    public String getApplicationNumber() {
        return applicationNumber;
    }

    public void setApplicationNumber(String applicationNumber) {
        this.applicationNumber = applicationNumber;
    }

    @Basic
    @Column(name = "createdDate", nullable = true)
    public Timestamp getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }

    @Basic
    @Column(name = "policy_number", nullable = true, length = 20)
    public String getPolicyNumber() {
        return policyNumber;
    }

    public void setPolicyNumber(String policyNumber) {
        this.policyNumber = policyNumber;
    }

    @Basic
    @Column(name = "status", nullable = true, length = 20)
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Basic
    @Column(name = "rider_id", nullable = true)
    public Long getRiderId() {
        return riderId;
    }

    public void setRiderId(Long riderId) {
        this.riderId = riderId;
    }

    @Basic
    @Column(name = "effectiveEndDate", nullable = true)
    public Timestamp getEffectiveEndDate() {
        return effectiveEndDate;
    }

    public void setEffectiveEndDate(Timestamp effectiveEndDate) {
        this.effectiveEndDate = effectiveEndDate;
    }

    @Basic
    @Column(name = "effectiveStartDate", nullable = true)
    public Timestamp getEffectiveStartDate() {
        return effectiveStartDate;
    }

    public void setEffectiveStartDate(Timestamp effectiveStartDate) {
        this.effectiveStartDate = effectiveStartDate;
    }

    @Basic
    @Column(name = "until", nullable = true)
    public Timestamp getUntil() {
        return until;
    }

    public void setUntil(Timestamp until) {
        this.until = until;
    }

    @Basic
    @Column(name = "age21Yn", nullable = true, length = 255)
    public String getAge21Yn() {
        return age21Yn;
    }

    public void setAge21Yn(String age21Yn) {
        this.age21Yn = age21Yn;
    }

    @Basic
    @Column(name = "seller_id", nullable = true)
    public Long getSellerId() {
        return sellerId;
    }

    public void setSellerId(Long sellerId) {
        this.sellerId = sellerId;
    }

    @Basic
    @Column(name = "ageYn", nullable = true, length = 255)
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
