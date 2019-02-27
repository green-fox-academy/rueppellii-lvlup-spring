package com.greenfox.lvlup.service;

import com.greenfox.lvlup.exception.GeneralException;
import com.greenfox.lvlup.model.dto.library.BadgeDTO;
import com.greenfox.lvlup.model.entity.Badge;
import com.greenfox.lvlup.model.entity.User;
import com.greenfox.lvlup.repositrory.BadgeRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;


@RunWith(MockitoJUnitRunner.class)
public class BadgeServiceTest {
    @InjectMocks
    private BadgeService badgeService;

    @Mock
    private BadgeRepository badgeRepositoryMock;

    @Mock
    private ModelMapper modelMapperMock;

    @Mock
    private UserService userServiceMock;

    User badgeCreator = new User("Test Elek");
    String token = "testtoken";
    String badToken = "notvalidtesttoken";
    Badge validBadge = new Badge("2.3", "Test badge", "general");
    Badge validBadge2 = new Badge("1.4", "English speaker", "general");
    Badge validBadge3 = new Badge("1.5", "Feedback giver", "mentor");
    BadgeDTO testDTO = new BadgeDTO("2.3", "Test badge", "general");


    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void whenConvertBadgeDTOToBadge_thenReturnBadgeEntity() {
        when(this.modelMapperMock.map(testDTO, Badge.class)).thenReturn(validBadge);
        Badge result = badgeService.convertBadgeDTOToBadge(testDTO);

        assertEquals(result.getName(), validBadge.getName());
        assertEquals(result.getVersion(), validBadge.getVersion());
        assertEquals(result.getTag(), validBadge.getTag());
        assertEquals(result.getDateOfCreation(), validBadge.getDateOfCreation());
        assertEquals(result.getUser(), validBadge.getUser());
    }

    @Test
    public void whenFindBadgeByNameAndVersionExistingBadgeVersion_thenReturnExistingBadgeVersion() {
        when(badgeRepositoryMock.findBadgeByNameAndVersion(anyString(), anyString())).thenReturn(validBadge);
        Badge testResult = badgeService.findBadgeByNameAndVersion(validBadge);

        assertNotNull(testResult);
        assertEquals(validBadge.getVersion(), testResult.getVersion());
        assertEquals(validBadge.getName(), testResult.getName());
    }

    @Test
    public void whenFindBadgeByNameAndVersionWithNotExistingBadgeVersion_thenReturnNull() {
        when(badgeRepositoryMock.findBadgeByNameAndVersion(anyString(), anyString())).thenReturn(null);
        Badge emptyBadge = badgeService.findBadgeByNameAndVersion(validBadge);

        assertNull(emptyBadge);
    }

    @Test
    public void saveBadgeIntoDatabase() throws Exception {
        when(badgeService.findBadgeByNameAndVersion(validBadge2)).thenReturn(null);
        System.out.println(validBadge2.getUser());
        badgeService.saveBadgeIntoDatabase(validBadge2, badgeCreator);

        assertEquals("Test Elek", validBadge2.getUser().getName());
        assertNotNull(validBadge2.getUser());
    }

    @Test(expected = GeneralException.class)
    public void saveBadgeIntoDatabase_whenBadgeNotNull_thenThrowsException() throws Exception {
        when(badgeService.findBadgeByNameAndVersion(validBadge2)).thenReturn(validBadge2);
        badgeService.saveBadgeIntoDatabase(validBadge2, badgeCreator);
    }

    @Test
    public void whenCreateBadge_thenReturnTrue() throws GeneralException {
        when(badgeService.convertBadgeDTOToBadge(testDTO)).thenReturn(validBadge);
        when(userServiceMock.findUserByTokenAuth(token)).thenReturn(badgeCreator);

        assertTrue(badgeService.createBadge(testDTO, token));
    }

    @Test
    public void whenCreateBadgeWithNotExistingUser_thenReturnFalse() throws GeneralException {
       when(userServiceMock.findUserByTokenAuth(badToken)).thenReturn(null);

        assertFalse(badgeService.createBadge(testDTO, badToken));
    }
}

