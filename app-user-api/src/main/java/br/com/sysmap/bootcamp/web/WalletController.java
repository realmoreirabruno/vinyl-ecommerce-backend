package br.com.sysmap.bootcamp.web;

import br.com.sysmap.bootcamp.domain.service.WalletService;
import br.com.sysmap.bootcamp.domain.entities.Wallet;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import java.math.BigDecimal;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@CrossOrigin(origins = "*")
@RequiredArgsConstructor
@RestController
@RequestMapping("/wallet")
public class WalletController {

    private final WalletService walletService;

    @Operation(summary = "Wallet balance", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Wallet found"),
    })
    @Parameter(description = "Value of adding credits", name = "value", example = "400", required = true)
    @GetMapping("/")
    public ResponseEntity<Wallet> getWalletFromUser() {
        return ResponseEntity.ok(this.walletService.getWallet());
    }

    @Operation(summary = "Add credits to a wallet", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Credits added"),
    })
    @PostMapping("/credit/{value}")
    public ResponseEntity<Wallet> creditWalletValue(@PathVariable("value") BigDecimal value) {
        return ResponseEntity.ok(this.walletService.credit(value));
    }

}