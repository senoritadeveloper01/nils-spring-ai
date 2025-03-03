package dev.nils.spring.ai.config;

import dev.nils.spring.ai.dto.AccountRequest;
import dev.nils.spring.ai.dto.AddAccountRequest;
import dev.nils.spring.ai.dto.AddedAccountResponse;
import dev.nils.spring.ai.dto.GetAccountsResponse;
import dev.nils.spring.ai.service.AIDataProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Description;

import java.util.function.Function;

@Configuration
public class AIFunctionConfiguration {

    // The @Description annotation helps the model understand when to call the function
    @Bean
    @Description("List the existing accounts in the database")
    public Function<AccountRequest, GetAccountsResponse> listAccounts(AIDataProvider aiDataProvider) {
        return request -> aiDataProvider.getAllAccounts();
    }

    @Bean
    @Description("""
			Add a new user to the database. \
			The account must include a name and a surname as two separate words""")
    public Function<AddAccountRequest, AddedAccountResponse> addAccount(AIDataProvider aiDataProvider) {
        return aiDataProvider::addAccount;
    }

}
