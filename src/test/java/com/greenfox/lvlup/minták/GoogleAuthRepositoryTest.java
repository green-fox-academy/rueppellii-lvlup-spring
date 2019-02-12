/*
package com.greenfox.lvlup.minták;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Kalendaryo2Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Transactional
public class GoogleAuthRepositoryTest {

  @Autowired
  private GoogleAuthRepository googleAuthRepo;

  private GoogleAuth googleAuth;

  @Before
  public void setup() {
    googleAuth = new GoogleAuth("Baudelaire");
  }

  @Test
  public void givenGoogleAuth_whenSave_thenGetOk() {
    googleAuthRepo.save(googleAuth);
    GoogleAuth googleAuthFound = googleAuthRepo.findByName("Baudelaire");

    assertEquals("Baudelaire", googleAuthFound.getName());
    assertEquals(googleAuth.getId(), googleAuthFound.getId());
  }*/
