package com.crud.tasks.domain.cards;

import com.crud.tasks.domain.cards.badges.TrelloAttachmentsByTypeDto;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class TrelloBadgesDto {

    @JsonProperty("votes")
    private int votes;

    @JsonProperty("attachmentsByType")
    private TrelloAttachmentsByTypeDto trelloAttachmentsByTypeDto;
}
