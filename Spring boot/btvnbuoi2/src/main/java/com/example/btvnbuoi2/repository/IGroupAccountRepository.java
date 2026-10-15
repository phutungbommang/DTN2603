
package com.example.btvnbuoi2.repository;

import com.example.btvnbuoi2.entity.GroupAccount;
import com.example.btvnbuoi2.entity.GroupAccountId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IGroupAccountRepository extends JpaRepository<GroupAccount, GroupAccountId> {
    List<GroupAccount> findByAccountId(Integer accountId);
    List<GroupAccount> findByGroupId(Integer groupId);
    boolean existsByGroupIdAndAccountId(Integer groupId, Integer accountId);
}
