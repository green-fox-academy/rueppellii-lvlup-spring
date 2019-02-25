package com.greenfox.lvlup.repositrory;

import com.greenfox.lvlup.model.entity.Badge;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class BadgeRepositoryTest {
    @Autowired
    private BadgeRepository badgeRepository;

    @Autowired
    private TestEntityManager entityManager;

    private Badge validBadge1;
    private Badge validBadge2;
    private Badge validBadge3;
    private List<Badge> badges = new ArrayList<>();

    @Before
    public void setup() {
        validBadge1 = new Badge("2.3", "Test badge", "general");
        validBadge2 = new Badge("1.3", "Feedback giver", "mentor");
        validBadge3 = new Badge("1.3", "Feedback receiver", "general");
        badges.add(validBadge1);
        badges.add(validBadge2);
        badges.add(validBadge3);
        entityManager.persist(new Badge("2.3", "Test badge", "general"));
        entityManager.persist(new Badge("1.3", "Feedback giver", "mentor"));
        entityManager.persist(new Badge("1.3", "Feedback receiver", "general"));
    }

    @Test
    public void whenFindBadgeByNameAndVersion_thenReturnBadge() {
        Badge foundBadge = badgeRepository.findBadgeByNameAndVersion("Test badge", "2.3");
        assertEquals(validBadge1.getVersion(), foundBadge.getVersion());
    }

    @Test
    public void whenFindBadgeByNameAndVersionNotPresent_thenReturnNull() {
        Badge notPresentBadge = badgeRepository.findBadgeByNameAndVersion("Test badge", "2.4");
        assertNull(notPresentBadge);
    }

    @Test
    public void whenFindAll_thenReturnListOfBadges() {
        List<Badge> foundBadges = badgeRepository.findAll();
        assertEquals(badges.size(), foundBadges.size());
        assertEquals(badges.get(0).getName(), foundBadges.get(0).getName());
        assertEquals(badges.get(1).getVersion(), foundBadges.get(1).getVersion());
        assertEquals(badges.get(2).getTag(), foundBadges.get(2).getTag());
        assertFalse(foundBadges.isEmpty());
    }
}
