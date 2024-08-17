package com.ieltsSupporter.IELTS.Supporter.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Data
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "rea_ques")
public class Reading {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int rea_id;
    private String rea_topic;
    private String rea_doc_1;
    private String rea_doc_2;
    private String rea_doc_3;
    private String rea_question_1;
    private String rea_question_2;
    private String rea_question_3;

}
