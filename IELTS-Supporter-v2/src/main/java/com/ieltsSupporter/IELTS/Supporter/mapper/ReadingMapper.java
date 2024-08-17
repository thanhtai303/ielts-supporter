package com.ieltsSupporter.IELTS.Supporter.mapper;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import com.ieltsSupporter.IELTS.Supporter.model.Reading;

@Mapper(componentModel = "spring")
@Component
public interface ReadingMapper {
    Reading toReading(Reading newReading);
}
