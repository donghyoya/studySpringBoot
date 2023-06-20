package gogora.gogo.gosafe.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "seller", schema = "goPlan_KB", catalog = "")
public class SellerEntity {
    private long id;
    private Timestamp createdDate;
    private Timestamp deletedDate;
    private Timestamp modifiedDate;
    private long balance;
    private String name;
    private String phone;
    private String tell;
    private int chargingPerHour;
    private String sellerCode;
    private String address;
    private String bossName;
    private String businessNumber;
    private String loginId;
    private String password;
    private String secretKey;
    private String sector;
    private String apiKey;
    private String detailAddress;
    private String upTae;
    private String sellerUid;
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
    @Column(name = "balance", nullable = false)
    public long getBalance() {
        return balance;
    }

    public void setBalance(long balance) {
        this.balance = balance;
    }

    @Basic
    @Column(name = "name", nullable = true, length = 50)
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
    @Column(name = "tell", nullable = true, length = 20)
    public String getTell() {
        return tell;
    }

    public void setTell(String tell) {
        this.tell = tell;
    }

    @Basic
    @Column(name = "chargingPerHour", nullable = false)
    public int getChargingPerHour() {
        return chargingPerHour;
    }

    public void setChargingPerHour(int chargingPerHour) {
        this.chargingPerHour = chargingPerHour;
    }

    @Basic
    @Column(name = "seller_code", nullable = true, length = 20)
    public String getSellerCode() {
        return sellerCode;
    }

    public void setSellerCode(String sellerCode) {
        this.sellerCode = sellerCode;
    }

    @Basic
    @Column(name = "address", nullable = true, length = 255)
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Basic
    @Column(name = "bossName", nullable = true, length = 255)
    public String getBossName() {
        return bossName;
    }

    public void setBossName(String bossName) {
        this.bossName = bossName;
    }

    @Basic
    @Column(name = "businessNumber", nullable = true, length = 255)
    public String getBusinessNumber() {
        return businessNumber;
    }

    public void setBusinessNumber(String businessNumber) {
        this.businessNumber = businessNumber;
    }

    @Basic
    @Column(name = "loginId", nullable = true, length = 255)
    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
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
    @Column(name = "secretKey", nullable = true, length = 255)
    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    @Basic
    @Column(name = "sector", nullable = true, length = 255)
    public String getSector() {
        return sector;
    }

    public void setSector(String sector) {
        this.sector = sector;
    }

    @Basic
    @Column(name = "apiKey", nullable = true, length = 255)
    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    @Basic
    @Column(name = "detailAddress", nullable = true, length = 255)
    public String getDetailAddress() {
        return detailAddress;
    }

    public void setDetailAddress(String detailAddress) {
        this.detailAddress = detailAddress;
    }

    @Basic
    @Column(name = "upTae", nullable = true, length = 255)
    public String getUpTae() {
        return upTae;
    }

    public void setUpTae(String upTae) {
        this.upTae = upTae;
    }

    @Basic
    @Column(name = "seller_UID", nullable = true, length = 255)
    public String getSellerUid() {
        return sellerUid;
    }

    public void setSellerUid(String sellerUid) {
        this.sellerUid = sellerUid;
    }

    @Basic
    @Column(name = "cmpcd", nullable = true, length = 255)
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
