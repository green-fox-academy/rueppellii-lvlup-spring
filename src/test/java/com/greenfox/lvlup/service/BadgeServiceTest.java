package com.greenfox.lvlup.service;

import com.greenfox.lvlup.exception.GeneralException;
import com.greenfox.lvlup.model.dto.library.BadgeDTO;
import com.greenfox.lvlup.model.entity.Badge;
import com.greenfox.lvlup.model.entity.User;
import com.greenfox.lvlup.repositrory.BadgeRepository;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
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
    List<Badge> validBadgeSet = Arrays.asList(validBadge, validBadge2, validBadge3);
    Optional<Badge> empty = Optional.empty();
    BadgeDTO testDTO = new BadgeDTO("2.3", "Test badge", "general");


    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        //this is done by @InjectMocks annotation
        // badgeService = new BadgeService(badgeRepositoryMock, modelMapperMock,userServiceMock);
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
        validBadge2.setUser(badgeCreator);
        doAnswer(i -> i.getArguments()[0])
                .when(badgeRepositoryMock)
                .save(validBadge2);
        Badge createdBadge = badgeService.saveBadgeIntoDatabase(validBadge2, badgeCreator);
        assertEquals("Test Elek", createdBadge.getUser().getName());
    }

    @Test(expected = GeneralException.class)
    public void saveBadgeIntoDatabase_whenBadgeNotNull_thenThrowsException() throws Exception {
        //when(badgeService.findBadgeByNameAndVersion(validBadge2)).thenReturn(validBadge2);
        // doThrow(new GeneralException()).when(badgeRepositoryMock).findBadgeByNameAndVersion(anyString(), anyString());
        when(badgeService.findBadgeByNameAndVersion(validBadge2)).thenReturn(validBadge2);
        badgeService.saveBadgeIntoDatabase(validBadge2, badgeCreator);
    }

    @Test
    public void whenCreateBadge_thenReturnTrue() throws GeneralException {
        when(badgeService.convertBadgeDTOToBadge(testDTO)).thenReturn(validBadge);
        when(userServiceMock.findUserByTokenAuth(token)).thenReturn(badgeCreator);
        when(badgeService.saveBadgeIntoDatabase(validBadge, badgeCreator)).thenReturn(validBadge);
        assertEquals(true, badgeService.createBadge(testDTO, token));
    }

    @Ignore
    @Test(expected = GeneralException.class)
    public void whenCreateBadge_thenReturnUserNotExists() throws GeneralException {
        when(badgeService.convertBadgeDTOToBadge(testDTO)).thenReturn(validBadge);
        doThrow(new GeneralException()).when(userServiceMock.findUserByTokenAuth(badToken));
        //when(userServiceMock.findUserByTokenAuth(token)).thenReturn(badgeCreator);
        //when(badgeService.saveBadgeIntoDatabase(validBadge, badgeCreator)).thenReturn(validBadge);
        //assertEquals(true, badgeService.createBadge(testDTO, token));
    }

    @Test
    public void whenCreateBadge_thenReturnBadgeVersionExists() throws GeneralException {
        when(badgeService.convertBadgeDTOToBadge(testDTO)).thenReturn(validBadge);
        when(userServiceMock.findUserByTokenAuth(token)).thenReturn(badgeCreator);
        when(badgeService.saveBadgeIntoDatabase(validBadge, badgeCreator)).thenReturn(null);
        //doThrow(new GeneralException()).when(badgeService.saveBadgeIntoDatabase(validBadge, badgeCreator));
        //        //        //when(mock.isOk()).thenThrow(exception);
        boolean isCreated = badgeService.createBadge(testDTO,token);
        //assertFalse(badgeService.createBadge(testDTO,token));
        assertEquals(false, isCreated);
    }
}

