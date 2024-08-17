package com.ieltsSupporter.IELTS.Supporter.mapper;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;
import com.ieltsSupporter.IELTS.Supporter.model.Writing;

@Mapper(componentModel = "spring")
@Component
public interface WritingMapper {

    Writing toWriting(Writing newWriting);
}
