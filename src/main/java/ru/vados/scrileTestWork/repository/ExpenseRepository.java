package ru.vados.scrileTestWork.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.vados.scrileTestWork.model.EmptyFakeEntityForAccessToJpa;

import java.util.List;

public interface ExpenseRepository extends JpaRepository<EmptyFakeEntityForAccessToJpa,Long> {

    @Query(value = "select * from expenses e where e.userid = :id and e.amount > :minSum",nativeQuery = true)
    List<ExpensProjection> getAllExpenseMoreMinSum(@Param("id") Integer id,@Param("minSum") Integer minSum);

}
