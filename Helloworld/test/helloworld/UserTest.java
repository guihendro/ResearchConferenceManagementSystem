/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package helloworld;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author guihendro
 */
public class UserTest {
    private User u;

    @Before
    public void setUp() {
        this.u = new User("A1", "A", "Author");
    }

    @After
    public void tearDown() {
        u = null;
    }

    /**
     * Test of validateLogin method, of class User.
     */
    @Test
    public void testValidateLogin() {
        boolean result = u.validateLogin(u.getUsername(), u.getPassword());
        assertTrue(result);
    }

}
