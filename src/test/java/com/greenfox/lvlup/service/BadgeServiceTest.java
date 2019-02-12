package com.greenfox.lvlup.service;

import com.greenfox.lvlup.controller.AdminController;
import com.greenfox.lvlup.model.entity.Badge;
import com.greenfox.lvlup.repositrory.BadgeRepository;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
//@WebMvcTest(AdminController.class)
public class BadgeServiceTest {
    @Mock
    private BadgeRepository badgeRepository;

    @Mock
    private ModelMapper modelMapper;

    @Mock
    private UserService userService;

    Badge validBadge = new Badge("2.3", "Test badge", "general");

    @BeforeEach
    void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void convertBadgeToBadgeDTO() {
    }

    @Test
    public void convertBadgeDTOToBadge() {
    }

    @Test
    public void findBadgeByNameAndVersion() {
        when(badgeRepository.findBadgeByNameAndVersion(anyString(), anyString())).thenReturn(null);
        Badge validBadge = new Badge("2.3", "Test badge", "general");
        when(badgeRepository.findBadgeByNameAndVersion(anyString(), anyString())).thenReturn(java.util.Optional.of(validBadge));

        /*
        public Badge findBadgeByNameAndVersion(Badge badge) {
            Badge badgeExisting = badgeRepository.findBadgeByNameAndVersion(badge.getName(), badge.getVersion()).orElse(null);
            return badgeExisting;

            //
        List<Todo> foundItems = new ArrayList<>();
        Todo mocktodo2 = new Todo();
        mocktodo2.setId(1L);
        mocktodo2.setName("Testobject");
        mocktodo2.setDescription("Mock description");
        mocktodo2.setDone(true);
        mocktodo2.setUrgent(false);

        Todo mocktodo = new Todo();
        mocktodo.setId(4L);
        mocktodo.setName("Testobject 4 ");
        mocktodo.setDescription("Mock description 2");
        mocktodo.setDone(true);
        mocktodo.setUrgent(false);

        foundItems.add(mocktodo);
        foundItems.add(mocktodo2);

        when(todoRepository.findAllByNameContainingOrDescriptionContaining(anyString(),anyString())).thenReturn(foundItems);
*/
        // innen folytatni!!
        List<Todo> resultFromService = todoService.findByNameOrDescription(anyString());

        assertNotNull(resultFromService);
        assertEquals(foundItems.get(1).getName(), resultFromService.get(1).getName());
        assertTrue(foundItems.size() == resultFromService.size());
    }

    }

    @Test
    public void createBadge() {
    }
}