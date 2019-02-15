package com.greenfox.lvlup.service;

import com.greenfox.lvlup.model.dto.library.BadgeDTO;
import com.greenfox.lvlup.model.entity.Badge;
import com.greenfox.lvlup.model.entity.User;
import com.greenfox.lvlup.repositrory.BadgeRepository;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyCollection;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class BadgeServiceTest {
    @InjectMocks
    private BadgeService badgeService;

    @Mock
    private BadgeRepository badgeRepository;

    @Mock
    private ModelMapper modelMapper;

    @Mock
    private UserService userService;

    Badge validBadge = new Badge("2.3", "Test badge", "general");
    Badge validBadge2 = new Badge("1.4", "English speaker", "general");
    Badge validBadge3 = new Badge("1.5", "Feedback giver", "mentor");
    List<Badge> validBadgeSet = Arrays.asList(validBadge, validBadge2, validBadge3);
    Optional<Badge> empty = Optional.empty();
    BadgeDTO testDTO = new BadgeDTO("2.3", "Test badge", "general");
    String badgeVersion = "2.3";
    String badgeVersionNotExisting = "2.4";
    String badgeName = "Test badge";

    @BeforeEach
    void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void convertBadgeDTOToBadge() {
        Badge result = this.modelMapper.map(testDTO, Badge.class);
        when(this.modelMapper.map(testDTO, Badge.class)).thenReturn(validBadge);
        assertEquals(result.getName(), validBadge.getName());
        assertEquals(result.getVersion(), validBadge.getVersion());
        assertEquals(result.getTag(), validBadge.getTag());
        assertEquals(result.getDateOfCreation(), validBadge.getDateOfCreation());
        assertEquals(result.getUser(), validBadge.getUser());
    }

    @Test
    public void findBadgeByNameAndVersionWithExistingBadgeVersion() {
        when(badgeRepository.findBadgeByNameAndVersion(anyString(), anyString())).thenReturn(Optional.ofNullable(validBadge));
        Optional<Badge> badgeExisting = badgeRepository.findBadgeByNameAndVersion(badgeVersion, badgeName);

        assertNotNull(badgeExisting);
        assertEquals(badgeVersion, badgeExisting.get().getVersion());
        assertEquals(badgeName, badgeExisting.get().getName());
    }

    @Test
    public void findBadgeByNameAndVersionWithNotExistingBadgeVersionReturnsEmptyOptional() {
        when(badgeRepository.findBadgeByNameAndVersion(anyString(), anyString())).thenReturn(empty);
        Optional<Badge> emptyBadge = badgeRepository.findBadgeByNameAndVersion(badgeVersionNotExisting, badgeName);

        assertFalse(emptyBadge.isPresent());
        assertEquals(empty, emptyBadge);
    }

    @Test
    public void createBadge() {

    }
}