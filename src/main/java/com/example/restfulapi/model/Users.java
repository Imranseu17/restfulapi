package com.example.restfulapi.model;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "users")
public class Users {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "username")
    private String userName;

    @Column(name = "password")
    private String password;

    @Column(name = "is_stakeholder_user")
    private boolean isStakeholderUser;

    @Column(name = "stakeholder_id")
    private  int stakeholderID;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "created_time")
    private Date createdTime;

    @Column(name = "updated_by")
    private String updatedBy;

    @Column(name = "updated_time")
    private Date updatedTime;

    @Column(name = "status")
    private int status;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "user_role",  joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Roles> rolesSet;

    public Users() {
    }

    public Users(Users users){

        this.id = users.getId();
        this.name = users.getName();
        this.userName = users.getUserName();
        this.password = users.getPassword();
        this.isStakeholderUser = users.isStakeholderUser();
        this.stakeholderID = users.getStakeholderID();
        this.createdBy = users.getCreatedBy();
        this.createdTime = users.getCreatedTime();
        this.updatedBy = users.getUpdatedBy();
        this.updatedTime = users.getUpdatedTime();
        this.status = users.getStatus();
        this.rolesSet = users.getRolesSet();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserName() {
        return userName;
    }

    public void setUsername(String username) {
        this.userName = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isStakeholderUser() {
        return isStakeholderUser;
    }

    public void setStakeholderUser(boolean stakeholderUser) {
        isStakeholderUser = stakeholderUser;
    }

    public int getStakeholderID() {
        return stakeholderID;
    }

    public void setStakeholderID(int stakeholderID) {
        this.stakeholderID = stakeholderID;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public Date getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(Date updatedTime) {
        this.updatedTime = updatedTime;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Set<Roles> getRolesSet() {
        return rolesSet;
    }

    public void setRolesSet(Set<Roles> rolesSet) {
        this.rolesSet = rolesSet;
    }

    @Override
    public String toString() {
        return "Users{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", isStakeholderUser=" + isStakeholderUser +
                ", stakeholderID=" + stakeholderID +
                ", createdBy='" + createdBy + '\'' +
                ", createdTime=" + createdTime +
                ", updatedBy='" + updatedBy + '\'' +
                ", updatedTime=" + updatedTime +
                ", status=" + status +
                ", rolesSet=" + rolesSet +
                '}';
    }
}
