package com.pippo.ppiyong.domain.message;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "latest_info")
public class LatestInfo {

    @Id
    private Long id;

    @Column(name = "latest_id")
    private String latestId;
}
