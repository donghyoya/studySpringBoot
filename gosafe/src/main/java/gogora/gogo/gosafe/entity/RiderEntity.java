package gogora.gogo.gosafe.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "rider", schema = "goPlan_KB", catalog = "")
public class RiderEntity {
    private long id;
    private String name;
    private String phone;
    private String vcnoHnglNm;
    private String driverId;
    private String region;
    private String loginId;
    private String insuranceStatus;
    private String mtdt;
    private String oprnPurp;
    private Timestamp effectiveStartDate;
    private Timestamp effectiveEndDate;
    private int gender;
    private String password;
    private String policyNumber;
    private String ssn;
    private Integer status;
    private Timestamp createdDate;
    private Timestamp deletedDate;
    private Timestamp modifiedDate;
    private Long sellerId;
    private String imagePath;
    private String applyStatus;
    private String totalWebViewUrl;
    private int balance;
    private String fcmToken;
    private String receivedDriverId;
    private String applicationNumber;

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
    @Column(name = "name", nullable = true, length = 20)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "phone", nullable = true, length = 20)
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Basic
    @Column(name = "vcno_hngl_nm", nullable = true, length = 20)
    public String getVcnoHnglNm() {
        return vcnoHnglNm;
    }

    public void setVcnoHnglNm(String vcnoHnglNm) {
        this.vcnoHnglNm = vcnoHnglNm;
    }

    @Basic
    @Column(name = "driver_id", nullable = true, length = 20)
    public String getDriverId() {
        return driverId;
    }

    public void setDriverId(String driverId) {
        this.driverId = driverId;
    }

    @Basic
    @Column(name = "region", nullable = true, length = 10)
    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    @Basic
    @Column(name = "loginId", nullable = true, length = 30)
    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    @Basic
    @Column(name = "insuranceStatus", nullable = true, length = 10)
    public String getInsuranceStatus() {
        return insuranceStatus;
    }

    public void setInsuranceStatus(String insuranceStatus) {
        this.insuranceStatus = insuranceStatus;
    }

    @Basic
    @Column(name = "mtdt", nullable = true, length = 8)
    public String getMtdt() {
        return mtdt;
    }

    public void setMtdt(String mtdt) {
        this.mtdt = mtdt;
    }

    @Basic
    @Column(name = "oprn_purp", nullable = true, length = 255)
    public String getOprnPurp() {
        return oprnPurp;
    }

    public void setOprnPurp(String oprnPurp) {
        this.oprnPurp = oprnPurp;
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
    @Column(name = "effectiveEndDate", nullable = true)
    public Timestamp getEffectiveEndDate() {
        return effectiveEndDate;
    }

    public void setEffectiveEndDate(Timestamp effectiveEndDate) {
        this.effectiveEndDate = effectiveEndDate;
    }

    @Basic
    @Column(name = "gender", nullable = false)
    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    @Basic
    @Column(name = "password", nullable = true, length = 255)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
    @Column(name = "ssn", nullable = true, length = 255)
    public String getSsn() {
        return ssn;
    }

    public void setSsn(String ssn) {
        this.ssn = ssn;
    }

    @Basic
    @Column(name = "status", nullable = true)
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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
    @Column(name = "deletedDate", nullable = true)
    public Timestamp getDeletedDate() {
        return deletedDate;
    }

    public void setDeletedDate(Timestamp deletedDate) {
        this.deletedDate = deletedDate;
    }

    @Basic
    @Column(name = "modifiedDate", nullable = true)
    public Timestamp getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Timestamp modifiedDate) {
        this.modifiedDate = modifiedDate;
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
    @Column(name = "imagePath", nullable = true, length = 255)
    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    @Basic
    @Column(name = "applyStatus", nullable = true, length = 255)
    public String getApplyStatus() {
        return applyStatus;
    }

    public void setApplyStatus(String applyStatus) {
        this.applyStatus = applyStatus;
    }

    @Basic
    @Column(name = "totalWebViewUrl", nullable = true, length = 255)
    public String getTotalWebViewUrl() {
        return totalWebViewUrl;
    }

    public void setTotalWebViewUrl(String totalWebViewUrl) {
        this.totalWebViewUrl = totalWebViewUrl;
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
    @Column(name = "fcmToken", nullable = true, length = 255)
    public String getFcmToken() {
        return fcmToken;
    }

    public void setFcmToken(String fcmToken) {
        this.fcmToken = fcmToken;
    }

    @Basic
    @Column(name = "receivedDriverId", nullable = true, length = 255)
    public String getReceivedDriverId() {
        return receivedDriverId;
    }

    public void setReceivedDriverId(String receivedDriverId) {
        this.receivedDriverId = receivedDriverId;
    }

    @Basic
    @Column(name = "applicationNumber", nullable = true, length = 20)
    public String getApplicationNumber() {
        return applicationNumber;
    }

    public void setApplicationNumber(String applicationNumber) {
        this.applicationNumber = applicationNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RiderEntity that = (RiderEntity) o;
        return id == that.id && gender == that.gender && balance == that.balance && Objects.equals(name, that.name) && Objects.equals(phone, that.phone) && Objects.equals(vcnoHnglNm, that.vcnoHnglNm) && Objects.equals(driverId, that.driverId) && Objects.equals(region, that.region) && Objects.equals(loginId, that.loginId) && Objects.equals(insuranceStatus, that.insuranceStatus) && Objects.equals(mtdt, that.mtdt) && Objects.equals(oprnPurp, that.oprnPurp) && Objects.equals(effectiveStartDate, that.effectiveStartDate) && Objects.equals(effectiveEndDate, that.effectiveEndDate) && Objects.equals(password, that.password) && Objects.equals(policyNumber, that.policyNumber) && Objects.equals(ssn, that.ssn) && Objects.equals(status, that.status) && Objects.equals(createdDate, that.createdDate) && Objects.equals(deletedDate, that.deletedDate) && Objects.equals(modifiedDate, that.modifiedDate) && Objects.equals(sellerId, that.sellerId) && Objects.equals(imagePath, that.imagePath) && Objects.equals(applyStatus, that.applyStatus) && Objects.equals(totalWebViewUrl, that.totalWebViewUrl) && Objects.equals(fcmToken, that.fcmToken) && Objects.equals(receivedDriverId, that.receivedDriverId) && Objects.equals(applicationNumber, that.applicationNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, phone, vcnoHnglNm, driverId, region, loginId, insuranceStatus, mtdt, oprnPurp, effectiveStartDate, effectiveEndDate, gender, password, policyNumber, ssn, status, createdDate, deletedDate, modifiedDate, sellerId, imagePath, applyStatus, totalWebViewUrl, balance, fcmToken, receivedDriverId, applicationNumber);
    }
}
