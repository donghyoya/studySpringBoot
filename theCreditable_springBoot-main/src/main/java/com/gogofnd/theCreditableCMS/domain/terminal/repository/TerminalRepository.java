package com.gogofnd.theCreditableCMS.domain.terminal.repository;

import com.gogofnd.theCreditableCMS.domain.terminal.entity.Terminal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TerminalRepository extends JpaRepository<Terminal,Long> {

    boolean existsByTid(int tid);
}
