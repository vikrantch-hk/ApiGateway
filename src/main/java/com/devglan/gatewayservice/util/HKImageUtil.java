package com.devglan.gatewayservice.util;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import com.devglan.gatewayservice.constant.EnumImageFormat;
import com.devglan.gatewayservice.constant.ImageAlignmentEnum;
import com.devglan.gatewayservice.constant.ImageSizeEnum;
import com.devglan.gatewayservice.constant.ImageTypeConstants;
import com.devglan.gatewayservice.constant.Keys;
import com.devglan.gatewayservice.constant.StoreConfigKeys;
import com.devglan.gatewayservice.dto.ImageDTO;
import com.devglan.gatewayservice.exception.InvalidParameterException;
import com.devglan.gatewayservice.service.StoreService;

import javax.annotation.PostConstruct;
import javax.imageio.IIOException;
import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Iterator;

/**
 * Created by IntelliJ IDEA.
 * User: Vaibhav
 * Date: Apr 10, 2013
 * Time: 1:56:50 AM
 */
@Component
public class HKImageUtil {

	@Autowired
	private Environment env;

	@Autowired
	private StoreService storeService;
	
	
  //@Value("#{hkEnvProps['" + Keys.noOfImagesInRepositorySubDir + "']}")
  Long noOfImagesInRepositoryDir;
  //@Value("#{hkEnvProps['" + Keys.imageUploads + "']}")
  String imageUploads;

  private static Logger logger = LoggerFactory.getLogger(HKImageUtil.class);

  public static String imageUploadsPath;
  public static String TEMP_IMAGE_DIRECTORY;

  private static Long noOfImagesInRepositorySubDir = null;

  public static int CDN_NETWORKS = 10;
//  public static String DOWNLOAD_BUCKET = "hk-prod";

  public static final String variantImagePrefix = "prd_";
  public static final String packImagePrefix = "pck_";
  public static final String categoryImagePrefix = "cat_";
  public static final String offerImagePrefix = "ofr_";
  public static final String optionImagePrefix = "opt_";
  public static final String bannerImagePrefix = "bnr_";
  public static final String contentImagePrefix = "con_";
  public static final String freebieImagePrefix = "free_";
  public static final String categoryHeaderImagePrefix = "cat_head_";
  public static final String storeMenuImagePrefix = "smn_image_";
  public static final String articleImagePrefix = "article_";
  public static final String counsellorImagePrefix = "cnslr_";
  public static final String orderImagePrefix = "ordr_";
  public static final String gruntUserImagePrefix = "grunt_user";
  public static final String gruntPostImagePrefix = "grunt_post";
  public static final String gruntBadgeImagePrefix = "grunt_badge";
  public static final String userProfileImagePrefix = "user_profile";
  public static final String eventImagePrefix = "event_";
  public static final String userStoreImagePrefix = "user_store";
  public static final String genericImagePrefix = "generic";
  public static final String brandImagePrefix = "br_";
  public static final String counsellorServicePrefix = "cslr_svc_";
  public static final String topSearchPrefix = "ts_";
  public static String imageDistPrefix;
  public static String imageDistSuffix;
  
  //@Value("#{hkEnvProps['" + Keys.imageDistributionDomainPrefix + "']}")
  String imageDistributionDomainPrefix;

  //@Value("#{hkEnvProps['" + Keys.imageDistributionDomainSuffix + "']}")
  String imageDistributionDomainSuffix;

  @PostConstruct
  public void postConstruction() {
    imageUploadsPath = StringUtils.isNotBlank(imageUploads) ? imageUploads : "";
    noOfImagesInRepositorySubDir = (noOfImagesInRepositoryDir != null) ? noOfImagesInRepositoryDir : 0;
    TEMP_IMAGE_DIRECTORY = imageUploads + "/temp/";
    imageDistPrefix = StringUtils.isNotBlank(imageDistributionDomainPrefix) ? imageDistributionDomainPrefix : "";
    imageDistSuffix = StringUtils.isNotBlank(imageDistributionDomainSuffix) ? imageDistributionDomainSuffix : "";
  }

  public String getS3ImageUrl(Long storeId, Long imageFormat, ImageSizeEnum imageSize, ImageAlignmentEnum imageAlignmentEnum, String amazonImageId) {
    StringBuilder imageUrl = new StringBuilder();
    String prefix = "http://";

    String imageDistributionUrl = getImageDistributionUrl(storeId, amazonImageId);
    imageUrl.append(prefix)
        .append(imageDistributionUrl)
        .append(getS3ImageKey(imageSize,imageFormat, imageAlignmentEnum, amazonImageId));

    return imageUrl.toString();
  }

