package com.ieltsSupporter.IELTS.Supporter.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ListeningCreationRequest {
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
