package pfa.ebanking.entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.persistence.JoinColumn;
@Entity 
@Table(name =  "user", uniqueConstraints = @UniqueConstraint(columnNames = "username"))
public class User implements Serializable {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
     
    @Column(nullable = false, unique = true, length = 45)
    private String email;
     
    @Column(nullable = false, length = 64)
    private String password;
     
    @Column(name = "first_name", nullable = false, length = 20)
    private String firstName;
     
    @Column(name = "last_name", nullable = false, length = 20)
    private String lastName;
    
    @Column(nullable = false, length = 45)
    private String adress;
    @Column(nullable = false, length = 45)
	private String city;
    @Column(nullable = false,length = 45)
	private String zip;
    @Column(nullable = false,length = 45)
	private String username;
    @Column(nullable = false, length = 45)
	private String phone;
    @Column(nullable = false, unique = true, length = 45)
	private String cin;
	
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(
			name = "users_roles",
			joinColumns = @JoinColumn(
		            name = "user_id", referencedColumnName = "id"),
			inverseJoinColumns = @JoinColumn(
				            name = "role_id", referencedColumnName = "id"))
	 private Set<Role> roles = new HashSet<>();
	//private Collection<Role> roles;
	
    public void addRole(Role role) {
        this.roles.add(role);
}
	
	public User() {}
	
	/*
	 * public User(String firstName, String lastName, String email, String
	 * password,String adress, String city, String zip, String username, String
	 * phone, String cin, Set<Role> roles roles) { super(); this.firstName =
	 * firstName; this.lastName = lastName; this.email = email; this.password =
	 * password; this.adress = adress; this.city = city; this.zip = zip;
	 * this.username = username; this.phone = phone; this.cin = cin; this.roles =
	 * roles; }
	 */
	
	
private static final long OTP_VALID_DURATION = 5 * 60 * 1000;   // 5 minutes
    
    public User(String email, String password, String firstName, String lastName, String adress, String city,
			String zip, String username, String phone, String cin, Set<Role> roles) {
		super();
		this.email = email;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.adress = adress;
		this.city = city;
		this.zip = zip;
		this.username = username;
		this.phone = phone;
		this.cin = cin;
		this.roles = roles;
	}

	@Column(name = "one_time_password")
    private String oneTimePassword;
     
    @Column(name = "otp_requested_time")
    private Date otpRequestedTime;
     
     
    public boolean isOTPRequired() {
        if (this.getOneTimePassword() == null) {
            return false;
        }
         
        long currentTimeInMillis = System.currentTimeMillis();
        long otpRequestedTimeInMillis = this.otpRequestedTime.getTime();
         
        if (otpRequestedTimeInMillis + OTP_VALID_DURATION < currentTimeInMillis) {
            // OTP expires
            return false;
        }
         
        return true;
    }
    
	public String getOneTimePassword() {
		return oneTimePassword;
	}

	public void setOneTimePassword(String oneTimePassword) {
		this.oneTimePassword = oneTimePassword;
	}

	public Date getOtpRequestedTime() {
		return otpRequestedTime;
	}

	public void setOtpRequestedTime(Date otpRequestedTime) {
		this.otpRequestedTime = otpRequestedTime;
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getAdress() {
		return adress;
	}

	public void setAdress(String adress) {
		this.adress = adress;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getCin() {
		return cin;
	}

	public void setCin(String cin) {
		this.cin = cin;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}


}
