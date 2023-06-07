package mk.ukim.finki.wp.lab;

import mk.ukim.finki.wp.lab.model.Role;
import mk.ukim.finki.wp.lab.model.User;
import mk.ukim.finki.wp.lab.model.exceptions.InvalidUserCredentialsException;
import mk.ukim.finki.wp.lab.model.exceptions.PasswordsDoNotMatchException;
import mk.ukim.finki.wp.lab.model.exceptions.UsernameAlreadyExistsException;
import mk.ukim.finki.wp.lab.repository.jpa.UserRepositoryJPA;
import mk.ukim.finki.wp.lab.service.impl.UserServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
public class UserRegistrationTest {

    @Mock
    private UserRepositoryJPA userRepositoryJPA;

    @Mock
    private PasswordEncoder passwordEncoder;
    private UserServiceImpl service;

    //private UserService service;


    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
        User user = new User("username", "name", "surname", "password", LocalDate.now(), Role.ROLE_USER);
        Mockito.when(this.userRepositoryJPA.save(Mockito.any(User.class))).thenReturn(user);
        Mockito.when(this.passwordEncoder.encode(Mockito.anyString())).thenReturn("password");

        this.service = Mockito.spy(new UserServiceImpl(this.userRepositoryJPA, this.passwordEncoder));
        //so celiot ovaj kod nie go inicijalizirame testot koj treba da go pisime
    }

    @Test
    public void testSuccessRegister(){
        User user = this.service.register("username", "name", "surname", "password", "password", LocalDate.now(), Role.ROLE_USER);
        //so linijata gore nie go povikuvame korisnikot koj e logiran
        Mockito.verify(this.service).register("username", "name", "surname", "password", "password", LocalDate.now(), Role.ROLE_USER);
        //so ova verificirame odnosno proveruvame dali metodot e povikan so soodvetnite argumenti
        Assert.assertNotNull("User is null", user);
        Assert.assertEquals("name do not match", "name", user.getUserFullname().getName());
        Assert.assertEquals("username do not match", "username", user.getUsername());
        Assert.assertEquals("password do not match", "password", user.getPassword());
        Assert.assertEquals("surname do not match", "surname", user.getUserFullname().getSurname());
        Assert.assertEquals("role do not match", Role.ROLE_USER, user.getRole());
        //proverka dali korisnikot e uspesno registriran (prvo kazuvame koja e porakata ako user e null)
        // so assertEquals proveruvame deka imeto na korisnikot e name odnosno toa sho go prakjame
    }

    @Test
    public void testNullUsername() {
        Assert.assertThrows("InvalidArgumentsException expected",
                InvalidUserCredentialsException.class,
                () -> this.service.register(null, "name", "surname", "password", "password", LocalDate.now(), Role.ROLE_USER));
        Mockito.verify(this.service).register(null, "name", "surname", "password", "password", LocalDate.now(), Role.ROLE_USER);
    }

    @Test
    public void testEmptyUsername() {
        String username = "";
        Assert.assertThrows("InvalidArgumentsException expected",
                InvalidUserCredentialsException.class,
                () -> this.service.register(username, "name", "surname", "password", "password", LocalDate.now(), Role.ROLE_USER));
        Mockito.verify(this.service).register(username, "name", "surname", "password", "password", LocalDate.now(), Role.ROLE_USER);
    }


    @Test
    public void testEmptyPassword() {
        String username = "username";
        String password = "";
        Assert.assertThrows("InvalidArgumentsException expected",
                InvalidUserCredentialsException.class,
                () -> this.service.register(username, "name", "surname", password, "password", LocalDate.now(), Role.ROLE_USER));
        Mockito.verify(this.service).register(username, "name", "surname", password, "password", LocalDate.now(), Role.ROLE_USER);
    }

    @Test
    public void testNullPassword() {
        String username = "username";
        String password = null;
        Assert.assertThrows("InvalidArgumentsException expected",
                InvalidUserCredentialsException.class,
                () -> this.service.register(username, "name", "surname", password, "password", LocalDate.now(), Role.ROLE_USER));
        Mockito.verify(this.service).register(username, "name", "surname", password, "password", LocalDate.now(), Role.ROLE_USER);
    }

    @Test
    public void testPasswordMismatch() {
        String username = "username";
        String password = "password";
        String confirmPassword = "otherPassword";
        Assert.assertThrows("PasswordsDoNotMatchException expected",
                PasswordsDoNotMatchException.class,
                () -> this.service.register(username, "name", "surname", password, confirmPassword, LocalDate.now(), Role.ROLE_USER));
        Mockito.verify(this.service).register(username, "name", "surname", password, confirmPassword, LocalDate.now(), Role.ROLE_USER);
    }

    @Test
    public void testDuplicateUsername() {
        User user = new User("username", "name", "surname", "password", LocalDate.now(), Role.ROLE_USER);
        Mockito.when(this.userRepositoryJPA.findByUsername(Mockito.anyString())).thenReturn(Optional.of(user));
        String username = "username";
        Assert.assertThrows("UsernameAlreadyExistsException expected",
                UsernameAlreadyExistsException.class,
                () -> this.service.register(username, "name", "surname", "password", "password", LocalDate.now(), Role.ROLE_USER));
        Mockito.verify(this.service).register(username, "name", "surname", "password", "password", LocalDate.now(), Role.ROLE_USER);
    }
}
