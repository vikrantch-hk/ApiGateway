package com.devglan.gatewayservice.pojo;


import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@NamedQueries({
    @NamedQuery(name = "getAllStores", query = "select st from Store st"),
    @NamedQuery(name = "getStoreById", query = "select st from Store st where st.id=:storeId"),
    @NamedQuery(name = "getAllApiEnabledStores", query = "select st from Store st where st.apiEnabled=:apiEnabled"),
        @NamedQuery(name = "getCFAStoreByStoreIdAndStoreType", query = "select st from Store st where st.storeType=2 and " +
                "id=:storeId")
})
@Entity
@Table(name = "store")
public class Store implements java.io.Serializable {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "id", unique = true, nullable = false)
  private Long id;

  @Column(name = "name", nullable = false, length = 45)
  private String name;

  @Column(name = "prefix", nullable = false, length = 45)
  private String prefix;

  @Column(name = "logo_url", nullable = false, length = 45)
  private String logoUrl;

  @Column(name = "favicon_url", nullable = false, length = 45)
  private String faviconUrl;

  @Column(name = "api_enabled", nullable = false)
  private boolean apiEnabled;

  @Column(name = "store_type", nullable = false)
  private Long storeType;

  @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "store")
  @Cascade(org.hibernate.annotations.CascadeType.DELETE_ORPHAN)
  private Set<StoreConfigValues> configValuesSet = new HashSet<StoreConfigValues>(10);


  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getPrefix() {
    return this.prefix;
  }

  public void setPrefix(String prefix) {
    this.prefix = prefix;
  }

  public String getLogoUrl() {
    return this.logoUrl;
  }

  public void setLogoUrl(String logoUrl) {
    this.logoUrl = logoUrl;
  }

  public String getFaviconUrl() {
    return this.faviconUrl;
  }

  public void setFaviconUrl(String faviconUrl) {
    this.faviconUrl = faviconUrl;
  }

  public boolean isApiEnabled() {
    return apiEnabled;
  }

  public void setApiEnabled(boolean apiEnabled) {
    this.apiEnabled = apiEnabled;
  }

  public Long getStoreType() {
    return storeType;
  }

  public void setStoreType(Long storeType) {
    this.storeType = storeType;
  }

  public Set<StoreConfigValues> getConfigValuesSet() {
    return configValuesSet;
  }

  public void setConfigValuesSet(Set<StoreConfigValues> configValuesSet) {
    this.configValuesSet = configValuesSet;
  }
}