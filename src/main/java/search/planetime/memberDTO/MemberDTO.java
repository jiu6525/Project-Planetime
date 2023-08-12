package search.planetime.memberDTO;

import lombok.*;
import search.planetime.domain.Gender;
import javax.validation.constraints.NotEmpty;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemberDTO {

    private String memberId;
    private String memberPwd;
    private String name;
    private String birth;
    private Gender gender;
    private String email;
    private String phone;
    private String memberType;


}
