package com.crud.tasks.trello.validator;

import com.crud.tasks.domain.TrelloBoard;
import com.crud.tasks.domain.TrelloList;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import java.util.ArrayList;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class TrelloValidatorTest {

    @InjectMocks
    private TrelloValidator trelloValidator;

    @Test
    public void whenValidateTrelloBoardsShouldFilteredTrelloBoardWithNameTest() {

        //Given
        TrelloList trelloList = new TrelloList("1", "TEST", true);
        List<TrelloList> trelloLists = new ArrayList<>();
        trelloLists.add(trelloList);
        TrelloBoard trelloBoard1 = new TrelloBoard("1","tEsT",trelloLists);
        TrelloBoard trelloBoard2 = new TrelloBoard("2","AnotherName",trelloLists);
        List<TrelloBoard> trelloBoards = new ArrayList<>();
        trelloBoards.add(trelloBoard1);
        trelloBoards.add(trelloBoard2);

        //When
        List<TrelloBoard> filteredBoards = trelloValidator.validateTrelloBoards(trelloBoards);

        //Then
        assertThat(filteredBoards.size()).isEqualTo(1);
    }
}
