package dev.nils.spring.ai.service;

import dev.nils.spring.ai.dto.AddAccountRequest;
import dev.nils.spring.ai.dto.AddedAccountResponse;
import dev.nils.spring.ai.dto.GetAccountsResponse;
import dev.nils.spring.ai.entity.Account;
import dev.nils.spring.ai.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AIDataProvider {

    private final AccountRepository accountRepository;

    public GetAccountsResponse getAllAccounts() {
        return new GetAccountsResponse(accountRepository.findAll());
    }

    public AddedAccountResponse addAccount(AddAccountRequest request) {
        Account account = new Account();
        account.setName(request.name());
        account.setSurname(request.surname());

        account = accountRepository.save(account);
        return new AddedAccountResponse(account);
    }
}
