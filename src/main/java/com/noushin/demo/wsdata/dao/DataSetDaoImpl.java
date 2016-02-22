package com.noushin.demo.wsdata.dao;

import org.springframework.stereotype.Component;

import com.noushin.demo.wsdata.model.DataSet;

/**
 * Hibernate specific implementation of additional database related operations for a specific business object.
 *
 * @author nbashir
 *
 * @param <T>
 *            Business Entity to persist
 * @param <ID>
 *            Entity Identifier
 */
@Component("dataSetDao")
public class DataSetDaoImpl extends GenericHibernateDao<DataSet, Integer> implements DataSetDao {
	// uncomment when ready to add more methods and need to log.
	//	private final static Logger logger = LoggerFactory.getLogger(DataSetDaoImpl.class);

}
