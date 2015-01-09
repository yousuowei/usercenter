package org.yousuowei.usercenter.imp.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.yousuowei.base.imp.entity.BaseEntity;

@Entity
@Table(name = "tb_role")
public class RoleEntity extends BaseEntity {

	/**
	 * @fieldName: serialVersionUID
	 * @fieldType: long
	 * @Description: TODO
	 */

	private static final long serialVersionUID = 4238612468156001604L;

	private String roleName;
	private List<PermissionEntity> permissionList;// 一个角色对应多个权限
	private List<UserEntity> userList;// 一个角色对应多个用户

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	@OneToMany(mappedBy = "role")
	public List<PermissionEntity> getPermissionList() {
		return permissionList;
	}

	public void setPermissionList(List<PermissionEntity> permissionList) {
		this.permissionList = permissionList;
	}

	@ManyToMany
	@JoinTable(name = "t_user_role", joinColumns = { @JoinColumn(name = "role_id") }, inverseJoinColumns = { @JoinColumn(name = "user_id") })
	public List<UserEntity> getUserList() {
		return userList;
	}

	public void setUserList(List<UserEntity> userList) {
		this.userList = userList;
	}

	@Transient
	public List<String> getPermissionsName() {
		List<String> list = new ArrayList<String>();
		List<PermissionEntity> perlist = getPermissionList();
		for (PermissionEntity per : perlist) {
			list.add(per.getPermissionName());
		}
		return list;
	}

}
