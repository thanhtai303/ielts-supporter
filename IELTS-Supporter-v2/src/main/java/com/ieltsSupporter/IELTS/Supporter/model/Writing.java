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
@Table(name = "wri_ques")
public class Writing {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int wri_id;
    String wri_topic;
    String wri_question_1;
    String wri_question_1_url;
    String wri_question_2;
    String wri_question_2_url;
}
