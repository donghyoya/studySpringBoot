package gogora.gogo.goplan.entity;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "kb_balance_history", schema = "goPlan_KB", catalog = "")
public class KbBalanceHistoryEntity {
    private long id;
    private int balance;
    private Date date;
    private String cmpcd;

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
    @Column(name = "date", nullable = true)
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Basic
    @Column(name = "cmpcd", nullable = true, length = 3)
    public String getCmpcd() {
        return cmpcd;
    }

    public void setCmpcd(String cmpcd) {
        this.cmpcd = cmpcd;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        KbBalanceHistoryEntity that = (KbBalanceHistoryEntity) o;
        return id == that.id && balance == that.balance && Objects.equals(date, that.date) && Objects.equals(cmpcd, that.cmpcd);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, balance, date, cmpcd);
    }
}
