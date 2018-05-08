package com.hk.gateway.dto;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.hk.gateway.constant.DtoJsonConstants;
import com.hk.gateway.pojo.Image;
import com.hk.gateway.response.JSONObject;

/**
 * Created by IntelliJ IDEA. User: Vaibhav Date: Apr 12, 2013 Time: 11:35:01 PM
 */
public class ImageDTO extends JSONObject {

	private Long id;
	protected Long storeId;
	protected String amazonImageId;
	protected Long width;
	protected Long height;
	protected String altText;
	/*
	 * private boolean hidden; private boolean published;
	 */
	protected String landingPage;
	protected String caption;
	protected Long type;

	protected boolean fallbackImage;
	protected String checksum;
	protected File imageFile;

	protected Long imageFormat;
	protected boolean oImgAv;

	public ImageDTO() {
	}

	public ImageDTO(Image image) {
		this.id = image.getId();
		this.storeId = image.getStoreId();
		this.amazonImageId = image.getAmazonImageId();
		this.width = image.getWidth();
		this.height = image.getHeight();
		this.altText = image.getAltText();
		this.landingPage = image.getLandingPage();
		this.caption = image.getCaption();
		this.type = image.getType();
		this.checksum = image.getChecksum();
		this.imageFormat = image.getImageFormat();
		this.oImgAv = image.isoImgAvl();
	}

	public ImageDTO(ImageDTO imageDTO) {
		this.id = imageDTO.getId();
		this.storeId = imageDTO.getStoreId();
		this.amazonImageId = imageDTO.getAmazonImageId();
		this.width = imageDTO.getWidth();
		this.height = imageDTO.getHeight();
		this.altText = imageDTO.getAltText();
		this.landingPage = imageDTO.getLandingPage();
		this.caption = imageDTO.getCaption();
		this.type = imageDTO.getType();
		this.fallbackImage = imageDTO.isFallbackImage();
		this.checksum = imageDTO.getChecksum();
		this.imageFormat = imageDTO.getImageFormat();
		this.oImgAv = imageDTO.isoImgAv();
	}

	public Image extractImage() {
		Image image = new Image();
		image.setId(this.id);
		image.setAmazonImageId(this.amazonImageId);
		image.setType(this.type);
		image.setChecksum(this.checksum);
		image.setWidth(this.width);
		image.setHeight(this.height);
		image.setAltText(this.altText);
		image.setLandingPage(this.landingPage);
		image.setCaption(this.caption);
		image.setImageFormat(this.imageFormat);
		image.setoImgAvl(this.oImgAv);
		return image;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getStoreId() {
		return storeId;
	}

	public void setStoreId(Long storeId) {
		this.storeId = storeId;
	}

	public String getAmazonImageId() {
		return amazonImageId;
	}

	public void setAmazonImageId(String amazonImageId) {
		this.amazonImageId = amazonImageId;
	}

	public Long getWidth() {
		return width;
	}

	public void setWidth(Long width) {
		this.width = width;
	}

	public Long getHeight() {
		return height;
	}

	public void setHeight(Long height) {
		this.height = height;
	}

	public String getAltText() {
		return altText;
	}

	public void setAltText(String altText) {
		this.altText = altText;
	}

	public Long getImageFormat() {
		return imageFormat;
	}

	public void setImageFormat(Long imageFormat) {
		this.imageFormat = imageFormat;
	}

	/*
	 * public boolean isHidden() { return hidden; }
	 * 
	 * public void setHidden(boolean hidden) { this.hidden = hidden; }
	 * 
	 * public boolean isPublished() { return published; }
	 * 
	 * public void setPublished(boolean published) { this.published = published; }
	 */

	public String getLandingPage() {
		return landingPage;
	}

	public void setLandingPage(String landingPage) {
		this.landingPage = landingPage;
	}

	public String getCaption() {
		return caption;
	}

	public void setCaption(String caption) {
		this.caption = caption;
	}

	public Long getType() {
		return type;
	}

	public void setType(Long type) {
		this.type = type;
	}

	public boolean isFallbackImage() {
		return fallbackImage;
	}

	public void setFallbackImage(boolean fallbackImage) {
		this.fallbackImage = fallbackImage;
	}

	public String getChecksum() {
		return checksum;
	}

	public void setChecksum(String checksum) {
		this.checksum = checksum;
	}

	public File getImageFile() {
		return imageFile;
	}

	public void setImageFile(File imageFile) {
		this.imageFile = imageFile;
	}

	public boolean isoImgAv() {
		return oImgAv;
	}

	public void setoImgAv(boolean oImgAv) {
		this.oImgAv = oImgAv;
	}

	@Override
	protected List<String> getKeys() {
		List<String> keyList = new ArrayList<String>(10);
		keyList.add(DtoJsonConstants.LANDING_PAGE);
		keyList.add(DtoJsonConstants.ALT_TEXT);
		keyList.add(DtoJsonConstants.CAPTION);
		keyList.add(DtoJsonConstants.FALLBACK);
		return keyList;
	}

	@Override
	protected List<Object> getValues() {
		List<Object> valueList = new ArrayList<Object>(10);
		valueList.add(this.landingPage);
		valueList.add(this.altText);
		valueList.add(this.caption);
		valueList.add(this.fallbackImage);
		return valueList;
	}
}
