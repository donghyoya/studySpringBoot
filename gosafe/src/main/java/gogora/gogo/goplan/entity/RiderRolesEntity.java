package gogora.gogo.goplan.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "rider_roles", schema = "goPlan_KB", catalog = "")
public class RiderRolesEntity {

    @Basic
    @Column(name = "driver_id", nullable = false)
    private long driverId;

    @Basic
    @Column(name = "roles", nullable = true, length = 255)
    private String roles;


    public long getDriverId() {
        return driverId;
    }

    public void setDriverId(long driverId) {
        this.driverId = driverId;
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
        RiderRolesEntity that = (RiderRolesEntity) o;
        return driverId == that.driverId && Objects.equals(roles, that.roles);
    }

    @Override
    public int hashCode() {
        return Objects.hash(driverId, roles);
    }
}
