# testing advanced

## profile config

TODO setup profiles for test and dev env

    application.yml
    application-dev.yml
    application-test.yml
    ApplicationProperties

## persistence config

TODO setup persistence config for mysql

    PersistenceJpaConfig

TODO setup persistence config for tests using h2

    H2TestProfileJpaConfig

## spring rest CRUD

    Paper
    PaperRepository
    
    PaperRequest
    PaperResponse
    PaperMapper

    PaperService
    
    PaperController

# testing

## dto unit test

TODO unit test the dto

    PaperMapperTest

## service test

TODO unit test for the service layer

    PaperServiceTest
        @ExtendWith(MockitoExtension.class)
        @Mock
        @InjectMocks

TODO integration test for the service layer

    PaperServiceIntegrationTest
        @SpringBootTest

## controller test

TODO unit test for controller layer

    PaperControllerTest

TODO integration test for controller layer using spring mock mvc

    ControllerMockMvcIntegrationTest
        @ActiveProfiles
        @ExtendWith(SpringExtension.class)
        @AutoConfigureMockMvc
        @SpringBootTest

TODO integration test for controller layer using rest template

    ControllerRestTemplateIntegrationTest
        @ActiveProfiles
        @ExtendWith(SpringExtension.class)
        @SpringBootTest

TODO integration test for controller layer using rest assured

    ControllerRestAssuredIntegrationTest
        @ActiveProfiles("test")
        @ExtendWith(SpringExtension.class)
        @SpringBootTest
