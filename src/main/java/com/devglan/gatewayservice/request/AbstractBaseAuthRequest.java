package com.devglan.gatewayservice.request;

/**
 * @author Rimal
 */
public class AbstractBaseAuthRequest extends AbstractBaseRequest {

//  private static Logger logger = LoggerFactory.getLogger(AbstractBaseAuthRequest.class);

  protected String apiAccessKey;


  public AbstractBaseAuthRequest() {
  }

  public AbstractBaseAuthRequest(Long storeId) {
    super(storeId);
  }

  public AbstractBaseAuthRequest(Long storeId,Long platformId) {
    super(storeId,platformId);
  }

  @Override
  protected boolean validate() {
    return super.validate();

    /*if (valid && StringUtils.isBlank(apiAccessKey)) {
      logger.error("Error validating " + this.getClass().getSimpleName() + " api access key cannot be null");
      valid = false;
    }*/

//    return valid;
  }


  public String getApiAccessKey() {
    return apiAccessKey;
  }

  public void setApiAccessKey(String apiAccessKey) {
    this.apiAccessKey = apiAccessKey;
  }
}
