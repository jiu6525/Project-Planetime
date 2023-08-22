package search.planetime.config.oauth;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import search.planetime.config.CustomBCryptPasswordEncoder;
import search.planetime.config.auth.PrincipalDetails;
import search.planetime.config.auth.provider.*;
import search.planetime.domain.Member;
import search.planetime.repository.MemberRepository;

import java.util.Map;
import java.util.Optional;

@Service
public class PrincipalOauth2UserService extends DefaultOAuth2UserService {

    @Autowired
    private CustomBCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private MemberRepository memberRepository;

    // 구글로 부터 받은 userRequest 데이터에 대한 후처리되는 함수
    // 함수 종료시 @AuthenticationPrincipal 어노테이션이 만들어진다.
    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        System.out.println("getClientRegistration : " + userRequest.getClientRegistration()); //registrationId로 어떤 OAuth로 로그인했는지 확인
        System.out.println("getAccessToken : " + userRequest.getAccessToken());

        OAuth2User oauth2User = super.loadUser(userRequest);
        // 구글 로그인 버튼 클릭 -> 구글 로그인 창 -> 로그인을 완료 -> code 를 리턴(OAuth-Client 라이브러리) -> AccessToken 요청
        // userRequest 정보 -> loadUser 함수 호출 -> 구글로부터 회원 프로필 받아준다.
        System.out.println("getAttributes : " + oauth2User.getAttributes());

        OAuth2UserInfo oAuth2UserInfo = null;
        OAuth2UserInfo oAuth2UserInfoKakao = null;
        OAuth2UserInfo oAuth2UserInfoKakaoName = null;
        // 회원가입을 강제로 진행할 예정
        if(userRequest.getClientRegistration().getRegistrationId().equals("google")){
            System.out.println("구글 로그인 요청");
            oAuth2UserInfo = new GoogleUserInfo(oauth2User.getAttributes());
        }else if(userRequest.getClientRegistration().getRegistrationId().equals("naver")){
            System.out.println("네이버 로그인 요청");
            oAuth2UserInfo = new NaverUserInfo((Map)oauth2User.getAttributes().get("response"));
        }else if(userRequest.getClientRegistration().getRegistrationId().equals("kakao")){
            System.out.println("카카오 로그인 요청");
            oAuth2UserInfo = new KakaoUserInfo((Map)oauth2User.getAttributes().get("kakao_account"));
            oAuth2UserInfoKakao = new KakaoUserInfo(oauth2User.getAttributes());
            oAuth2UserInfoKakaoName = new KakaoUserInfo((Map)oauth2User.getAttributes().get("properties"));
        } else{
            System.out.println("우리는 구글,네이버, 카카오 만 지원합니다.");
        }
        String provider, providerId, memberId, memberPwd, name, email, memberType = "";

        provider = oAuth2UserInfo.getProvider();// google
        if(oAuth2UserInfoKakao == null){
            providerId = oAuth2UserInfo.getProviderId();
            name = oAuth2UserInfo.getName();

        }else{
            providerId = oAuth2UserInfoKakao.getProviderId();
            name = oAuth2UserInfoKakaoName.getName();

        }
        memberId = provider + "_" + providerId;
        memberPwd = bCryptPasswordEncoder.encode("planetime");
        email = oAuth2UserInfo.getEmail();
        memberType = "ROLE_USER";



        Member memberEntity = memberRepository.findByMemberId(memberId);

        System.out.println(memberEntity);

        if (memberEntity == null) {
            System.out.println("OAuth 로그인이 최초입니다.");
            memberEntity = (Member.builder()
                    .memberId(memberId)
                    .memberPwd(memberPwd)
                    .email(email)
                    .name(name)
                    .memberType(memberType)
                    .provider(provider)
                    .providerId(providerId)
                    .build());

            memberRepository.save(memberEntity);
        }else{
            System.out.println("로그인을 이미 한적이 있습니다. 당신은 자동회원가입이 되어 있습니다. ");
        }

        return new PrincipalDetails(memberEntity, oauth2User.getAttributes());
    }
}
