package gogora.gogo.goplan.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "reject_message", schema = "goPlan_KB", catalog = "")
public class RejectMessageEntity {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private long id;

    @Basic
    @Column(name = "reject_message", nullable = true, length = 255)
    private String rejectMessage;

    @Basic
    @Column(name = "reject_reason", nullable = true, length = 255)
    private String rejectReason;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    public String getRejectMessage() {
        return rejectMessage;
    }

    public void setRejectMessage(String rejectMessage) {
        this.rejectMessage = rejectMessage;
    }


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
