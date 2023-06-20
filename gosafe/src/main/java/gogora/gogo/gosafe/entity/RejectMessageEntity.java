package gogora.gogo.gosafe.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "reject_message", schema = "goPlan_KB", catalog = "")
public class RejectMessageEntity {
    private long id;
    private String rejectMessage;
    private String rejectReason;

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
    @Column(name = "reject_message", nullable = true, length = 255)
    public String getRejectMessage() {
        return rejectMessage;
    }

    public void setRejectMessage(String rejectMessage) {
        this.rejectMessage = rejectMessage;
    }

    @Basic
    @Column(name = "reject_reason", nullable = true, length = 255)
    public String getRejectReason() {
        return rejectReason;
    }

    public void setRejectReason(String rejectReason) {
        this.rejectReason = rejectReason;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RejectMessageEntity that = (RejectMessageEntity) o;
        return id == that.id && Objects.equals(rejectMessage, that.rejectMessage) && Objects.equals(rejectReason, that.rejectReason);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, rejectMessage, rejectReason);
    }
}
