package com.ieltsSupporter.IELTS.Supporter.mapper;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;
import com.ieltsSupporter.IELTS.Supporter.model.ListeningAnswer;

@Mapper(componentModel = "spring")
@Component
public interface ListeningAnswerMapper {

    ListeningAnswer toListeningAnswer(ListeningAnswer newListeningAnswer);
}
