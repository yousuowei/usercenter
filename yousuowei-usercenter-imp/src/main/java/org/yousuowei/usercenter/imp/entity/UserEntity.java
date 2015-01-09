package org.yousuowei.usercenter.imp.entity;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Transient;

import org.yousuowei.base.imp.entity.BaseEntity;

@Entity(name = "tb_user")
public class UserEntity extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6491897504636774296L;

	private String userName;
	private String psd;
	private String email;
	private int state;// 用户状态 -1 用户初始状态 （未激活），0用户已激活（邮箱验证通过）
	private Long registTime;
	private List<RoleEntity> roleList;// 一个用户具有多个角色

	@Column(name = "u_name")
	public String getUserName() {
		return userName;
	}

	@Column(name = "u_email", nullable = false, unique = true)
	public String getEmail() {
		return email;
	}

	@Column(name = "u_psd", nullable = false)
	public String getPsd() {
		return psd;
	}

	@Column(name = "u_regist_time", nullable = false)
	public Long getRegistTime() {
		return registTime;
	}

	@ManyToMany
	@JoinTable(name = "tb_user_role", joinColumns = { @JoinColumn(name = "user_id") }, inverseJoinColumns = { @JoinColumn(name = "role_id") })
	public List<RoleEntity> getRoleList() {
		return roleList;
	}

	@Transient
	public Set<String> getRolesName() {
		List<RoleEntity> roles = getRoleList();
		Set<String> set = new HashSet<String>();

		for (RoleEntity role : roles) {
			set.add(role.getRoleName());
		}
		return set;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setPsd(String psd) {
		this.psd = psd;
	}

	public void setRoleList(List<RoleEntity> roleList) {
		this.roleList = roleList;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setRegistTime(Long registTime) {
		this.registTime = registTime;
	}

	@Column(name = "u_state", nullable = false, columnDefinition = "INT default -1")
	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

}
