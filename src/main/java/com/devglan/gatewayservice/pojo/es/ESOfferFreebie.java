package com.devglan.gatewayservice.pojo.es;

import java.io.Serializable;

public class ESOfferFreebie implements Serializable {
  private Long id;
  private Long qty;

  Long getId() {
    return id;
  }

  void setId(Long id) {
    this.id = id;
  }
}
