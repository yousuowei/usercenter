package org.yousuowei.usercenter.imp.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.yousuowei.base.imp.entity.BaseEntity;

@Entity
@Table(name = "t_permission")
public class PermissionEntity extends BaseEntity {

	/**
	 * @fieldName: serialVersionUID
	 * @fieldType: long
	 * @Description: TODO
	 */

	private static final long serialVersionUID = 7662680609512640571L;

	private String permissionName;
	private RoleEntity role;// 一个权限对应一个角色

	public String getPermissionName() {
		return permissionName;
	}

	public void setPermissionName(String permissionName) {
		this.permissionName = permissionName;
	}

	@ManyToOne
	@JoinColumn(name = "role_id")
	public RoleEntity getRole() {
		return role;
	}

	public void setRole(RoleEntity role) {
		this.role = role;
	}

}
