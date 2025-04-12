package machugi.online.example.machugi.service;

import lombok.RequiredArgsConstructor;
import machugi.online.example.machugi.dto.MemberDTO;
import machugi.online.example.machugi.entity.MemberEntity;
import machugi.online.example.machugi.repository.MemberRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {
    public final MemberRepository memberRepository;
    public void save(MemberDTO memberDTO){
        // dto-> entity 변환
        MemberEntity memberEntity = MemberEntity.toMemberEntity(memberDTO);

        // repository의 save 메서드 호출
        memberRepository.save(memberEntity);
    }

    public MemberDTO login(MemberDTO memberDTO) {
        Optional<MemberEntity> byMemberEmail = memberRepository.findByMemberEmail(memberDTO.getMemberEmail());
        if (byMemberEmail.isPresent()) {
            MemberEntity memberEntity = byMemberEmail.get();
            if (memberEntity.getMemberPassword().equals(memberDTO.getMemberPassword())) {
                return MemberDTO.toMemberDTO(memberEntity);
            }
        }
        return null;  // 이메일이 없거나 비밀번호가 틀리면 null 반환
    }

    public String emailCheck(String memberEmail) {
        Optional<MemberEntity> byMemberEmail = memberRepository.findByMemberEmail(memberEmail);
        if (byMemberEmail.isPresent()) {
            // 조회결과가 있다 -> 사용할 수 없다.
            return null;
        } else {
            // 조회결과가 없다 -> 사용할 수 있다.
            return "ok";
        }
    }


}

