package search.planetime.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
public class Member {

    @Id @GeneratedValue
    private int MEMBER_NO;
    private String MEMBER_ID;
    private String MEMBER_PWD;
    private String NAME;
    private String GENDER;
    private String EMAIL;
    private String Phone;
    private String SIGN_DATE;
    private int MEMBER_TYPE;


}
