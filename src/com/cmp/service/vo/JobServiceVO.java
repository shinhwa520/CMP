package com.cmp.service.vo;

public class JobServiceVO {

	private String scheduleName;
	private String triggerName;
	private String triggerGroup;
	private String description;
	private String preFireTime;
	private String nextFireTime;
	private String priority;
	private String triggerState;
	private String triggerType;
	private String startTime;
	private String endTime;
	private String misFireInstr;
	private String cronExpression;
	private String timeZoneId;
	
	public String getScheduleName() {
		return scheduleName;
	}
	public void setScheduleName(String scheduleName) {
		this.scheduleName = scheduleName;
	}
	public String getTriggerName() {
		return triggerName;
	}
	public void setTriggerName(String triggerName) {
		this.triggerName = triggerName;
	}
	public String getTriggerGroup() {
		return triggerGroup;
	}
	public void setTriggerGroup(String triggerGroup) {
		this.triggerGroup = triggerGroup;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getPreFireTime() {
		return preFireTime;
	}
	public void setPreFireTime(String preFireTime) {
		this.preFireTime = preFireTime;
	}
	public String getNextFireTime() {
		return nextFireTime;
	}
	public void setNextFireTime(String nextFireTime) {
		this.nextFireTime = nextFireTime;
	}
	public String getPriority() {
		return priority;
	}
	public void setPriority(String priority) {
		this.priority = priority;
	}
	public String getTriggerState() {
		return triggerState;
	}
	public void setTriggerState(String triggerState) {
		this.triggerState = triggerState;
	}
	public String getTriggerType() {
		return triggerType;
	}
	public void setTriggerType(String triggerType) {
		this.triggerType = triggerType;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public String getMisFireInstr() {
		return misFireInstr;
	}
	public void setMisFireInstr(String misFireInstr) {
		this.misFireInstr = misFireInstr;
	}
	public String getCronExpression() {
		return cronExpression;
	}
	public void setCronExpression(String cronExpression) {
		this.cronExpression = cronExpression;
	}
	public String getTimeZoneId() {
		return timeZoneId;
	}
	public void setTimeZoneId(String timeZoneId) {
		this.timeZoneId = timeZoneId;
	}
}
