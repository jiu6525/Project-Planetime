package search.planetime.controller;

import lombok.Getter;
import lombok.Setter;
import search.planetime.domain.Gender;
import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class MemberForm {

    @NotEmpty
    private String memberId;

    @NotEmpty
    private String memberPwd;

    @NotEmpty
    private String name;

    private String birth;

//    private String year;
//    private String month;
//    private String day;

    public String setBirth(String year, String month, String day){
        this.birth = year+month+day;
        return birth;
    }

    private Gender gender;
    private String email;
    private String phone;



}
