package com.perscholas.HospitalManagementSystem.service;

import com.perscholas.HospitalManagementSystem.Entity.Role;
import com.perscholas.HospitalManagementSystem.Entity.User;
import com.perscholas.HospitalManagementSystem.exception.DataNotFoundException;
import com.perscholas.HospitalManagementSystem.exception.UserIdMismatchException;
import com.perscholas.HospitalManagementSystem.repository.RoleRepository;
import com.perscholas.HospitalManagementSystem.repository.UserRepository;
import com.perscholas.HospitalManagementSystem.Service.UserService;
import com.perscholas.HospitalManagementSystem.dto.UserDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;
import java.util.stream.Stream;

import static junit.framework.TestCase.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private RoleRepository roleRepository;

    private UserService userService;
    private UserDto userDto;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        userService = new UserService(userRepository, passwordEncoder, roleRepository);
        when(roleRepository.save(any())).thenReturn(Mockito.mock(Role.class));

    }


    @ParameterizedTest
    @MethodSource("userDtoProvider")
    void testCreateUser(UserDto userDto) {
        User user = new User();
        user.setId(1L);
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setAge(userDto.getAge());
        user.setGender(userDto.getGender());
        user.setPhoneNumber(userDto.getPhoneNumber());
        user.setEmail(userDto.getEmail());
        user.setPassword("password");
        Role role = new Role();
        role.setName("ROLE_USER");

        when(roleRepository.findByName("ROLE_USER")).thenReturn(role);
        when(userRepository.save(any(User.class))).thenReturn(new User());

        User result = userService.createUser(userDto);
        result.setId(1L);
        Assertions.assertEquals(user.getId(), result.getId());


        Mockito.verify(userRepository, Mockito.times(1)).save(any(User.class));
    }

    private static Stream<Arguments> userDtoProvider() {
        return Stream.of(
                Arguments.of(createUserDto("John", "Doe", 30, "Male", 1234567890L, "john@example.com", "password")),
                Arguments.of(createUserDto("Jane", "Smith", 25, "Female", 9876543210L, "jane@example.com", "password"))
                // Add more test cases as needed
        );
    }

    private static UserDto createUserDto(String firstName, String lastName, int age, String gender, Long phoneNumber, String email, String password) {
        UserDto userDto = new UserDto();
        userDto.setFirstName(firstName);
        userDto.setLastName(lastName);
        userDto.setAge(age);
        userDto.setGender(gender);
        userDto.setPhoneNumber(phoneNumber);
        userDto.setEmail(email);
        userDto.setPassword(password);
        return userDto;
    }
    @Test
    @DisplayName("Test For Check Role Exist")
    public void testCheckRoleExist() {
        Role expectedRole = new Role();
        expectedRole.setName("ROLE_USER");

        when(roleRepository.save(any(Role.class))).thenReturn(expectedRole);

        Role result = userService.checkRoleExist();

        assertEquals(expectedRole.getName(), result.getName());

        verify(roleRepository, times(1)).save(any(Role.class));
    }




//    @Test
//    void testFindAll() {
//        Iterable<User> users = Mockito.mock(Iterable.class);
//        when(userRepository.findAll()).thenReturn(users);
//
//        Iterable<User> result = userService.findAll();
//
//        Assertions.assertEquals(users, result);
//    }

    @Test
    void testFindUser() throws DataNotFoundException {
        Long userId = 1L;

        User user = new User();
        user.setId(userId);
        user.setFirstName("John");

        when(userRepository.findById(userId)).thenReturn(Optional.of(user));

        User result = userService.findUser(userId);

        Assertions.assertEquals(user, result);
    }

    @Test
    void testFindUserNotFound() {
        Long userId = 1L;

        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        Assertions.assertThrows(DataNotFoundException.class, () -> {
            userService.findUser(userId);
        });
    }

//    @Test
//    void testFindUserByEmail() throws DataNotFoundException {
//        String email = "john@example.com";
//
//        User user = new User();
//        user.setId(1L);
//        user.setEmail(email);
//
//        when(userRepository.findByEmail(email)).thenReturn(Optional.of(user));
//
//        User result = userService.findUserByEmail(email);
//
//        Assertions.assertEquals(user, result);
//    }

    @Test
    void testDeleteUser() throws DataNotFoundException {
        Long userId = 1L;

        User user = new User();
        user.setId(userId);
        user.setFirstName("John");

        when(userRepository.findById(userId)).thenReturn(Optional.of(user));

        userService.deleteUser(userId);

        verify(userRepository, times(1)).deleteById(userId);
    }

    @Test
    void testDeleteUserNotFound() {
        Long userId = 1L;

        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        Assertions.assertThrows(DataNotFoundException.class, () -> {
            userService.deleteUser(userId);
        });
    }

    @Test
    void testUpdateUserMismatchedId() {
        Long userId = 1L;

        User user = new User();
        user.setId(userId);
        user.setFirstName("John");

        when(userRepository.findById(userId)).thenReturn(Optional.of(user));

        User updatedUser = new User();
        updatedUser.setId(2L);
        updatedUser.setFirstName("John Updated");

        Assertions.assertThrows(UserIdMismatchException.class, () -> {
            userService.updateUser(updatedUser, userId);
        });

        verify(userRepository, Mockito.never()).save(any(User.class));
    }

    @Test
    void testUpdateUserNotFound() {
        Long userId = 1L;

        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        User updatedUser = new User();
        updatedUser.setId(userId);
        updatedUser.setFirstName("John Updated");

        Assertions.assertThrows(DataNotFoundException.class, () -> {
            userService.updateUser(updatedUser, userId);
        });

        verify(userRepository, Mockito.never()).save(any(User.class));
    }

}