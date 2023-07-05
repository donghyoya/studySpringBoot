package gogora.gogo.goplan.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "Seller_roles", schema = "goPlan_KB", catalog = "")
public class SellerRolesEntity {

    @Id
    @Column(name = "Seller_id", nullable = false)
    private long sellerId;

    @Basic
    @Column(name = "roles", nullable = true, length = 255)
    private String roles;


    public long getSellerId() {
        return sellerId;
    }

    public void setSellerId(long sellerId) {
        this.sellerId = sellerId;
    }


    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SellerRolesEntity that = (SellerRolesEntity) o;
        return sellerId == that.sellerId && Objects.equals(roles, that.roles);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sellerId, roles);
    }
}
