package com.ieltsSupporter.IELTS.Supporter.mapper;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;
import com.ieltsSupporter.IELTS.Supporter.model.ReadingAnswer;

@Mapper(componentModel = "spring")
@Component
public interface ReadingAnswerMapper {

    ReadingAnswer toReadingAnswer(ReadingAnswer newReadingAnswer);
}
