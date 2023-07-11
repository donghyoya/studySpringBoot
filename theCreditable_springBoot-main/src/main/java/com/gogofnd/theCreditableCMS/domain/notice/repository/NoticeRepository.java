package com.gogofnd.theCreditableCMS.domain.notice.repository;

import com.gogofnd.theCreditableCMS.domain.notice.entity.Notice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoticeRepository extends JpaRepository<Notice,Long> {
}
