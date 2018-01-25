package com.crud.tasks.mapper;

import com.crud.tasks.domain.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

@RunWith(MockitoJUnitRunner.class)
public class TrelloMapperTest {

    @InjectMocks
    private TrelloMapper trelloMapper;

    @Test
    public void should_MapToBoards(){

        // Given
        List<TrelloListDto> lists = new ArrayList<>();
        lists.add(new TrelloListDto("1", "Test1", true));
        lists.add(new TrelloListDto("2", "Test2", false));
        List<TrelloBoardDto> trelloBoardDto = new ArrayList<>();
        trelloBoardDto.add(new TrelloBoardDto("1", "Test",lists));

        // When
        List<TrelloBoard> result = trelloMapper.mapToBoards(trelloBoardDto);

        // Then
        assertFalse(result.isEmpty());
        assertFalse(result.get(0).getLists().isEmpty());
        assertEquals(1, result.size());
        assertEquals(2, result.get(0).getLists().size());
        assertEquals("Test", result.get(0).getName());
        assertEquals("Test2", result.get(0).getLists().get(1).getName());
        assertEquals(false, result.get(0).getLists().get(1).isClosed());
    }

    @Test
    public void should_MapToBoardsDto(){
        // Given
        List<TrelloList> lists = new ArrayList<>();
        lists.add(new TrelloList("1", "Test1", true));
        lists.add(new TrelloList("2", "Test2", false));
        List<TrelloBoard> trelloBoard = new ArrayList<>();
        trelloBoard.add(new TrelloBoard("1", "Test", lists));

        // When
        List<TrelloBoardDto> result = trelloMapper.mapToBoardsDto(trelloBoard);

        // Then
        assertFalse(result.isEmpty());
        assertFalse(result.get(0).getLists().isEmpty());
        assertEquals(1, result.size());
        assertEquals(2, result.get(0).getLists().size());
        assertEquals("Test", result.get(0).getName());
        assertEquals("Test2", result.get(0).getLists().get(1).getName());
        assertEquals(false, result.get(0).getLists().get(1).isClosed());
    }

    @Test
    public void should_MapToList(){
        // Given
        List<TrelloListDto> lists = new ArrayList<>();
        lists.add(new TrelloListDto("1", "Test1", true));
        lists.add(new TrelloListDto("2", "Test2", false));

        // When
        List<TrelloList> result = trelloMapper.mapToList(lists);

        // Then
        assertFalse(result.isEmpty());
        assertEquals(2, result.size());
        assertEquals("Test1", result.get(0).getName());
        assertEquals(true, result.get(0).isClosed());
        assertEquals("Test2", result.get(1).getName());
        assertEquals(false, result.get(1).isClosed());
    }

    @Test
    public void should_MapToListDto(){
        // Given
        List<TrelloList> trelloLists = new ArrayList<>();
        trelloLists.add(new TrelloList("1", "Test1", true));
        trelloLists.add(new TrelloList("2", "Test2", false));

        // When
        List<TrelloListDto> result = trelloMapper.mapToListDto(trelloLists);

        // Then
        assertFalse(result.isEmpty());
        assertEquals(2, result.size());
        assertEquals("Test1", result.get(0).getName());
        assertEquals(true, result.get(0).isClosed());
        assertEquals("Test2", result.get(1).getName());
        assertEquals(false, result.get(1).isClosed());
    }

    @Test
    public void should_MapToCardDto() {

        // Given
        TrelloCard trelloCard = new TrelloCard("Test",
                "Test description",
                "top",
                "test_id");

        // When
        TrelloCardDto trelloCardDto = trelloMapper.mapToCardDto(trelloCard);

        // Then
        assertEquals("Test", trelloCardDto.getName());
        assertEquals("Test description", trelloCardDto.getDescription());
        assertEquals("top", trelloCardDto.getPos());
        assertEquals("test_id", trelloCardDto.getListId());
    }

    @Test
    public void shouldMapToCard(){
        // Given
        TrelloCardDto trelloCardDto = new TrelloCardDto("Test",
                "Test description",
                "top",
                "test_id");

        // When
        TrelloCard trelloCard = trelloMapper.mapToCard(trelloCardDto);

        // Then
        assertEquals("Test", trelloCard.getName());
        assertEquals("Test description", trelloCard.getDescription());
        assertEquals("top", trelloCard.getPos());
        assertEquals("test_id", trelloCard.getListId());
    }
}