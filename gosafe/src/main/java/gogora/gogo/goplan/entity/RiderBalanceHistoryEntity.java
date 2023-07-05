package gogora.gogo.goplan.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "rider_balance_history", schema = "goPlan_KB", catalog = "")
public class RiderBalanceHistoryEntity {
    private long id;
    private int balance;
    private String balanceStatus;
    private Timestamp createdDate;
    private Long callId;
    private Long riderId;

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
    @Column(name = "balance", nullable = false)
    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    @Basic
    @Column(name = "balanceStatus", nullable = true, length = 255)
    public String getBalanceStatus() {
        return balanceStatus;
    }

    public void setBalanceStatus(String balanceStatus) {
        this.balanceStatus = balanceStatus;
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
    @Column(name = "call_id", nullable = true)
    public Long getCallId() {
        return callId;
    }

    public void setCallId(Long callId) {
        this.callId = callId;
    }

    @Basic
    @Column(name = "rider_id", nullable = true)
    public Long getRiderId() {
        return riderId;
    }

    public void setRiderId(Long riderId) {
        this.riderId = riderId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RiderBalanceHistoryEntity that = (RiderBalanceHistoryEntity) o;
        return id == that.id && balance == that.balance && Objects.equals(balanceStatus, that.balanceStatus) && Objects.equals(createdDate, that.createdDate) && Objects.equals(callId, that.callId) && Objects.equals(riderId, that.riderId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, balance, balanceStatus, createdDate, callId, riderId);
    }
}
