package gogora.gogo.goplan.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "calls", schema = "goPlan_KB", catalog = "")
public class CallsEntity {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private long id;

    @Basic
    @Column(name = "balance", nullable = false)
    private long balance;

    @Basic
    @Column(name = "appoint_time", nullable = true)
    private Timestamp appointTime;

    @Basic
    @Column(name = "complete_time", nullable = true)
    private Timestamp completeTime;

    @Basic
    @Column(name = "call_id", nullable = true, length = 30)
    private String callId;

    @Basic
    @Column(name = "pickup_time", nullable = true)
    private Timestamp pickupTime;

    @Basic
    @Column(name = "request_time", nullable = true)
    private Timestamp requestTime;

    @Basic
    @Column(name = "delivery_Address", nullable = true, length = 500)
    private String deliveryAddress;

    @Basic
    @Column(name = "delivery_status", nullable = true, length = 20)
    private String deliveryStatus;

    @Basic
    @Column(name = "modifiedDate", nullable = true)
    private Timestamp modifiedDate;

    @Basic
    @Column(name = "pickUp_Address", nullable = true, length = 500)
    private String pickUpAddress;

    @Basic
    @Column(name = "rider_id", nullable = true)
    private Long riderId;
    private String companyName;
    private String kbCallId;
    private Long totalTime;
    private long dailyTotalRiding;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    public long getBalance() {
        return balance;
    }

    public void setBalance(long balance) {
        this.balance = balance;
    }


    public Timestamp getAppointTime() {
        return appointTime;
    }

    public void setAppointTime(Timestamp appointTime) {
        this.appointTime = appointTime;
    }


    public Timestamp getCompleteTime() {
        return completeTime;
    }

    public void setCompleteTime(Timestamp completeTime) {
        this.completeTime = completeTime;
    }


    public String getCallId() {
        return callId;
    }

    public void setCallId(String callId) {
        this.callId = callId;
    }


    public Timestamp getPickupTime() {
        return pickupTime;
    }

    public void setPickupTime(Timestamp pickupTime) {
        this.pickupTime = pickupTime;
    }


    public Timestamp getRequestTime() {
        return requestTime;
    }

    public void setRequestTime(Timestamp requestTime) {
        this.requestTime = requestTime;
    }


    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }


    public String getDeliveryStatus() {
        return deliveryStatus;
    }

    public void setDeliveryStatus(String deliveryStatus) {
        this.deliveryStatus = deliveryStatus;
    }


    public Timestamp getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Timestamp modifiedDate) {
        this.modifiedDate = modifiedDate;
    }


    public String getPickUpAddress() {
        return pickUpAddress;
    }

    public void setPickUpAddress(String pickUpAddress) {
        this.pickUpAddress = pickUpAddress;
    }


    public Long getRiderId() {
        return riderId;
    }

    public void setRiderId(Long riderId) {
        this.riderId = riderId;
    }

    @Basic
    @Column(name = "companyName", nullable = true, length = 255)
    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    @Basic
    @Column(name = "kb_call_id", nullable = true, length = 16)
    public String getKbCallId() {
        return kbCallId;
    }

    public void setKbCallId(String kbCallId) {
        this.kbCallId = kbCallId;
    }

    @Basic
    @Column(name = "totalTime", nullable = true)
    public Long getTotalTime() {
        return totalTime;
    }

    public void setTotalTime(Long totalTime) {
        this.totalTime = totalTime;
    }

    @Basic
    @Column(name = "dailyTotalRiding", nullable = false)
    public long getDailyTotalRiding() {
        return dailyTotalRiding;
    }

    public void setDailyTotalRiding(long dailyTotalRiding) {
        this.dailyTotalRiding = dailyTotalRiding;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CallsEntity that = (CallsEntity) o;
        return id == that.id && balance == that.balance && dailyTotalRiding == that.dailyTotalRiding && Objects.equals(appointTime, that.appointTime) && Objects.equals(completeTime, that.completeTime) && Objects.equals(callId, that.callId) && Objects.equals(pickupTime, that.pickupTime) && Objects.equals(requestTime, that.requestTime) && Objects.equals(deliveryAddress, that.deliveryAddress) && Objects.equals(deliveryStatus, that.deliveryStatus) && Objects.equals(modifiedDate, that.modifiedDate) && Objects.equals(pickUpAddress, that.pickUpAddress) && Objects.equals(riderId, that.riderId) && Objects.equals(companyName, that.companyName) && Objects.equals(kbCallId, that.kbCallId) && Objects.equals(totalTime, that.totalTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, balance, appointTime, completeTime, callId, pickupTime, requestTime, deliveryAddress, deliveryStatus, modifiedDate, pickUpAddress, riderId, companyName, kbCallId, totalTime, dailyTotalRiding);
    }
}
