package com.crud.tasks.domain;

import com.crud.tasks.domain.cards.TrelloBadgesDto;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class TrelloCardDto {
    private String name;
    private String description;
    private String pos;
    private String listId;
    private TrelloBadgesDto trelloBadgesDto;

}

