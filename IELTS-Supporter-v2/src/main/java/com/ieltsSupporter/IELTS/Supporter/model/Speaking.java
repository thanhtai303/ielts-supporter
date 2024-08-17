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
@Table(name = "spe_ques")
public class Speaking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int spe_id;
    String spe_topic;
    String spe_question_1_1;
    String spe_question_1_2;
    String spe_question_1_3;
    String spe_question_1_4;
    String spe_question_2;
    String spe_question_2_suggest_1;
    String spe_question_2_suggest_2;
    String spe_question_2_suggest_3;
    String spe_question_2_suggest_4;
    String spe_question_3_1;
    String spe_question_3_2;
    String spe_question_3_3;

}
