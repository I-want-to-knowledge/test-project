package com.geo.source.spring_simple.example7_chapter.impl;

import com.geo.source.spring_simple.example7_chapter.Singer7Dao;
import com.geo.source.spring_simple.example7_chapter.entity.Singer;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Hibernate框架测试
 *
 * @author YanZhen
 * @since 2019-06-17 14:24
 */
@Transactional
@Repository
public class Singer7DaoImpl implements Singer7Dao {

  private static Logger logger = LoggerFactory.getLogger(Singer7DaoImpl.class);
  private SessionFactory sessionFactory;

  public SessionFactory getSessionFactory() {
    return sessionFactory;
  }

  @Resource
  public void setSessionFactory(SessionFactory sessionFactory) {
    this.sessionFactory = sessionFactory;
  }

  @Transactional(readOnly = true)
  @Override
  public List<Singer> findAll() {
    return sessionFactory.getCurrentSession().createQuery("from Singer s").list();
  }

  @Transactional(readOnly = true)
  @Override
  public List<Singer> findAllWithAlbum() {
    return sessionFactory.getCurrentSession().createNamedQuery("Singer.findAllWithAlbum").list();
  }

  @Transactional(readOnly = true)
  @Override
  public Singer findById(Long id) {
    return (Singer) sessionFactory.getCurrentSession()
            .createNamedQuery("Singer.findById").setParameter("id", id).uniqueResult();
  }

  @Override
  public Singer save(Singer singer) {
    sessionFactory.getCurrentSession().saveOrUpdate(singer);
    logger.info("Singer saved with id: {}", singer.getId());
    return singer;
  }

  @Override
  public void delete(Singer singer) {
    sessionFactory.getCurrentSession().delete(singer);
    logger.info("Singer deleted with id: {}", singer.getId());
  }
}
