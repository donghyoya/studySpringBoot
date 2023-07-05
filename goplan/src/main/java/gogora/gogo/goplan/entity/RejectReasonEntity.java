package gogora.gogo.goplan.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "reject_reason", schema = "goPlan_KB", catalog = "")
public class RejectReasonEntity {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private long id;

    @Basic
    @Column(name = "reject_reason", nullable = true, length = 255)
    private String rejectReason;

    @Basic
    @Column(name = "reject_date", nullable = true)
    private Timestamp rejectDate;

    @Basic
    @Column(name = "status", nullable = true, length = 20)
    private String status;

    @Basic
    @Column(name = "rider_id", nullable = true)
    private Long riderId;

    @Basic
    @Column(name = "joinWeb_Id", nullable = true)
    private Long joinWebId;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    public String getRejectReason() {
        return rejectReason;
    }

    public void setRejectReason(String rejectReason) {
        this.rejectReason = rejectReason;
    }


    public Timestamp getRejectDate() {
        return rejectDate;
    }

    public void setRejectDate(Timestamp rejectDate) {
        this.rejectDate = rejectDate;
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
