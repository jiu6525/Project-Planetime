package search.planetime.domain;

import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Entity
@Table(name = "member")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Member {

    @Id
    @GeneratedValue
    @Column(name = "member_no")
    private Long no;
    @Column(nullable = false, unique = true)
    private String memberId;
    @Setter
    private String memberPwd;
    private String name;
    private String birth;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    private String email;
    private String phone;
    private String signDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
    private String memberType;
    private String provider;
    private String providerId;

//    @PrePersist
//    public void prePersist(){
//        this.memberType = this.memberType == null ? "user" : this.memberType;
//    }

    @Builder
    public Member(String memberId, String memberPwd, String name, String birth, Gender gender,
                  String email, String phone, String memberType, String provider, String providerId){
        this.memberId = memberId;
        this.memberPwd = memberPwd;
        this.name = name;
        this.birth = birth;
        this.gender = gender;
        this.email = email;
        this.phone = phone;
        this.memberType = memberType;
        this.provider = provider;
        this.providerId = providerId;
    }

}
