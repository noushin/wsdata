package com.noushin.demo.wsdata.service;

import javax.ws.rs.NotFoundException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.noushin.demo.wsdata.dao.DataSetDao;
import com.noushin.demo.wsdata.dao.InMemoryDataSetDao;
import com.noushin.demo.wsdata.model.DataSet;


@Service("dataSetService")
public class DataSetServiceImpl implements DataSetService {
	
	private final static Logger logger = LoggerFactory.getLogger(DataSetServiceImpl.class);

	@Autowired
	private InMemoryDataSetDao inMemoryDataSetDao; //simple map in memory
	
	@Autowired
	@Qualifier("dataSetDao")
	private DataSetDao dataSetDao; // uses a database
	
	@Override
	public Integer saveContent(DataSet set) {
		String id = inMemoryDataSetDao.saveSet(set);
		logger.debug("Saved in memory, added to map: " + id);
		
		DataSet dbSet = dataSetDao.makePersistent(set);
		if (dbSet != null)
			logger.debug("Saved to database.");
		return dbSet.getId();
	}

	@Override
	public DataSet getContent(Integer id) {
		DataSet content = dataSetDao.findById(id);
		if (content != null) {
				return content;
		}
		throw new NotFoundException(); // let JAX-RS map exception to HTTP status code and populate response.
	}

	@Override
	public String ping() {
		return "Service is available.";
	}

}