  private static String getS3ImageKey(ImageSizeEnum imageSize, Long imageFormat, ImageAlignmentEnum imageAlignmentEnum, String amazonImageId) {
    StringBuilder imageKey = new StringBuilder();
    EnumImageFormat enumImageFormat = EnumImageFormat.getImageFormatEnumById(imageFormat) == null
            ? EnumImageFormat.JPG : EnumImageFormat.getImageFormatEnumById(imageFormat);
    imageKey.append(amazonImageId)
        .append("_");
    if (imageAlignmentEnum != null) {
      imageKey.append(imageAlignmentEnum.getSuffix())
          .append("_");
    }
    imageKey.append(imageSize.getSuffix())
        .append("." + enumImageFormat.getExtension());

    return imageKey.toString();
  }

  private String getImageDistributionUrl(Long storeId, String amazonImageId) {
    StringBuilder imageDistributionUrl = new StringBuilder();

    //if image distribution prefix and suffix are not blank, then serve images from cdn, else direct from download bucket
    if (StringUtils.isNotBlank(imageDistPrefix) && StringUtils.isNotBlank(imageDistSuffix)) {
      imageDistributionUrl
          .append(imageDistPrefix)
          .append(getCdnNumber(amazonImageId))
          .append(".")
          .append(imageDistSuffix)
          .append("/");
    } else {
      String downloadBucket = storeService.getStoreConfigValue(storeId, StoreConfigKeys.S3_DOWNLOAD_BUCKET);
//      String downloadBucket = DOWNLOAD_BUCKET;
      imageDistributionUrl
          .append(downloadBucket)
          .append(".s3.amazonaws.com/");
    }

    return imageDistributionUrl.toString();
  }

  public static void syncImageInfo(ImageDTO imageDTO) throws IOException {
    boolean validImage = false;
    try {
      BufferedImage image = ImageIO.read(imageDTO.getImageFile());
      imageDTO.setWidth((long) image.getWidth());
      imageDTO.setHeight((long) image.getHeight());


      imageDTO.setChecksum(BaseUtils.getMD5Checksum(imageDTO.getImageFile()));

      validImage = true;
    } catch (IIOException iioe) {
      logger.error("Error while converting image to buffered image: " + imageDTO.getImageFile().getName() + ": " + iioe);
    }

    if (!validImage) {
      throw new InvalidParameterException("UNSUPPORTED_IMAGE_TYPE");
    }
  }

  private static Long getCdnNumber(String amazonImageId) {
    return (getImageId(amazonImageId) % CDN_NETWORKS) + 1;
  }

  private static Long getImageId(String amazonImageId) {
    if (StringUtils.isNotBlank(amazonImageId)) {
      String imageIdStr = amazonImageId.substring(amazonImageId.indexOf('/') + 1);
      imageIdStr = imageIdStr.replace(variantImagePrefix, "");
      imageIdStr = imageIdStr.replace(packImagePrefix, "");
      imageIdStr = imageIdStr.replace(categoryHeaderImagePrefix, "");
      imageIdStr = imageIdStr.replace(categoryImagePrefix, "");
      imageIdStr = imageIdStr.replace(offerImagePrefix, "");
      imageIdStr = imageIdStr.replace(optionImagePrefix, "");
      imageIdStr = imageIdStr.replace(bannerImagePrefix, "");
      imageIdStr = imageIdStr.replace(contentImagePrefix, "");
      imageIdStr = imageIdStr.replace(freebieImagePrefix, "");
      imageIdStr = imageIdStr.replace(storeMenuImagePrefix, "");
      imageIdStr = imageIdStr.replace(articleImagePrefix, "");
      imageIdStr = imageIdStr.replace(counsellorImagePrefix,"");
      imageIdStr = imageIdStr.replace(orderImagePrefix, "");
      imageIdStr = imageIdStr.replace(gruntUserImagePrefix, "");
      imageIdStr = imageIdStr.replace(gruntPostImagePrefix, "");
      imageIdStr = imageIdStr.replace(gruntBadgeImagePrefix, "");
      imageIdStr = imageIdStr.replace(brandImagePrefix, "");
      imageIdStr = imageIdStr.replace(counsellorServicePrefix, "");
      imageIdStr = imageIdStr.replace(topSearchPrefix, "");
      imageIdStr = imageIdStr.replace(userProfileImagePrefix,"");
      imageIdStr = imageIdStr.replace(userStoreImagePrefix,"");
      imageIdStr = imageIdStr.replace(eventImagePrefix,"");
      imageIdStr = imageIdStr.replace(genericImagePrefix,"");
      return Long.parseLong(imageIdStr);
    }
    return null;
  }

