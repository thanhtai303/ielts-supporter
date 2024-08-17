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
@Table(name = "lis_ques")
public class Listening {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int lis_id;
    String lis_topic;
    String lis_question_1;
    String lis_question_2;
    String lis_question_3;
    String lis_question_4;
    String lis_audio_1;
    String lis_audio_2;
    String lis_audio_3;
    String lis_audio_4;
}
