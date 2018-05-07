package com.devglan.gatewayservice.pojo;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author Rimal
 */
@Entity
@Table(name = "store_config_values")
@NamedQueries({
        @NamedQuery(name = "findStoreConfigValuesByStoreIdAndConfigKey", query = "from StoreConfigValues scv " +
                "where scv.configKey = :configKey and scv.store.id=:storeId")})
public class StoreConfigValues implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "id", unique = true, nullable = false)
  private Long id;

  @Column(name = "config_key", nullable = false, length = 45)
  private String configKey;

  @Column(name = "config_value", nullable = false, length = 45)
  private String configValue;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "store_id", nullable = false)
  private Store store;


  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getConfigKey() {
    return configKey;
  }

  public void setConfigKey(String configKey) {
    this.configKey = configKey;
  }

  public String getConfigValue() {
    return configValue;
  }

  public void setConfigValue(String configValue) {
    this.configValue = configValue;
  }

  public Store getStore() {
    return store;
  }

  public void setStore(Store store) {
    this.store = store;
  }
}
