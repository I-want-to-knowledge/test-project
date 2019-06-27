package com.geo.source.spring_simple.example8_chapter.impl;

import com.geo.source.spring_simple.example8_chapter.SingerService;
import com.geo.source.spring_simple.example8_chapter.entity.Singer;
import com.geo.source.spring_simple.example8_chapter.entity.Singer_;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.*;
import java.util.List;

/**
 * jpa 2 数据访问，服务
 *
 * @author YanZhen
 * @since 2019-06-18 16:06
 */
@Service
@Repository
public class SingerServiceImpl implements SingerService {
  private static final String ALL_SINGER_NATIVE_QUERY = "select id, first_name, last_name, birth_date, version from singer";
  private Logger logger = LoggerFactory.getLogger(SingerServiceImpl.class);

  @PersistenceContext
  private EntityManager em;

  @Override
  public List<Singer> findAll() {
    return em.createNamedQuery(Singer.FIND_ALL, Singer.class).getResultList();
  }

  @Override
  public List<Singer> findAllWithAlbum() {
    return em.createNamedQuery(Singer.FIND_ALL_WITH_ALBUM, Singer.class).getResultList();
  }

  @Override
  public Singer findById(Long id) {
    return em.createNamedQuery(Singer.FIND_BY_ID, Singer.class)
            .setParameter("id", id)
            .getSingleResult();
  }

  @Transactional
  @Override
  public Singer save(Singer singer) {
    if (singer != null) {
      if (singer.getId() == null) {
        logger.info("Inserting new singer");
        em.persist(singer);
      } else {
        em.merge(singer);
        logger.info("Updating existing singer");
      }
    }
    return singer;
  }

  @Transactional
  @Override
  public void delete(Singer singer) {
    final Singer merge = em.merge(singer);
    em.remove(merge);
    logger.info("Singer with id: {} deleted successfully", singer.getId());
  }

  @Override
  public List<Singer> findAllByNativeQuery() {
    return em.createNativeQuery(ALL_SINGER_NATIVE_QUERY, Singer.class).getResultList();
  }

  @Override
  public List displayAllSingerSummary() {
    return em.createQuery("select s.firstName, s.lastName, s.birthDate, a.title" +
            " from Singer s left join s.albums a where a.releaseDate=" +
            "(select max(a2.releaseDate) from Album a2 where a2.singer.id=s.id)").getResultList();
  }

  @Override
  public List<Singer> findByCriteriaQuery(String firstName, String lastName) {
    logger.info("Finding singer for firstName: {} and lastName: {}", firstName, lastName);
    final CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
    final CriteriaQuery<Singer> query = criteriaBuilder.createQuery(Singer.class);
    final Root<Singer> singerRoot = query.from(Singer.class);
    singerRoot.fetch(Singer_.albums, JoinType.LEFT);// 错误忽略
    singerRoot.fetch(Singer_.instruments, JoinType.LEFT);// 错误忽略

    query.select(singerRoot).distinct(true);
    Predicate conjunction = criteriaBuilder.conjunction();
    if (firstName != null) {
      conjunction = criteriaBuilder.and(conjunction,
              criteriaBuilder.equal(singerRoot.get(Singer_.firstName), firstName));// 错误忽略
    }

    if (lastName != null) {
      conjunction = criteriaBuilder.and(conjunction,
              criteriaBuilder.equal(singerRoot.get(Singer_.lastName), lastName));// 错误忽略
    }

    query.where(conjunction);
    return em.createQuery(query).getResultList();
  }
}
