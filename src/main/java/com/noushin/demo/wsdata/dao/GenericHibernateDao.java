package com.noushin.demo.wsdata.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Example;
import org.springframework.transaction.annotation.Transactional;

/**
 * Hibernate specific implementation of CRUD operations.
 *
 * @author nbashir
 *
 * @param <T>
 *            Business Entity to persist
 * @param <ID>
 *            Entity Identifier
 */
public abstract class GenericHibernateDao<T, ID extends Serializable> implements GenericDao<T, ID> {

	private Class<T> persistentClass;
	private Session session;
	protected SessionFactory sessionFactory;

// uncomment when you are ready log messages for this class.	
//	private final static Logger logger = LoggerFactory.getLogger(GenericHibernateDao.class);

	@SuppressWarnings("unchecked")
	public GenericHibernateDao() {
		this.persistentClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}

	public void setSession(Session session) {
		this.session = session;
	}

	/*
	 * When you get a Session with sessionFactory.getCurrentSession(), the Session is flushed and closed automatically
	 * when the transaction is committed.
	 */
	protected Session getSession() {
		if ((session != null) && (session.isOpen())) {
			return session;
		}
		else {
			setSession(sessionFactory.getCurrentSession());
			return session;
		}
	}

	@Resource(name = "sessionFactory")
	public void setSessionFactory(SessionFactory sessFactory) {
		this.sessionFactory = sessFactory;
	}

	public Class<T> getPersistentClass() {
		return persistentClass;
	}

	@Override
	@Transactional
	public T findById(ID id) {
		T entity;
		entity = (T) getSession().get(getPersistentClass(), id);
		return entity;
	}

	@Override
	@Transactional
	public List<T> findAll() {
		return findByCriteria();
	}

	@Override
	@SuppressWarnings("unchecked")
	@Transactional
	public List<T> findByExample(T exampleInstance, String[] excludeProperty) {
		Example example = Example.create(exampleInstance);
		if ((excludeProperty != null) && (excludeProperty.length > 0)) {
			for (String exclude : excludeProperty) {
				example.excludeProperty(exclude);
			}
		}
		Criteria criteria = getSession().createCriteria(getPersistentClass());
		criteria.add(example);
		return criteria.list();
	}

	@Override
	@Transactional
	public T makePersistent(T entity) {
		if (entity != null) {
			getSession().saveOrUpdate(entity);
		}
		return entity;
	}

	@Override
	@Transactional
	public void makeTransient(T entity) {
		if (entity != null) {
			getSession().delete(entity);
		}
	}

	public void flush() {
		getSession().flush();
	}

	public void clear() {
		getSession().clear();
	}

	@SuppressWarnings("unchecked")
	protected List<T> findByCriteria(Criterion... criterion) {
		Criteria crit = getSession().createCriteria(getPersistentClass());
		for (Criterion c : criterion) {
			crit.add(c);
		}
		return crit.list();
	}

}
