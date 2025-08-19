// 代码生成时间: 2025-08-19 21:39:19
import grails.transaction.Transactional
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.core.authority.SimpleGrantedAuthority

// UserLoginService class responsible for user authentication
@Transactional
class UserLoginService implements UserDetailsService {

    // Dependency injection of PasswordEncoder
    private final PasswordEncoder passwordEncoder
    private final UserRepository userRepository

    UserLoginService(PasswordEncoder passwordEncoder, UserRepository userRepository) {
        this.passwordEncoder = passwordEncoder
        this.userRepository = userRepository
    }

    // Method to load user by username
    @Override
    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            // Find user by username
            def user = userRepository.findByUsername(username)
            if (user) {
                // Check if password is valid
                if (passwordEncoder.matches("userPassword", user.password)) {
                    // Return the user details
                    return new org.springframework.security.core.userdetails.User(
                        user.username, user.password, // User's password will be encoded in the security config
                        [SimpleGrantedAuthority("ROLE_USER")] as Set // Default role
                    )
                } else {
                    // Throw an exception if password does not match
                    throw new SecurityException("Invalid password for username: $username")
                }
            } else {
                // Throw an exception if user is not found
                throw new UsernameNotFoundException("User $username not found")
            }
        } catch (Exception e) {
            // Handle any unexpected exceptions
            throw new RuntimeException("Failed to load user: $username", e)
        }
    }

    // Method to authenticate user
    boolean authenticate(String username, String password) {
        try {
            // Load user by username
            UserDetails userDetails = loadUserByUsername(username)
            // Compare passwords
            return passwordEncoder.matches(password, userDetails.password)
        } catch (UsernameNotFoundException e) {
            // Handle user not found exception
            return false
        } catch (SecurityException e) {
            // Handle security exception
            return false
        } catch (Exception e) {
            // Handle any unexpected exceptions
            return false
        }
    }
}