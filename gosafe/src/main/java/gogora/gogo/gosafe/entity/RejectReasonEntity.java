package gogora.gogo.gosafe.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "reject_reason", schema = "goPlan_KB", catalog = "")
public class RejectReasonEntity {
    private long id;
    private String rejectReason;
    private Timestamp rejectDate;
    private String status;
    private Long riderId;
    private Long joinWebId;

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
    @Column(name = "reject_reason", nullable = true, length = 255)
    public String getRejectReason() {
        return rejectReason;
    }

    public void setRejectReason(String rejectReason) {
        this.rejectReason = rejectReason;
    }

    @Basic
    @Column(name = "reject_date", nullable = true)
    public Timestamp getRejectDate() {
        return rejectDate;
    }

    public void setRejectDate(Timestamp rejectDate) {
        this.rejectDate = rejectDate;
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
    @Column(name = "joinWeb_Id", nullable = true)
    public Long getJoinWebId() {
        return joinWebId;
    }

    public void setJoinWebId(Long joinWebId) {
        this.joinWebId = joinWebId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RejectReasonEntity that = (RejectReasonEntity) o;
        return id == that.id && Objects.equals(rejectReason, that.rejectReason) && Objects.equals(rejectDate, that.rejectDate) && Objects.equals(status, that.status) && Objects.equals(riderId, that.riderId) && Objects.equals(joinWebId, that.joinWebId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, rejectReason, rejectDate, status, riderId, joinWebId);
    }
}
