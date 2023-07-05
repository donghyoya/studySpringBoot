package gogora.gogo.goplan.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "seller", schema = "goPlan_KB", catalog = "")
public class SellerEntity {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private long id;

    @Basic
    @Column(name = "createdDate", nullable = true)
    private Timestamp createdDate;

    @Basic
    @Column(name = "deletedDate", nullable = true)
    private Timestamp deletedDate;

    @Basic
    @Column(name = "modifiedDate", nullable = true)
    private Timestamp modifiedDate;

    @Basic
    @Column(name = "balance", nullable = false)
    private long balance;

    @Basic
    @Column(name = "name", nullable = true, length = 50)
    private String name;

    @Basic
    @Column(name = "phone", nullable = true, length = 20)
    private String phone;

    @Basic
    @Column(name = "tell", nullable = true, length = 20)
    private String tell;

    @Basic
    @Column(name = "chargingPerHour", nullable = false)
    private int chargingPerHour;

    @Basic
    @Column(name = "seller_code", nullable = true, length = 20)
    private String sellerCode;

    @Basic
    @Column(name = "address", nullable = true, length = 255)
    private String address;

    @Basic
    @Column(name = "bossName", nullable = true, length = 255)
    private String bossName;

    @Basic
    @Column(name = "businessNumber", nullable = true, length = 255)
    private String businessNumber;

    @Basic
    @Column(name = "loginId", nullable = true, length = 255)
    private String loginId;

    @Basic
    @Column(name = "password", nullable = true, length = 255)
    private String password;

    @Basic
    @Column(name = "secretKey", nullable = true, length = 255)
    private String secretKey;

    @Basic
    @Column(name = "sector", nullable = true, length = 255)
    private String sector;

    @Basic
    @Column(name = "apiKey", nullable = true, length = 255)
    private String apiKey;

    @Basic
    @Column(name = "detailAddress", nullable = true, length = 255)
    private String detailAddress;

    @Basic
    @Column(name = "upTae", nullable = true, length = 255)
    private String upTae;

    @Basic
    @Column(name = "seller_UID", nullable = true, length = 255)
    private String sellerUid;

    @Basic
    @Column(name = "cmpcd", nullable = true, length = 255)
    private String cmpcd;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    public Timestamp getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }


    public Timestamp getDeletedDate() {
        return deletedDate;
    }

    public void setDeletedDate(Timestamp deletedDate) {
        this.deletedDate = deletedDate;
    }


    public Timestamp getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Timestamp modifiedDate) {
        this.modifiedDate = modifiedDate;
    }


    public long getBalance() {
        return balance;
    }

    public void setBalance(long balance) {
        this.balance = balance;
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


    public String getTell() {
        return tell;
    }

    public void setTell(String tell) {
        this.tell = tell;
    }


    public int getChargingPerHour() {
        return chargingPerHour;
    }

    public void setChargingPerHour(int chargingPerHour) {
        this.chargingPerHour = chargingPerHour;
    }


    public String getSellerCode() {
        return sellerCode;
    }

    public void setSellerCode(String sellerCode) {
        this.sellerCode = sellerCode;
    }


    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }


    public String getBossName() {
        return bossName;
    }

    public void setBossName(String bossName) {
        this.bossName = bossName;
    }


    public String getBusinessNumber() {
        return businessNumber;
    }

    public void setBusinessNumber(String businessNumber) {
        this.businessNumber = businessNumber;
    }


    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }


    public String getSector() {
        return sector;
    }

    public void setSector(String sector) {
        this.sector = sector;
    }


    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }


    public String getDetailAddress() {
        return detailAddress;
    }

    public void setDetailAddress(String detailAddress) {
        this.detailAddress = detailAddress;
    }


    public String getUpTae() {
        return upTae;
    }

    public void setUpTae(String upTae) {
        this.upTae = upTae;
    }


    public String getSellerUid() {
        return sellerUid;
    }

    public void setSellerUid(String sellerUid) {
        this.sellerUid = sellerUid;
    }


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
        SellerEntity that = (SellerEntity) o;
        return id == that.id && balance == that.balance && chargingPerHour == that.chargingPerHour && Objects.equals(createdDate, that.createdDate) && Objects.equals(deletedDate, that.deletedDate) && Objects.equals(modifiedDate, that.modifiedDate) && Objects.equals(name, that.name) && Objects.equals(phone, that.phone) && Objects.equals(tell, that.tell) && Objects.equals(sellerCode, that.sellerCode) && Objects.equals(address, that.address) && Objects.equals(bossName, that.bossName) && Objects.equals(businessNumber, that.businessNumber) && Objects.equals(loginId, that.loginId) && Objects.equals(password, that.password) && Objects.equals(secretKey, that.secretKey) && Objects.equals(sector, that.sector) && Objects.equals(apiKey, that.apiKey) && Objects.equals(detailAddress, that.detailAddress) && Objects.equals(upTae, that.upTae) && Objects.equals(sellerUid, that.sellerUid) && Objects.equals(cmpcd, that.cmpcd);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, createdDate, deletedDate, modifiedDate, balance, name, phone, tell, chargingPerHour, sellerCode, address, bossName, businessNumber, loginId, password, secretKey, sector, apiKey, detailAddress, upTae, sellerUid, cmpcd);
    }
}
