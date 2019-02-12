/*
package com.greenfox.lvlup.minták;RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Kalendaryo2Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CalendarServiceTest {

  @Autowired
  private CalendarService calendarService;

  private List<Calendar> list;
  private Calendar calendar = new Calendar();

  @MockBean
  private CalendarRepository calendarRepository;

  @Before
  public void setup() {
    MockitoAnnotations.initMocks(this);
    list = new ArrayList<>();
    list.add(new Calendar("Holiday"));
    list.add(new Calendar("Work"));
    list.add(new Calendar("Own calendar"));

    calendar.setName("Work");
  }

  @Test
  public void testFindAll() {
    when(calendarRepository.findAll()).thenReturn(list);

    List<Calendar> result = calendarService.getAll();
    verify(calendarRepository, atLeastOnce()).findAll();
    assertNotNull(result);
    assertFalse(result.isEmpty());
  }

  @Test
  public void testFindByName() {
    when(calendarRepository.findByName("Work")).thenReturn(calendar);
    Assert.assertEquals("Work", calendarService.getByName("Work").getName());
  }*/
