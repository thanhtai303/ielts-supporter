package com.ieltsSupporter.IELTS.Supporter.mapper;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import com.ieltsSupporter.IELTS.Supporter.model.Speaking;

@Mapper(componentModel = "spring")
@Component
public interface SpeakingMapper {

    Speaking toSpeaking(Speaking newSpeaking);
}
