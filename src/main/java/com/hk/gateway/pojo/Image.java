package com.hk.gateway.pojo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * @author Rimal
 */
@NamedQueries({
		@NamedQuery(name = "getImageById", query = "select img from Image img where img.id = :imageId and img.hidden = false and img.published =true"),
		@NamedQuery(name = "getImageByChecksum", query = "select img from Image img where img.checksum = :checksum and img.hidden = false and img.published =true") })
@Entity
@Table(name = "image")
public class Image implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;

	@Column(name = "store_id", nullable = false)
	private Long storeId;

	@Column(name = "amazon_image_id")
	private String amazonImageId;

	@Column(name = "checksum", nullable = false, length = 80)
	private String checksum;

	@Column(name = "width")
	private Long width;

	@Column(name = "height")
	private Long height;

	@Column(name = "alt_text", nullable = false, length = 100)
	private String altText;

	@Column(name = "hidden", nullable = false)
	private boolean hidden;

	@Column(name = "published", nullable = false)
	private boolean published;

	@Column(name = "landing_page", length = 1000)
	private String landingPage;

	@Column(name = "caption", length = 250)
	private String caption;

	@Column(name = "image_type", nullable = false)
	private Long type;

	@Column(name = "format", nullable = false)
	private Long imageFormat;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "create_dt", length = 19)
	private Date createDt = new Date();

	@Column(name = "o_img_avl", nullable = false)
	private boolean oImgAvl;

	public Long getId() {
		return this.id;
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

	public String getChecksum() {
		return this.checksum;
	}

	public void setChecksum(String checksum) {
		this.checksum = checksum;
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
		return this.altText;
	}

	public void setAltText(String altText) {
		this.altText = altText;
	}

	public boolean isHidden() {
		return this.hidden;
	}

	public void setHidden(boolean hidden) {
		this.hidden = hidden;
	}

	public boolean isPublished() {
		return this.published;
	}

	public void setPublished(boolean published) {
		this.published = published;
	}

	public String getLandingPage() {
		return this.landingPage;
	}

	public void setLandingPage(String landingPage) {
		this.landingPage = landingPage;
	}

	public String getCaption() {
		return this.caption;
	}

	public void setCaption(String caption) {
		this.caption = caption;
	}

	public Long getType() {
		return this.type;
	}

	public void setType(Long type) {
		this.type = type;
	}

	public Date getCreateDt() {
		return createDt;
	}

	public void setCreateDt(Date createDt) {
		this.createDt = createDt;
	}

	public Long getImageFormat() {
		return imageFormat;
	}

	public void setImageFormat(Long imageFormat) {
		this.imageFormat = imageFormat;
	}

	public boolean isoImgAvl() {
		return oImgAvl;
	}

	public void setoImgAvl(boolean oImgAvl) {
		this.oImgAvl = oImgAvl;
	}

	@Override
	public String toString() {
		return id != null ? id.toString() : "";
	}
}