  public static String getAmazonImageId(int imageType, Long imageId) {
    StringBuilder imageKey = new StringBuilder();
    long noOfImagesInRepo = noOfImagesInRepositorySubDir != null ? noOfImagesInRepositorySubDir : 100;

    imageKey.append(imageId / noOfImagesInRepo + 1)
        .append("/")
        .append(getImagePrefix(imageType))
        .append(imageId);

    return imageKey.toString();
  }

 /* public static File getFileFromFileBean(FileBean tempImage) throws IOException {
    if (tempImage == null) {
      throw new InvalidParameterException("FILE_BEAN_CANNOT_BE_NULL");
    }

    File imageFile = new File(TEMP_IMAGE_DIRECTORY + System.currentTimeMillis() + "_" + BaseUtils.getRandomString(4) + ".jpg");
    imageFile.getParentFile().mkdirs();
    tempImage.save(imageFile);

    return imageFile;
  }*/

  public File getImage(String amazonImageId, Long storeId, Long imageFormat) {
    EnumImageFormat enumImageFormat = EnumImageFormat.getImageFormatEnumById(imageFormat) == null
            ? EnumImageFormat.JPG : EnumImageFormat.getImageFormatEnumById(imageFormat);
    File imageFile = new File(TEMP_IMAGE_DIRECTORY + System.currentTimeMillis() + "_" + BaseUtils.getRandomString(4) + "." + enumImageFormat.getExtension());

    try {
      URL amazonUrl = new URL(getS3ImageUrl(storeId, imageFormat, ImageSizeEnum.Original, null, amazonImageId));
      FileUtils.copyURLToFile(amazonUrl, imageFile);

      return imageFile;
    } catch (IOException ioe) {
      logger.error("Error encountered while getting image from url: " + ioe);
    }
    return null;
  }

  private static String getImagePrefix(int imageType) {
    if(imageType == ImageTypeConstants.ORDER){
      return orderImagePrefix;
    } else if(imageType == ImageTypeConstants.GRUNT_USER){
      return gruntUserImagePrefix;
    } else if(imageType == ImageTypeConstants.GRUNT_BADGE){
      return gruntBadgeImagePrefix;
    } else if(imageType == ImageTypeConstants.GRUNT_POST){
      return gruntPostImagePrefix;
    } else if(imageType == ImageTypeConstants.USER_PROFILE){
      return userProfileImagePrefix;
    } else if(imageType == ImageTypeConstants.USER_STORE){
      return userStoreImagePrefix;
    } else if(imageType == ImageTypeConstants.GENERIC_IMAGE){
      return genericImagePrefix;
    }
    throw new InvalidParameterException("NO_PREFIX_EXISTS_FOR_PASSED_IMAGE_ID");
  }

  public static boolean isJpegImage(String imagePath) {
    String imageFileType = getImageType(imagePath);
    return StringUtils.isNotBlank(imageFileType) && (imageFileType.equalsIgnoreCase("jpg") || imageFileType.equalsIgnoreCase("jpeg"));
  }

  public static String getImageType(String srcImage) {
    String formatName;

    try {
      ImageInputStream imageStream = ImageIO.createImageInputStream(new File(srcImage));
      Iterator<ImageReader> readers = ImageIO.getImageReaders(imageStream);
      ImageReader reader;
      if (!readers.hasNext()) {
        imageStream.close();
        throw new InvalidParameterException("Exception while finding image format", srcImage);
      } else {
        reader = readers.next();
      }

      reader.setInput(imageStream, true, true);

      reader.dispose();
      imageStream.close();

      formatName = reader.getFormatName();
    } catch (IOException e) {
      e.printStackTrace();
      throw new InvalidParameterException("Exception while finding image format", srcImage);
    }
    return formatName;
  }

  public String getS3UploadBucketForStore(Long storeId) {
    String uploadBucketForStore = storeService.getStoreConfigValue(storeId, StoreConfigKeys.S3_UPLOAD_BUCKET);

    String deployEnv = env.getActiveProfiles()[0];

    if (deployEnv.equals(Keys.devEnv)) {
      uploadBucketForStore = uploadBucketForStore.replaceAll(Keys.prodEnv, Keys.devEnv);
    } else if (deployEnv.equals(Keys.stagingEnv)) {
      uploadBucketForStore = uploadBucketForStore.replaceAll(Keys.prodEnv, Keys.stagingEnv);
    } else if (deployEnv.equals(Keys.qaEnv)) {
      uploadBucketForStore = uploadBucketForStore.replaceAll(Keys.prodEnv, Keys.qaEnv);
    }


    return uploadBucketForStore;
  }



}
