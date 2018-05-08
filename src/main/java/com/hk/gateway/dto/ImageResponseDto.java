package com.hk.gateway.dto;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.hk.gateway.constant.DtoJsonConstants;
import com.hk.gateway.constant.ImageSizeEnum;
import com.hk.gateway.response.JSONObject;
import com.hk.gateway.util.HKImageUtil;

/**
 * Created by Ronak on 7/6/2015.
 */
public class ImageResponseDto extends JSONObject {

	@Autowired
	private HKImageUtil hkImageUtil;
	protected Long imageId;
	protected String imgLink;
	protected String webUrl;
	protected String landingPageParams;
	protected int width;
	protected int height;
	protected String altText;
	protected String caption;
	protected String menuId;

	public ImageResponseDto(ImageDTO imageDTO) {
		this.setImgLink(hkImageUtil.getS3ImageUrl(imageDTO.getStoreId(), imageDTO.getImageFormat(),
				ImageSizeEnum.Original, null, imageDTO.getAmazonImageId()));
		this.setWidth(imageDTO.getWidth().intValue());
		this.setHeight(imageDTO.getHeight().intValue());
		this.setLandingPageParams(imageDTO.getLandingPage());
		this.setAltText(imageDTO.getAltText());
		this.setCaption(imageDTO.getCaption());
		this.setImageId(imageDTO.getId());
	}

	public ImageResponseDto() {
	}

	public Long getImageId() {
		return imageId;
	}

	public void setImageId(Long imageId) {
		this.imageId = imageId;
	}

	public String getImgLink() {
		return imgLink;
	}

	public void setImgLink(String imgLink) {
		this.imgLink = imgLink;
	}

	public String getWebUrl() {
		return webUrl;
	}

	public void setWebUrl(String webUrl) {
		this.webUrl = webUrl;
	}

	public String getLandingPageParams() {
		return landingPageParams;
	}

	public void setLandingPageParams(String landingPageParams) {
		this.landingPageParams = landingPageParams;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public String getAltText() {
		return altText;
	}

	public void setAltText(String altText) {
		this.altText = altText;
	}

	public String getCaption() {
		return caption;
	}

	public void setCaption(String caption) {
		this.caption = caption;
	}

	public String getMenuId() {
		return menuId;
	}

	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}

	@Override
	protected List<String> getKeys() {
		List<String> keyList = new ArrayList<String>();
		keyList.add(DtoJsonConstants.IMAGE_URL);
		keyList.add(DtoJsonConstants.IMAGE_ID);
		keyList.add(DtoJsonConstants.URL);
		keyList.add(DtoJsonConstants.LANDING_PAGE);
		keyList.add(DtoJsonConstants.WIDTH);
		keyList.add(DtoJsonConstants.HEIGHT);
		keyList.add(DtoJsonConstants.ALT_TEXT);
		keyList.add(DtoJsonConstants.CAPTION);
		keyList.add(DtoJsonConstants.MENUID);
		return keyList;
	}

	@Override
	protected List<Object> getValues() {
		List<Object> values = new ArrayList<Object>();
		values.add(imgLink);
		values.add(imageId);
		values.add(webUrl);
		values.add(landingPageParams);
		values.add(width);
		values.add(height);
		values.add(altText);
		values.add(caption);
		values.add(menuId);
		return values;
	}
}
