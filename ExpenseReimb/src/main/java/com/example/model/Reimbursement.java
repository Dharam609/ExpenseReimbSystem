package com.example.model;

import java.sql.Blob;

public class Reimbursement {
	private int reimbId;
	private double amount;
	private String submittedDate;
	private String resolvedDate;
	private String description;
	private Blob receipt;
	private String applicant;
	private String managerName;
	private String status;
	private String type;

	public Reimbursement() {

	}

	public Reimbursement(int reimbId, double amount, String submittedDate, String resolvedDate, String description,
			String applicant, String managerName, String status, String type) {
		super();
		this.reimbId = reimbId;
		this.amount = amount;
		this.submittedDate = submittedDate;
		this.resolvedDate = resolvedDate;
		this.description = description;
		this.applicant = applicant;
		this.managerName = managerName;
		this.status = status;
		this.type = type;
	}

	public Reimbursement(double amount, String submittedDate, String resolvedDate, String description, String applicant,
			String managerName, String status, String type) {
		super();
		this.amount = amount;
		this.submittedDate = submittedDate;
		this.resolvedDate = resolvedDate;
		this.description = description;
		this.applicant = applicant;
		this.managerName = managerName;
		this.status = status;
		this.type = type;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getSubmittedDate() {
		return submittedDate;
	}

	public void setSubmittedDate(String submittedDate) {
		this.submittedDate = submittedDate;
	}

	public String getResolvedDate() {
		return resolvedDate;
	}

	public void setResolvedDate(String resolvedDate) {
		this.resolvedDate = resolvedDate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Blob getReceipt() {
		return receipt;
	}

	public void setReceipt(Blob receipt) {
		this.receipt = receipt;
	}

	public String getApplicant() {
		return applicant;
	}

	public void setApplicant(String applicant) {
		this.applicant = applicant;
	}

	public String getManagerName() {
		return managerName;
	}

	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getReimbId() {
		return reimbId;
	}

	@Override
	public String toString() {
		return "Reimbursement [reimbId=" + reimbId + ", amount=" + amount + ", submittedDate=" + submittedDate
				+ ", resolvedDate=" + resolvedDate + ", description=" + description + ", receipt=" + receipt
				+ ", applicant=" + applicant + ", managerName=" + managerName + ", status=" + status + ", type=" + type
				+ "]";
	}

}
