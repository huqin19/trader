package io.renren.modules.generator.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

/**
 * ${comments}
 * 
 * @author huqin
 * @email qin.hu@newtouch.cn
 * @date 2018-06-08 08:51:32
 */
@TableName("ZQ_SHEETS")
public class ZqSheetsEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * $column.comments
	 */
	@TableId
	@JsonSerialize(using = ToStringSerializer.class)
	private BigDecimal objectId;
	/**
	 * $column.comments
	 */
	private BigDecimal parentId;
	/**
	 * $column.comments
	 */
	private String sheetName;
	/**
	 * $column.comments
	 */
	private String sheetUrl;
	/**
	 * $column.comments
	 */
	private BigDecimal status;
	/**
	 * $column.comments
	 */
	private String creatorId;
	/**
	 * $column.comments
	 */
	private Timestamp createdTimestamp;
	/**
	 * $column.comments
	 */
	private String lastModifierId;
	/**
	 * $column.comments
	 */
	private Timestamp modifiedTimestamp;

	/**
	 * 设置：${column.comments}
	 */
	public void setObjectId(BigDecimal objectId) {
		this.objectId = objectId;
	}
	/**
	 * 获取：${column.comments}
	 */
	public BigDecimal getObjectId() {
		return objectId;
	}
	/**
	 * 设置：${column.comments}
	 */
	public void setParentId(BigDecimal parentId) {
		this.parentId = parentId;
	}
	/**
	 * 获取：${column.comments}
	 */
	public BigDecimal getParentId() {
		return parentId;
	}
	/**
	 * 设置：${column.comments}
	 */
	public void setSheetName(String sheetName) {
		this.sheetName = sheetName;
	}
	/**
	 * 获取：${column.comments}
	 */
	public String getSheetName() {
		return sheetName;
	}
	/**
	 * 设置：${column.comments}
	 */
	public void setSheetUrl(String sheetUrl) {
		this.sheetUrl = sheetUrl;
	}
	/**
	 * 获取：${column.comments}
	 */
	public String getSheetUrl() {
		return sheetUrl;
	}
	/**
	 * 设置：${column.comments}
	 */
	public void setStatus(BigDecimal status) {
		this.status = status;
	}
	/**
	 * 获取：${column.comments}
	 */
	public BigDecimal getStatus() {
		return status;
	}
	/**
	 * 设置：${column.comments}
	 */
	public void setCreatorId(String creatorId) {
		this.creatorId = creatorId;
	}
	/**
	 * 获取：${column.comments}
	 */
	public String getCreatorId() {
		return creatorId;
	}
	/**
	 * 设置：${column.comments}
	 */
	public void setCreatedTimestamp(Timestamp createdTimestamp) {
		this.createdTimestamp = createdTimestamp;
	}
	/**
	 * 获取：${column.comments}
	 */
	public Timestamp getCreatedTimestamp() {
		return createdTimestamp;
	}
	/**
	 * 设置：${column.comments}
	 */
	public void setLastModifierId(String lastModifierId) {
		this.lastModifierId = lastModifierId;
	}
	/**
	 * 获取：${column.comments}
	 */
	public String getLastModifierId() {
		return lastModifierId;
	}
	/**
	 * 设置：${column.comments}
	 */
	public void setModifiedTimestamp(Timestamp modifiedTimestamp) {
		this.modifiedTimestamp = modifiedTimestamp;
	}
	/**
	 * 获取：${column.comments}
	 */
	public Timestamp getModifiedTimestamp() {
		return modifiedTimestamp;
	}
}
