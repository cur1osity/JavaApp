package com.crud.tasks.service;


import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.domain.TrelloBoardDto;
import com.crud.tasks.domain.TrelloCardDto;
import com.crud.tasks.domain.TrelloListDto;
import com.crud.tasks.trello.client.CreatedTrelloCardDto;
import com.crud.tasks.trello.client.TrelloClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import java.util.ArrayList;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@RunWith(MockitoJUnitRunner.class)
public class TrelloServiceTest {

    @InjectMocks
    private TrelloService trelloService;

    @Mock
    private TrelloClient trelloClient;

    @Mock
    AdminConfig adminConfig;

    @Mock
    SimpleEmailService simpleEmailService;

    @Test
    public void whenFindingTrelloBoardsShouldReturnAllTrelloBoards() {

        //Given
        List<TrelloListDto> trelloListDtos = new ArrayList<>();
        TrelloListDto trelloListDto = new TrelloListDto("1","TEST",true);
        trelloListDtos.add(trelloListDto);
        TrelloBoardDto trelloBoardDto1 = new TrelloBoardDto("1","TEST1", trelloListDtos);
        TrelloBoardDto trelloBoardDto2 = new TrelloBoardDto("2","TEST2", trelloListDtos);
        List<TrelloBoardDto> trelloBoardDtos = new ArrayList<>();
        trelloBoardDtos.add(trelloBoardDto1);
        trelloBoardDtos.add(trelloBoardDto2);
        given(trelloClient.getTrelloBoards()).willReturn(trelloBoardDtos);

        //When & Then
        assertThat(trelloService.fetchTrelloBoards()).containsOnly(trelloBoardDto1, trelloBoardDto2);
    }

    @Test
    public void whenNewTrelloCardIsCreatedShouldReturnCreatedTrelloCardDto() {

        //Given
        TrelloCardDto trelloCardDto = new TrelloCardDto("TEST","TEST_DESC", "top","1");
        CreatedTrelloCardDto createdTrelloCardDto = new CreatedTrelloCardDto("1","TEST","www.google.com");

        given(trelloClient.createNewCard(trelloCardDto)).willReturn(createdTrelloCardDto);

        //When & Then
        assertThat(trelloService.createdTrelloCardDto(trelloCardDto)).isEqualToComparingFieldByField(createdTrelloCardDto);
    }
}
