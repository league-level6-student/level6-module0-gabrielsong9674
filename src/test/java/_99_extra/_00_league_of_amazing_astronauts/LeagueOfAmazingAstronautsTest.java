package _99_extra._00_league_of_amazing_astronauts;

import _99_extra._00_league_of_amazing_astronauts.LeagueOfAmazingAstronauts;
import _99_extra._00_league_of_amazing_astronauts.models.Astronaut;
import _99_extra._00_league_of_amazing_astronauts.models.Rocketship;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import _10_white_box_testing.models.PaymentService;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

/*

When writing the tests, mock both the Rocketship and Astronaut for the sake of practice.
 */
class LeagueOfAmazingAstronautsTest {

    LeagueOfAmazingAstronauts underTest = new LeagueOfAmazingAstronauts();
    
    @Mock
    Rocketship rocketship;
    
    @Mock
    Astronaut astronaut;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        underTest.setRocketship(rocketship);

    }

    @Test
    void itShouldPrepareAstronaut() {
        //given
    	doNothing().when(astronaut).train();
    	doNothing().when(rocketship).loadOccupant(astronaut);
    	//when
    	underTest.prepareAstronaut(astronaut);
        //then
    	verify(astronaut, times(1)).train();
        verify(rocketship, times(1)).loadOccupant(astronaut);
        verifyNoMoreInteractions(astronaut);
        verifyNoMoreInteractions(rocketship);
        

    }

    @Test
    void itShouldLaunchRocket() {
        //given (assumptions)
    	String destination = "Mars";
    	int miles = 68000000;
    	when(rocketship.isLoaded()).thenReturn(true);

        //when
    	underTest.launchRocket(destination);
    	
        //then
    	verify(rocketship, times(1)).setDestination(destination, miles);
    	verify(rocketship, times(1)).isLoaded();
    	verify(rocketship, times(1)).launch();
    	verifyNoMoreInteractions(astronaut);
        verifyNoMoreInteractions(rocketship);
        
    	
    }


    @Test
    void itShouldThrowWhenDestinationIsUnknown() {
        //given
    	String destination = "Jupiter";
    	int miles = 680000000;
    	when(rocketship.isLoaded()).thenReturn(true);
        //when
        //then
    	Throwable exceptionThrown = assertThrows(Exception.class, () -> underTest.launchRocket(destination));
        assertEquals(exceptionThrown.getMessage(), "Destination is unavailable");
    }

    @Test
    void itShouldThrowNotLoaded() {
        //given
    	String destination = "Mars";
    	int miles = 68000000;
        //when
        //then
    	Throwable exceptionThrown = assertThrows(Exception.class, () -> underTest.launchRocket(destination));
        assertEquals(exceptionThrown.getMessage(), "Rocketship is not loaded");

    }
}