package dev.nils.spring.ai.dto;

import dev.nils.spring.ai.entity.Account;

import java.util.List;

public record GetAccountsResponse(List<Account> accounts) {
}