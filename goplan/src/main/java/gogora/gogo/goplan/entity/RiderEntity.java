package gogora.gogo.goplan.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "rider", schema = "goPlan_KB", catalog = "")
public class RiderEntity {

//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Id
//    @Column(name = "id", nullable = false)
//    private long id;
//
//    @Basic
//    @Column(name = "name", nullable = true, length = 20)
//    private String name;
//
//    @Basic
//    @Column(name = "phone", nullable = true, length = 20)
//    private String phone;
//
//    @Basic
//    @Column(name = "vcno_hngl_nm", nullable = true, length = 20)
//    private String vcnoHnglNm;
//
//    @Basic
//    @Column(name = "driver_id", nullable = true, length = 20)
//    private String driverId;
//
//    @Basic
//    @Column(name = "region", nullable = true, length = 10)
//    private String region;
//
//    @Basic
//    @Column(name = "loginId", nullable = true, length = 30)
//    private String loginId;
//
//    @Basic
//    @Column(name = "insuranceStatus", nullable = true, length = 10)
//    private String insuranceStatus;
//
//    @Basic
//    @Column(name = "mtdt", nullable = true, length = 8)
//    private String mtdt;
//
//    @Basic
//    @Column(name = "oprn_purp", nullable = true, length = 255)
//    private String oprnPurp;
//
//    @Basic
//    @Column(name = "effectiveStartDate", nullable = true)
//    private Timestamp effectiveStartDate;
//
//    @Basic
//    @Column(name = "effectiveEndDate", nullable = true)
//    private Timestamp effectiveEndDate;
//
//    @Basic
//    @Column(name = "gender", nullable = false)
//    private Integer gender;
//
//    @Basic
//    @Column(name = "password", nullable = true, length = 255)
//    private String password;
//
//    @Basic
//    @Column(name = "policy_number", nullable = true, length = 20)
//    private String policyNumber;
//
//    @Basic
//    @Column(name = "ssn", nullable = true, length = 255)
//    private String ssn;
//
//    @Basic
//    @Column(name = "status", nullable = true)
//    private int status;
//
//    @Basic
//    @Column(name = "createdDate", nullable = true)
//    private Timestamp createdDate;
//
//    @Basic
//    @Column(name = "deletedDate", nullable = true)
//    private Timestamp deletedDate;
//
//    @Basic
//    @Column(name = "modifiedDate", nullable = true)
//    private Timestamp modifiedDate;
//
//    @Basic
//    @Column(name = "seller_id", nullable = true)
//    private Long sellerId;
//
//    @Basic
//    @Column(name = "imagePath", nullable = true, length = 255)
//    private String imagePath;
//
//    @Basic
//    @Column(name = "applyStatus", nullable = true, length = 255)
//    private String applyStatus;
//
//    @Basic
//    @Column(name = "totalWebViewUrl", nullable = true, length = 255)
//    private String totalWebViewUrl;
//
//    @Basic
//    @Column(name = "balance", nullable = false)
//    private int balance;
//
//    @Basic
//    @Column(name = "fcmToken", nullable = true, length = 255)
//    private String fcmToken;
//
//    @Basic
//    @Column(name = "receivedDriverId", nullable = true, length = 255)
//    private String receivedDriverId;
//
//    @Basic
//    @Column(name = "applicationNumber", nullable = true, length = 20)
//    private String applicationNumber;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(unique = true)
    private String phone;

    @Column(name = "vcno_hngl_nm")
    private String vcnoHnglNm;

    @Column(name = "driver_id")
    private String driverId;

    private String region;

    @Column(name = "loginId")
    private String loginId;

    @Column(name = "insuranceStatus")
    private String insuranceStatus;

    private String mtdt;

    @Column(name = "oprn_purp")
    private String oprnPurp;

    @Column(name = "effectiveStartDate")
    private LocalDateTime effectiveStartDate;

    @Column(name = "effectiveEndDate")
    private LocalDateTime effectiveEndDate;

    private Integer gender;

    private String password;

    @Column(name = "policy_number")
    private String policyNumber;

    private String ssn;

    private Integer status;

    @Column(name = "createdDate")
    private LocalDateTime createdDate;

    @Column(name = "deletedDate")
    private LocalDateTime deletedDate;

    @Column(name = "modifiedDate")
    private LocalDateTime modifiedDate;

    @Column(name = "seller_id")
    private Long sellerId;

    @Column(name = "imagePath")
    private String imagePath;

    @Column(name = "applyStatus")
    private String applyStatus;

    @Column(name = "totalWebViewUrl")
    private String totalWebViewUrl;

    private Integer balance;

    @Column(name = "fcmToken")
    private String fcmToken;

    @Column(name = "receivedDriverId")
    private String receivedDriverId;

    @Column(name = "applicationNumber")
    private String applicationNumber;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }


    public String getVcnoHnglNm() {
        return vcnoHnglNm;
    }

    public void setVcnoHnglNm(String vcnoHnglNm) {
        this.vcnoHnglNm = vcnoHnglNm;
    }


    public String getDriverId() {
        return driverId;
    }

    public void setDriverId(String driverId) {
        this.driverId = driverId;
    }


    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }


    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }


    public String getInsuranceStatus() {
        return insuranceStatus;
    }

    public void setInsuranceStatus(String insuranceStatus) {
        this.insuranceStatus = insuranceStatus;
    }


    public String getMtdt() {
        return mtdt;
    }

    public void setMtdt(String mtdt) {
        this.mtdt = mtdt;
    }


    public String getOprnPurp() {
        return oprnPurp;
    }

    public void setOprnPurp(String oprnPurp) {
        this.oprnPurp = oprnPurp;
    }


    public LocalDateTime getEffectiveStartDate() {
        return effectiveStartDate;
    }

    public void setEffectiveStartDate(LocalDateTime effectiveStartDate) {
        this.effectiveStartDate = effectiveStartDate;
    }


    public LocalDateTime getEffectiveEndDate() {
        return effectiveEndDate;
    }


    public int getGender() {
        return gender;
    }

    public void setEffectiveEndDate(LocalDateTime effectiveEndDate) {
        this.effectiveEndDate = effectiveEndDate;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public String getPolicyNumber() {
        return policyNumber;
    }

    public void setPolicyNumber(String policyNumber) {
        this.policyNumber = policyNumber;
    }


    public String getSsn() {
        return ssn;
    }

    public void setSsn(String ssn) {
        this.ssn = ssn;
    }


    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }


    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }


    public LocalDateTime getDeletedDate() {
        return deletedDate;
    }

    public void setDeletedDate(LocalDateTime deletedDate) {
        this.deletedDate = deletedDate;
    }


    public LocalDateTime getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(LocalDateTime modifiedDate) {
        this.modifiedDate = modifiedDate;
    }


    public Long getSellerId() {
        return sellerId;
    }

    public void setSellerId(Long sellerId) {
        this.sellerId = sellerId;
    }


    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }


    public String getApplyStatus() {
        return applyStatus;
    }

    public void setApplyStatus(String applyStatus) {
        this.applyStatus = applyStatus;
    }


    public String getTotalWebViewUrl() {
        return totalWebViewUrl;
    }

    public void setTotalWebViewUrl(String totalWebViewUrl) {
        this.totalWebViewUrl = totalWebViewUrl;
    }


    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }


    public String getFcmToken() {
        return fcmToken;
    }

    public void setFcmToken(String fcmToken) {
        this.fcmToken = fcmToken;
    }


    public String getReceivedDriverId() {
        return receivedDriverId;
    }

    public void setReceivedDriverId(String receivedDriverId) {
        this.receivedDriverId = receivedDriverId;
    }


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
