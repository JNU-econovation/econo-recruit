//package com.econovation.recruit;
//import com.econovation.recruit.domain.board.Board;
//import com.econovation.recruit.domain.board.BoardRepository;
//import com.econovation.recruit.domain.board.Navigation;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.junit.runner.RunWith;
//import org.mockito.Mock;
//import org.mockito.junit.MockitoJUnitRunner;
//
//import java.util.ArrayList;
//import java.util.Collections;
//import java.util.List;
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertTrue;
//import static org.mockito.Mockito.*;
//
////@ExtendWith(MockitoJUnitRunner.class)
//@RunWith(MockitoJUnitRunner.class)
//class BoardRepositoryTest {
//
//    @Mock
//    private BoardRepository boardRepository;
//
//    @Test
//    public void testGetByHopeField() {
//        // Arrange
//        Navigation navigation = new Navigation();
//        navigation.setId(1L);
//
//        Board board1 = Board.builder()
//                .navigation(navigation)
//                .colLoc(1)
//                .colTitle("Test Title 1")
//                .lowLoc(10)
//                .id(1L)
//                .build();
//
//        Board board2 = Board.builder()
//                .navigation(navigation)
//                .colLoc(2)
//                .colTitle("Test Title 2")
//                .lowLoc(20)
//                .id(2L)
//                .build();
//
//        List<Board> boards = new ArrayList<>();
//        boards.add(board1);
//        boards.add(board2);
//
//        when(boardRepository.getByHopeField(eq("Test Title 1"))).thenReturn(Optional.of(Collections.singletonList(board1)));
//        when(boardRepository.getByHopeField(eq("Test Title 2"))).thenReturn(Optional.of(Collections.singletonList(board2)));
//
//        // Act
//        Optional<List<Board>> result1 = Optional.ofNullable(boardRepository.getByHopeField("Test Title 1"));
//        Optional<List<Board>> result2 = Optional.ofNullable(boardRepository.getByHopeField("Test Title 2"));
//
//        // Assert
//        assertTrue(result1.isPresent());
//        assertEquals(1, result1.get().size());
//        assertEquals(board1.getId(), result1.get().get(0).getId());
//
//        assertTrue(result2.isPresent());
//        assertEquals(1, result2.get().size());
//        assertEquals(board2.getId(), result2.get().get(0).getId());
//
//        verify(boardRepository, times(2)).getByHopeField(anyString());
//    }
//}