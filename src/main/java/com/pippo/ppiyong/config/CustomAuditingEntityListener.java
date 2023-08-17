package com.pippo.ppiyong.config;
import com.pippo.ppiyong.type.BaseTimeEntity;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;


import java.time.LocalDateTime;
import java.time.ZoneId;

public class CustomAuditingEntityListener extends AuditingEntityListener {

    @PrePersist
    public void prePersist(Object target) {
        if (target instanceof BaseTimeEntity) {
            BaseTimeEntity entity = (BaseTimeEntity) target;
            entity.setCreatedAt(LocalDateTime.now(ZoneId.of("Asia/Seoul"))); // Set KST time
            entity.setModifiedAt(LocalDateTime.now(ZoneId.of("Asia/Seoul"))); // Set KST time
        }
    }

    @PreUpdate
    public void preUpdate(Object target) {
        if (target instanceof BaseTimeEntity) {
            BaseTimeEntity entity = (BaseTimeEntity) target;
            entity.setModifiedAt(LocalDateTime.now(ZoneId.of("Asia/Seoul"))); // Set KST time
        }
    }
}

