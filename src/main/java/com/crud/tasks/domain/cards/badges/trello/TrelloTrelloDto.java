package com.crud.tasks.domain.cards.badges.trello;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class TrelloTrelloDto {

    @JsonProperty("board")
    private int board;

    @JsonProperty("card")
    private int card;



}
