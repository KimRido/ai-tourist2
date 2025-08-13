package com.example.backend.domain.user.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity //DB테이블과 매핑될 엔티티임을 알려줌
@EntityListeners(AuditingEntityListener.class) //JPA의 **감사 기능(Auditing)**을 활성화함. 생성일, 수정일 같은 필드를 자동으로 관리할 수 있게 해줌
@Table(name = "user_user_entity") //매핑될 테이블 이름이 user_user_entity 임을 알려줌
@Getter
@Builder //빌더패턴 사용을 가능하게 해줌
@NoArgsConstructor //파라미터 없는 생성자 자동생성
@AllArgsConstructor //파라미터 있는 생성자 자동생성
public class UserEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username", unique = true, nullable = false, updatable = false)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "is_lock", nullable = false)
    private Boolean isLock;

    @Column(name = "is_social", nullable = false)
    private Boolean isSocial;

    @Enumerated(EnumType.STRING)
    @Column(name = "social_provider_type")
    private SocialProviderType socialProviderType;

    @Enumerated(EnumType.STRING)
    @Column(name = "role_type", nullable = false)
    private UserRoleType roleType;

    @Column(name = "nickname")
    private String nickname;

    @Column(name = "email")
    private String email;

    @Column(name = "mobile")
    private String mobile;

    @CreatedDate
    @Column(name = "created_date", updatable = false)
    private LocalDateTime createdDate;

    @LastModifiedDate
    @Column(name = "updated_date")
    private LocalDateTime updatedDate;
}
