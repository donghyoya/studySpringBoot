package gogora.gogo.goplan.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "rider_insurance_history", schema = "goPlan_KB", catalog = "")
public class RiderInsuranceHistoryEntity {
    private long id;
    private Timestamp endorsementCompleteTime;
    private Timestamp endorsementRequestTime;
    private Timestamp underwritingCompleteTime;
    private Timestamp underwritingRequestTime;
    private Timestamp withdrawCompleteTime;
    private Timestamp withdrawRequestTime;
    private Long riderId;
    private Long riderWebId;

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
    @Column(name = "endorsement_complete_time", nullable = true)
    public Timestamp getEndorsementCompleteTime() {
        return endorsementCompleteTime;
    }

    public void setEndorsementCompleteTime(Timestamp endorsementCompleteTime) {
        this.endorsementCompleteTime = endorsementCompleteTime;
    }

    @Basic
    @Column(name = "endorsement_request_time", nullable = true)
    public Timestamp getEndorsementRequestTime() {
        return endorsementRequestTime;
    }

    public void setEndorsementRequestTime(Timestamp endorsementRequestTime) {
        this.endorsementRequestTime = endorsementRequestTime;
    }

    @Basic
    @Column(name = "underwriting_complete_time", nullable = true)
    public Timestamp getUnderwritingCompleteTime() {
        return underwritingCompleteTime;
    }

    public void setUnderwritingCompleteTime(Timestamp underwritingCompleteTime) {
        this.underwritingCompleteTime = underwritingCompleteTime;
    }

    @Basic
    @Column(name = "underwriting_request_time", nullable = true)
    public Timestamp getUnderwritingRequestTime() {
        return underwritingRequestTime;
    }

    public void setUnderwritingRequestTime(Timestamp underwritingRequestTime) {
        this.underwritingRequestTime = underwritingRequestTime;
    }

    @Basic
    @Column(name = "withdraw_complete_time", nullable = true)
    public Timestamp getWithdrawCompleteTime() {
        return withdrawCompleteTime;
    }

    public void setWithdrawCompleteTime(Timestamp withdrawCompleteTime) {
        this.withdrawCompleteTime = withdrawCompleteTime;
    }

    @Basic
    @Column(name = "withdraw_request_time", nullable = true)
    public Timestamp getWithdrawRequestTime() {
        return withdrawRequestTime;
    }

    public void setWithdrawRequestTime(Timestamp withdrawRequestTime) {
        this.withdrawRequestTime = withdrawRequestTime;
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
    @Column(name = "riderWeb_id", nullable = true)
    public Long getRiderWebId() {
        return riderWebId;
    }

    public void setRiderWebId(Long riderWebId) {
        this.riderWebId = riderWebId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RiderInsuranceHistoryEntity that = (RiderInsuranceHistoryEntity) o;
        return id == that.id && Objects.equals(endorsementCompleteTime, that.endorsementCompleteTime) && Objects.equals(endorsementRequestTime, that.endorsementRequestTime) && Objects.equals(underwritingCompleteTime, that.underwritingCompleteTime) && Objects.equals(underwritingRequestTime, that.underwritingRequestTime) && Objects.equals(withdrawCompleteTime, that.withdrawCompleteTime) && Objects.equals(withdrawRequestTime, that.withdrawRequestTime) && Objects.equals(riderId, that.riderId) && Objects.equals(riderWebId, that.riderWebId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, endorsementCompleteTime, endorsementRequestTime, underwritingCompleteTime, underwritingRequestTime, withdrawCompleteTime, withdrawRequestTime, riderId, riderWebId);
    }
}
