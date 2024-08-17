package com.ieltsSupporter.IELTS.Supporter.mapper;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;
import com.ieltsSupporter.IELTS.Supporter.model.Listening;

@Mapper(componentModel = "spring")
@Component
public interface ListeningMapper {

    Listening toListening(Listening newListening);
}
