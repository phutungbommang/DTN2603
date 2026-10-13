
package com.example.btvnbuoi2.repository;

import com.example.btvnbuoi2.entity.GroupAccount;
import com.example.btvnbuoi2.entity.GroupAccountId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IGroupAccountRepository extends JpaRepository<GroupAccount, GroupAccountId> {
    List<GroupAccount> findByAccount_Id(Integer accountId);
    List<GroupAccount> findByGroup_Id(Integer groupId);
    boolean existsByGroup_IdAndAccount_Id(Integer groupId, Integer accountId);
}
