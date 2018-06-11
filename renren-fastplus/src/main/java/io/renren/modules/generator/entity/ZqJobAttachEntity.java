package io.renren.modules.generator.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

/**
 * ${comments}
 * 
 * @author huqin
 * @email qin.hu@newtouch.cn
 * @date 2018-06-08 08:51:32
 */
@TableName("ZQ_JOB_ATTACH")
public class ZqJobAttachEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * $column.comments
	 */
	@TableId
	private Long objectId;
	/**
	 * $column.comments
	 */
	private Long jobId;
	/**
	 * $column.comments
	 */
	private String sheetId;
	/**
	 * $column.comments
	 */
	private String userId;
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
	public void setObjectId(Long objectId) {
		this.objectId = objectId;
	}
	/**
	 * 获取：${column.comments}
	 */
	public Long getObjectId() {
		return objectId;
	}
	/**
	 * 设置：${column.comments}
	 */
	public void setJobId(Long jobId) {
		this.jobId = jobId;
	}
	/**
	 * 获取：${column.comments}
	 */
	public Long getJobId() {
		return jobId;
	}
	/**
	 * 设置：${column.comments}
	 */
	public void setSheetId(String sheetId) {
		this.sheetId = sheetId;
	}
	/**
	 * 获取：${column.comments}
	 */
	public String getSheetId() {
		return sheetId;
	}
	/**
	 * 设置：${column.comments}
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}
	/**
	 * 获取：${column.comments}
	 */
	public String getUserId() {
		return userId;
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
