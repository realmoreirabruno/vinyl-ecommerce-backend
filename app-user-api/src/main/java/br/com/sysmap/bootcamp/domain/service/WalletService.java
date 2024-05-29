package br.com.sysmap.bootcamp.domain.service;

import br.com.sysmap.bootcamp.domain.entities.Users;
import br.com.sysmap.bootcamp.domain.entities.Wallet;
import br.com.sysmap.bootcamp.domain.points.PointsPerWeekDay;
import br.com.sysmap.bootcamp.domain.repository.WalletRepository;
import br.com.sysmap.bootcamp.dto.WalletDto;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class WalletService {

    private final UsersService usersService;
    private final WalletRepository walletRepository;

    public void debit(WalletDto walletDto) {
        Users users = usersService.findByEmail(walletDto.getEmail());
        Wallet wallet = walletRepository.findByUsers(users).orElseThrow();
        wallet.setBalance(wallet.getBalance().subtract(walletDto.getValue()));

        LocalDate today = LocalDate.now();
        LocalDateTime lastUpdate = LocalDateTime.now();
        BigDecimal balance = wallet.getBalance().subtract(walletDto.getValue());

        wallet.setPoints(wallet.getPoints() + PointsPerWeekDay.getPointsPerWeekDay(today.getDayOfWeek()));
        wallet.setLastUpdate(lastUpdate);
        wallet.setBalance(balance);

        walletRepository.save(wallet);
    }

    public Wallet credit(BigDecimal value) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        Users users = usersService.findByEmail(authentication.getName());
        Wallet wallet = walletRepository.findByUsers(users).orElseThrow();
        wallet.setBalance(wallet.getBalance().add(value));

        return walletRepository.save(wallet);
    }

    public Wallet getWallet() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        Users users = usersService.findByEmail(authentication.getName());

        return this.walletRepository.findByUsers(users).orElseThrow();
    }
}