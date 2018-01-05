package com.crud.tasks.domain.cards.badges;

import com.crud.tasks.domain.cards.badges.trello.TrelloTrelloDto;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class TrelloAttachmentsByTypeDto {

    @JsonProperty("trello")
    TrelloTrelloDto trelloTrelloDto;
}
