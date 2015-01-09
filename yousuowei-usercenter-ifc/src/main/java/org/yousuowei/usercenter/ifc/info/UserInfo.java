package org.yousuowei.usercenter.ifc.info;

public class UserInfo {

	private String userName;
	private String psd;
	private String rePsd;
	private String email;
	private int state;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPsd() {
		return psd;
	}

	public void setPsd(String psd) {
		this.psd = psd;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRePsd() {
		return rePsd;
	}

	public void setRePsd(String rePsd) {
		this.rePsd = rePsd;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

}
