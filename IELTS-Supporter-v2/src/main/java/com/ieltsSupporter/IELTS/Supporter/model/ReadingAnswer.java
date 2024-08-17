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
@Table(name = "rea_ans")
public class ReadingAnswer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int rea_ans_id;
    int rea_id;
    String rea_answer_1;
    String rea_answer_2;
    String rea_answer_3;
    String rea_answer_4;
    String rea_answer_5;
    String rea_answer_6;
    String rea_answer_7;
    String rea_answer_8;
    String rea_answer_9;
    String rea_answer_10;
    String rea_answer_11;
    String rea_answer_12;
    String rea_answer_13;
    String rea_answer_14;
    String rea_answer_15;
    String rea_answer_16;
    String rea_answer_17;
    String rea_answer_18;
    String rea_answer_19;
    String rea_answer_20;
    String rea_answer_21;
    String rea_answer_22;
    String rea_answer_23;
    String rea_answer_24;
    String rea_answer_25;
    String rea_answer_26;
    String rea_answer_27;
    String rea_answer_28;
    String rea_answer_29;
    String rea_answer_30;
    String rea_answer_31;
    String rea_answer_32;
    String rea_answer_33;
    String rea_answer_34;
    String rea_answer_35;
    String rea_answer_36;
    String rea_answer_37;
    String rea_answer_38;
    String rea_answer_39;
    String rea_answer_40;

    public String[] allAnswer() {
        String[] answers = { rea_answer_1, rea_answer_2, rea_answer_3,
                rea_answer_4, rea_answer_5, rea_answer_6, rea_answer_7, rea_answer_8,
                rea_answer_9, rea_answer_10, rea_answer_11, rea_answer_12, rea_answer_13,
                rea_answer_14, rea_answer_15, rea_answer_16, rea_answer_17,
                rea_answer_18, rea_answer_19, rea_answer_20, rea_answer_21,
                rea_answer_22, rea_answer_23, rea_answer_24, rea_answer_25,
                rea_answer_26, rea_answer_27, rea_answer_28, rea_answer_29,
                rea_answer_30, rea_answer_31, rea_answer_32, rea_answer_33,
                rea_answer_34, rea_answer_35, rea_answer_36, rea_answer_37,
                rea_answer_38, rea_answer_39, rea_answer_40 };
        return answers;
    }
}
