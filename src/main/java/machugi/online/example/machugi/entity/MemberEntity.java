package machugi.online.example.machugi.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import machugi.online.example.machugi.dto.MemberDTO;

@Getter
@Setter
@Entity
@Table(name = "member_table")
public class MemberEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 자동으로 id값 생성 및 올림
    private Long id;

    @Column(unique=true) // 중복 금지
    private String memberEmail;

    @Column
    private String memberPassword;

    @Column
    private String memberName;


    public static MemberEntity toMemberEntity(MemberDTO memberDTO) {
        MemberEntity memberEntity = new MemberEntity();
        memberEntity.setMemberEmail(memberDTO.getMemberEmail());
        memberEntity.setMemberPassword(memberDTO.getMemberPassword());
        memberEntity.setMemberName(memberDTO.getMemberName());
        return memberEntity;
    }
}