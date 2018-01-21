package com.cmp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(
	name = "WEB_API_SETTING",
	uniqueConstraints = {@UniqueConstraint(columnNames = {"API_SETTING_ID"})}
)
public class WebApiSetting {

	public WebApiSetting() {
		super();
	}

	public WebApiSetting(String apiSettingId, String moduleId, String tableName, String tableField) {
		super();
		this.apiSettingId = apiSettingId;
		this.moduleId = moduleId;
		this.tableName = tableName;
		this.tableField = tableField;
	}

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid")
	@Column(name = "API_SETTING_ID", unique = true)
	private String apiSettingId;
	
	@Column(name = "MODULE_ID", nullable = false)
	private String moduleId;
	
	@Column(name = "TABLE_NAME", nullable = false)
	private String tableName;
	
	@Column(name = "TABLE_FIELD", nullable = false)
	private String tableField;

	public String getApiSettingId() {
		return apiSettingId;
	}

	public void setApiSettingId(String apiSettingId) {
		this.apiSettingId = apiSettingId;
	}

	public String getModuleId() {
		return moduleId;
	}

	public void setModuleId(String moduleId) {
		this.moduleId = moduleId;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getTableField() {
		return tableField;
	}

	public void setTableField(String tableField) {
		this.tableField = tableField;
	}
}
