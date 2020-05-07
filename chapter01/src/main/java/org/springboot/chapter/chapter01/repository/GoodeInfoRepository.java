package org.springboot.chapter.chapter01.repository;

import org.springboot.chapter.chapter01.entity.GoodInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;


public interface GoodeInfoRepository extends JpaRepository<GoodInfoEntity,Long> {
}
