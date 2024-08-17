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
@Table(name = "lis_ans")
public class ListeningAnswer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int lis_ans_id;
    int lis_id;
    String lis_answer_1;
    String lis_answer_2;
    String lis_answer_3;
    String lis_answer_4;
    String lis_answer_5;
    String lis_answer_6;
    String lis_answer_7;
    String lis_answer_8;
    String lis_answer_9;
    String lis_answer_10;
    String lis_answer_11;
    String lis_answer_12;
    String lis_answer_13;
    String lis_answer_14;
    String lis_answer_15;
    String lis_answer_16;
    String lis_answer_17;
    String lis_answer_18;
    String lis_answer_19;
    String lis_answer_20;
    String lis_answer_21;
    String lis_answer_22;
    String lis_answer_23;
    String lis_answer_24;
    String lis_answer_25;
    String lis_answer_26;
    String lis_answer_27;
    String lis_answer_28;
    String lis_answer_29;
    String lis_answer_30;
    String lis_answer_31;
    String lis_answer_32;
    String lis_answer_33;
    String lis_answer_34;
    String lis_answer_35;
    String lis_answer_36;
    String lis_answer_37;
    String lis_answer_38;
    String lis_answer_39;
    String lis_answer_40;

    public String[] allAnswer() {
        String[] answers = { lis_answer_1, lis_answer_2, lis_answer_3,
                lis_answer_4, lis_answer_5, lis_answer_6, lis_answer_7, lis_answer_8,
                lis_answer_9, lis_answer_10, lis_answer_11, lis_answer_12, lis_answer_13,
                lis_answer_14, lis_answer_15, lis_answer_16, lis_answer_17,
                lis_answer_18, lis_answer_19, lis_answer_20, lis_answer_21,
                lis_answer_22, lis_answer_23, lis_answer_24, lis_answer_25,
                lis_answer_26, lis_answer_27, lis_answer_28, lis_answer_29,
                lis_answer_30, lis_answer_31, lis_answer_32, lis_answer_33,
                lis_answer_34, lis_answer_35, lis_answer_36, lis_answer_37,
                lis_answer_38, lis_answer_39, lis_answer_40 };
        return answers;
    }
}
