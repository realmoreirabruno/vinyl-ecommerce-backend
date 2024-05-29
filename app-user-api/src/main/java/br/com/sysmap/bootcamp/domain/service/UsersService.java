package br.com.sysmap.bootcamp.domain.service;

import br.com.sysmap.bootcamp.domain.entities.Users;
import br.com.sysmap.bootcamp.domain.entities.Wallet;
import br.com.sysmap.bootcamp.domain.exceptions.InvalidCredentialsException;
import br.com.sysmap.bootcamp.domain.exceptions.InvalidPasswordException;
import br.com.sysmap.bootcamp.domain.exceptions.NotFoundException;
import br.com.sysmap.bootcamp.domain.exceptions.UserAlreadyExistsException;
import br.com.sysmap.bootcamp.domain.repository.UsersRepository;
import br.com.sysmap.bootcamp.domain.repository.WalletRepository;
import br.com.sysmap.bootcamp.dto.AuthDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Slf4j
@Service
public class UsersService implements UserDetailsService {

    private final UsersRepository usersRepository;
    private final WalletRepository walletRepository;
    private final PasswordEncoder passwordEncoder;

    private void loginValidation (Users user) {

        if (user.getName().isBlank() || user.getName().isEmpty()) {
            throw new InvalidCredentialsException("Name required");
        }

        if (user.getEmail().isBlank() || user.getEmail().isEmpty()) {
            throw new InvalidCredentialsException("Email required");
        }

        if (user.getPassword().isBlank() || user.getPassword().isEmpty()) {
            throw new InvalidCredentialsException("Password required");
        }
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public Users save(Users user) {

        Optional<Users> usersOptional = this.usersRepository.findByEmail(user.getEmail());
        if (usersOptional.isPresent()) {
            throw new UserAlreadyExistsException("User already exists");
        }

        user = user.toBuilder().password(this.passwordEncoder.encode(user.getPassword())).build();

        Wallet wallet = Wallet.builder()
                .users(user)
                .lastUpdate(LocalDateTime.now())
                .points(0L)
                .balance(new BigDecimal(999))              
                .build();

        this.walletRepository.save(wallet);

        log.info("Creating user: {}", user);
        return this.usersRepository.save(user);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public List<Users> findAll() {
        return this.usersRepository.findAll();
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public Users getUserById(Long id) {
        return usersRepository.findById(id).orElseThrow(() ->
            new NotFoundException("User not found"));
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public Users update(Users user) {
        Optional<Users> tempUser = usersRepository.findByEmail(user.getEmail());
        
        Users updatingProcessUser = tempUser.get();
        String password = updatingProcessUser.getPassword();

        if (passwordEncoder.matches(user.getPassword(), password)) {
            user.setPassword(password);
        } else {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }

        updatingProcessUser.setEmail(user.getEmail());
        updatingProcessUser.setName(user.getName());
        updatingProcessUser.setPassword(user.getPassword());

        log.info("Updating user: {}", user);
        return this.usersRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Users> usersOptional = this.usersRepository.findByEmail(username);

        return usersOptional.map(users -> new User(users.getEmail(), users.getPassword(), new ArrayList<GrantedAuthority>()))
                .orElseThrow(() -> new UsernameNotFoundException("User not found" + username));
    }

    public Users findByEmail(String email) {
        return this.usersRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found"));
    }

    public AuthDto auth(AuthDto authDto) {
        Users users = this.findByEmail(authDto.getEmail());

        if (!this.passwordEncoder.matches(authDto.getPassword(), users.getPassword())) {
            throw new InvalidPasswordException("Invalid password");
        }

        StringBuilder password = new StringBuilder().append(users.getEmail()).append(":").append(users.getPassword());

        return AuthDto.builder().email(users.getEmail()).token(
                Base64.getEncoder().withoutPadding().encodeToString(password.toString().getBytes())
        ).id(users.getId()).build();
    }
}