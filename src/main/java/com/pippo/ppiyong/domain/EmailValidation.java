package com.pippo.ppiyong.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Getter
@Entity
@Table(name = "EmailValidation")
@AllArgsConstructor
@NoArgsConstructor
public class EmailValidation {
    @Id
    private String email;
    private Date exp;
    private String ePw;
}