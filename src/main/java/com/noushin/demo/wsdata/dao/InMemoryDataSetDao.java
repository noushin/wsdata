package com.noushin.demo.wsdata.dao;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.noushin.demo.wsdata.model.DataSet;

/**
 * A non-db implementation of a Data access object.
 * @author nbashir
 *
 */
@Component
public class InMemoryDataSetDao {
	
	private static final Logger logger = LoggerFactory.getLogger(InMemoryDataSetDao.class);
	
	private Map<String, DataSet> dataSet = null;
	
	public InMemoryDataSetDao() {
		dataSet = new HashMap<String, DataSet>();
	}
	
	public String saveSet(DataSet set) {
		String id = UUID.randomUUID().toString();
		if (set != null) {		
			dataSet.put(id, set);
		}
		logger.debug(String.format(">>> Data set[%s]: %s saved.", id, set.getContent()));
		return id;
	}

	public DataSet getSet(String id) {
		DataSet content = dataSet.get(id);
		if (content != null) {
			logger.debug(String.format(">>> Data set[%s]: %s was found.", id, content));
		}
		return content;
	}

	
}
