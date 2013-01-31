package com.aripd.account.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

import com.aripd.account.domain.Account;
import com.aripd.account.dto.AccountDto;
import com.aripd.account.exception.AccountNotFoundException;
import com.aripd.account.repository.AccountRepository;
import com.aripd.account.util.AccountTestUtil;

import static junit.framework.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class AccountServiceImplTest {

	private static final Long ID = Long.valueOf(5);
    private static final String FIRST_NAME = "Foo";
    private static final String FIRST_NAME_UPDATED = "FooUpdated";
    private static final String LAST_NAME = "Bar";
    private static final String LAST_NAME_UPDATED = "BarUpdated";
	private static final String EMAIL = "foo@domain.tld";
	private static final String EMAIL_UPDATED = "foo_updated@domain.tld";
	private static final String USERNAME = "foo";
	private static final String USERNAME_UPDATED = "foo_updated";
	private static final String PASSWORD = "foopass";
	private static final String PASSWORD_UPDATED = "foopass_updated";
	private static final String DATE_OF_BIRTH = "1978-02-14";
	private static final String DATE_OF_BIRTH_UPDATED = "1982-03-05";
    
    private AccountServiceImpl service;

    private AccountRepository repositoryMock;

    @Before
    public void setUp() {
        service = new AccountServiceImpl();

        repositoryMock = mock(AccountRepository.class);
        service.setAccountRepository(repositoryMock);
    }
    
    @Test
    public void create() {
        AccountDto created = AccountTestUtil.createDTO(null, FIRST_NAME, LAST_NAME, EMAIL, USERNAME, PASSWORD, DATE_OF_BIRTH);
        Account persisted = AccountTestUtil.createModelObject(ID, FIRST_NAME, LAST_NAME, EMAIL, USERNAME, PASSWORD, DATE_OF_BIRTH);
        
        when(repositoryMock.save(any(Account.class))).thenReturn(persisted);
        
        Account returned = service.create(created);

        ArgumentCaptor<Account> personArgument = ArgumentCaptor.forClass(Account.class);
        verify(repositoryMock, times(1)).save(personArgument.capture());
        verifyNoMoreInteractions(repositoryMock);

        assertAccount(created, personArgument.getValue());
        assertEquals(persisted, returned);
    }
    
    @Test
    public void delete() throws AccountNotFoundException {
        Account deleted = AccountTestUtil.createModelObject(ID, FIRST_NAME, LAST_NAME, EMAIL, USERNAME, PASSWORD, DATE_OF_BIRTH);
        when(repositoryMock.findOne(ID)).thenReturn(deleted);
        
        Account returned = service.delete(ID);
        
        verify(repositoryMock, times(1)).findOne(ID);
        verify(repositoryMock, times(1)).delete(deleted);
        verifyNoMoreInteractions(repositoryMock);
        
        assertEquals(deleted, returned);
    }
    
    @Test(expected = AccountNotFoundException.class)
    public void deleteWhenAccountIsNotFound() throws AccountNotFoundException {
        when(repositoryMock.findOne(ID)).thenReturn(null);
        
        service.delete(ID);
        
        verify(repositoryMock, times(1)).findOne(ID);
        verifyNoMoreInteractions(repositoryMock);
    }
    
    @Test
    public void findAll() {
        List<Account> persons = new ArrayList<Account>();
        when(repositoryMock.findAll()).thenReturn(persons);
        
        List<Account> returned = service.findAll();
        
        verify(repositoryMock, times(1)).findAll();
        verifyNoMoreInteractions(repositoryMock);
        
        assertEquals(persons, returned);
    }
    
    @Test
    public void findById() {
        Account person = AccountTestUtil.createModelObject(ID, FIRST_NAME, LAST_NAME, EMAIL, USERNAME, PASSWORD, DATE_OF_BIRTH);
        when(repositoryMock.findOne(ID)).thenReturn(person);
        
        Account returned = service.findById(ID);
        
        verify(repositoryMock, times(1)).findOne(ID);
        verifyNoMoreInteractions(repositoryMock);
        
        assertEquals(person, returned);
    }
    
    @Test
    public void update() throws AccountNotFoundException {
        AccountDto updated = AccountTestUtil.createDTO(ID, FIRST_NAME_UPDATED, LAST_NAME_UPDATED, EMAIL_UPDATED, USERNAME_UPDATED, PASSWORD_UPDATED, DATE_OF_BIRTH_UPDATED);
        Account person = AccountTestUtil.createModelObject(ID, FIRST_NAME, LAST_NAME, EMAIL, USERNAME, PASSWORD, DATE_OF_BIRTH);
        
        when(repositoryMock.findOne(updated.getId())).thenReturn(person);
        
        Account returned = service.update(updated);
        
        verify(repositoryMock, times(1)).findOne(updated.getId());
        verifyNoMoreInteractions(repositoryMock);
        
        assertAccount(updated, returned);
    }
    
    @Test(expected = AccountNotFoundException.class)
    public void updateWhenAccountIsNotFound() throws AccountNotFoundException {
        AccountDto updated = AccountTestUtil.createDTO(ID, FIRST_NAME_UPDATED, LAST_NAME_UPDATED, EMAIL_UPDATED, USERNAME_UPDATED, PASSWORD_UPDATED, DATE_OF_BIRTH_UPDATED);
        
        when(repositoryMock.findOne(updated.getId())).thenReturn(null);

        service.update(updated);

        verify(repositoryMock, times(1)).findOne(updated.getId());
        verifyNoMoreInteractions(repositoryMock);
    }

    private void assertAccount(AccountDto expected, Account actual) {
        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.getFirstName(), actual.getFirstName());
        assertEquals(expected.getLastName(), expected.getLastName());
    }
    
}
