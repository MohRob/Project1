package com.revature.projectOneFinal.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "reimbursement_table")
public class ReimbursementModel {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int reimbursementId;
	private String reason;
	private int amount;
	private String status;
	private int userId;
	public ReimbursementModel() {
		super();
	}
	
	public ReimbursementModel(String reason, int amount, String status) {
		super();
		this.reason = reason;
		this.amount = amount;
		this.status = status;
	}

	public ReimbursementModel(int reimbursementId, String reason, int amount, String status, int userId) {
		super();
		this.reimbursementId = reimbursementId;
		this.reason = reason;
		this.amount = amount;
		this.status = status;
		this.userId = userId;
	}
	public int getReimbursementId() {
		return reimbursementId;
	}
	public void setReimbursementId(int reimbursementId) {
		this.reimbursementId = reimbursementId;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	@Override
	public String toString() {
		return "ReimbursementModel [reimbursementId=" + reimbursementId + ", reason=" + reason + ", amount=" + amount
				+ ", status=" + status + ", userId=" + userId + "]";
	}

}
