package com.hk.gateway.pojo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Created by abdulnaseer on 2/23/15.
 */
@Entity
@Table(name = "agent_has_user")
@NamedQueries({
		@NamedQuery(name = "findUsersByAgentUserIdAndType", query = "select ahu.userId from AgentHasUser ahu where ahu.agentId = :agentId and ahu.type = :agentType and ahu.active = true"),
		@NamedQuery(name = "findUsersByAgentUserId", query = "select ahu.userId from AgentHasUser ahu where ahu.agentId = :agentId and ahu.active = true"),
		@NamedQuery(name = "findActiveAgentUserMappingByUserIdAndType", query = "from AgentHasUser ahu where ahu.userId = :userId and ahu.type = :agentType and ahu.active = true "),
		@NamedQuery(name = "findActiveAgentUserMappingByUserId", query = "from AgentHasUser ahu where ahu.userId = :userId and ahu.active = true ") })
public class AgentHasUser implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", nullable = false)
	private int id;

	@Column(name = "agent_id", nullable = false)
	private Long agentId;

	@Column(name = "user_id", nullable = false)
	private Long userId;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "create_dt", nullable = false, length = 19)
	private Date createDate = new Date();

	@Column(name = "type", nullable = false)
	private Long type;

	@Column(name = "active", nullable = false)
	private boolean active = true;

	public Long getAgentId() {
		return agentId;
	}

	public void setAgentId(Long agentId) {
		this.agentId = agentId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Long getType() {
		return type;
	}

	public void setType(Long type) {
		this.type = type;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
}
