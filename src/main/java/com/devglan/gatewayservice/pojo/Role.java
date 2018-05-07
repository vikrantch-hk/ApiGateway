package com.devglan.gatewayservice.pojo;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.io.Serializable;

/**
 * @author adlakha.vaibhav
 */
@NamedQueries({
    @NamedQuery(name = "getRoleByName", query = "from Role r where r.name = :roleName")
})
@Entity
@Table(name = "role")
public class Role implements Serializable {

  @Id
  @Column(name = "name")
  private String name;

  @Column(name = "description")
  private String desc;

  @ManyToMany(fetch = FetchType.EAGER)
  @Fetch(value = FetchMode.SELECT)
  @JoinTable(name = "role_permission",
      joinColumns = @JoinColumn(name = "role_name", referencedColumnName = "name"),
      inverseJoinColumns = @JoinColumn(name = "permission_name", referencedColumnName = "name")
  )

  private Set<Permission> permissions = new HashSet<Permission>();

  @Transient
  private boolean selected;

  public Role() {
  }

  public Role(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDesc() {
    return desc;
  }

  public void setDesc(String desc) {
    this.desc = desc;
  }

  public Set<Permission> getPermissions() {
    return permissions;
  }

  public void setPermissions(Set<Permission> permissions) {
    this.permissions = permissions;
  }

  public boolean isSelected() {
    return selected;
  }

  public void setSelected(boolean selected) {
    this.selected = selected;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }

    if (o instanceof Role) {
      Role role = (Role) o;
      return new EqualsBuilder()
          .append(this.name, role.getName())
          .isEquals();
    }

    return false;
    /*if (this == o) return true;
    if (!(o instanceof Role)) return false;

    Role role = (Role) o;

    if (!name.equals(role.name)) return false;

    return true;*/
  }

  @Override
  public int hashCode() {
    return new HashCodeBuilder()
        .append(this.name)
        .toHashCode();
//    return name.hashCode();
  }
}