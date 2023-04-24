package shop.mtcoding.servicebank.model.transaction;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    
    // 입금 내역
    @Query("select ts from Transaction ts where ts.depositAccount.number = :depositAccountNumber")
    List<Transaction> findByDeposit(@Param("depositAccountNumber") Integer depositAccountNumber);

    List<Transaction> findByDepositAccount_Number(Integer depositAccount_number);

    // 출금 내역
    @Query("select ts from Transaction ts where ts.withdrawAccount.number = :withdrawAccountNumber")
    List<Transaction> findByWithdraw(@Param("withdrawAccountNumber") Integer withdrawAccountNumber);

    List<Transaction> findByWithdrawAccount_Number(Integer withdrawAccount_number);

    // 입출금 내역
    @Query("select ts from Transaction ts where ts.withdrawAccount.number = :number or ts.depositAccount.number = :number")
    List<Transaction> findByDepositAndWithdraw(@Param("number") Integer number);

    List<Transaction> findByDepositAccount_NumberOrWithdrawAccount_Number(Integer depositAccount_number, Integer withdrawAccount_number);
}
