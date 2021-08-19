package intl.lotto.laoslottery.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false, unique = true, length = 20)
	private String username;
	@Column(nullable = false, length = 64)
	private String password;
	@Column(nullable = false, length = 30)
	private String firstname;
	@Column(nullable = false, length = 30)
	private String lastname;
}
