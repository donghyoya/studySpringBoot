package gogora.gogo.goplan.entity.query;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "rider")
@Data
public class Rider {

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

    @Column(name = "loginid")
    private String loginId;

    @Column(name = "insurancestatus")
    private String insuranceStatus;

    private String mtdt;

    @Column(name = "oprn_purp")
    private String oprnPurp;

    @Column(name = "effectivestartdate")
    private LocalDateTime effectiveStartDate;

    @Column(name = "effectiveenddate")
    private LocalDateTime effectiveEndDate;

    private Integer gender;

    private String password;

    @Column(name = "policy_number")
    private String policyNumber;

    private String ssn;

    private Integer status;

    @Column(name = "createddate")
    private LocalDateTime createdDate;

    @Column(name = "deleteddate")
    private LocalDateTime deletedDate;

    @Column(name = "modifieddate")
    private LocalDateTime modifiedDate;

    @Column(name = "seller_id")
    private Long sellerId;

    @Column(name = "imagepath")
    private String imagePath;

    @Column(name = "applystatus")
    private String applyStatus;

    @Column(name = "totalwebviewurl")
    private String totalWebViewUrl;

    private Integer balance;

    @Column(name = "fcmtoken")
    private String fcmToken;

    @Column(name = "receiveddriverid")
    private String receivedDriverId;

    @Column(name = "applicationnumber")
    private String applicationNumber;

    public void updateRiderInfo(Rider rider) {
        this.id = rider.getId();
        this.name = rider.getName();
        this.phone = rider.getPhone();
        this.vcnoHnglNm = rider.getVcnoHnglNm();
        this.driverId = rider.getDriverId();
        this.region = rider.getRegion();
        this.loginId = rider.getLoginId();
        this.insuranceStatus = rider.getInsuranceStatus();
        this.mtdt = rider.getMtdt();
        this.oprnPurp = rider.getOprnPurp();
        this.effectiveStartDate = rider.getEffectiveStartDate();
        this.effectiveEndDate = rider.getEffectiveEndDate();
        this.gender = rider.getGender();
        this.password = rider.getPassword();
        this.policyNumber = rider.getPolicyNumber();
        this.ssn = rider.getSsn();
        this.status = rider.getStatus();
        this.createdDate = rider.getCreatedDate();
        this.deletedDate = rider.getDeletedDate();
        this.modifiedDate = rider.getModifiedDate();
        this.sellerId = rider.getSellerId();
        this.imagePath = rider.getImagePath();
        this.applyStatus = rider.getApplyStatus();
        this.totalWebViewUrl = rider.getTotalWebViewUrl();
        this.balance = rider.getBalance();
        this.fcmToken = rider.getFcmToken();
        this.receivedDriverId = rider.getReceivedDriverId();
        this.applicationNumber = rider.getApplicationNumber();
    }

}
