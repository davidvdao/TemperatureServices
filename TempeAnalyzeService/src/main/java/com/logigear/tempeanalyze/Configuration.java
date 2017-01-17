package com.logigear.tempeanalyze;

import java.util.Properties;

import org.restexpress.RestExpress;
import org.restexpress.util.Environment;

public class Configuration
extends Environment
{
	private static final String DEFAULT_EXECUTOR_THREAD_POOL_SIZE = "20";

	private static final String PORT_PROPERTY = "port";
	private static final String BASE_URL_PROPERTY = "base.url";
	private static final String EXECUTOR_THREAD_POOL_SIZE = "executor.threadPool.size";
        private static final String DATA_SERVICE_URI = "data.uri";
	private int port;
	private String baseUrl;
        private String dataServiceURI;
	private int executorThreadPoolSize;

	private SampleController sampleController;

	@Override
	protected void fillValues(Properties p)
	{
		this.port = Integer.parseInt(p.getProperty(PORT_PROPERTY, String.valueOf(RestExpress.DEFAULT_PORT)));
		this.baseUrl = p.getProperty(BASE_URL_PROPERTY, "http://localhost:" + String.valueOf(port));
		this.executorThreadPoolSize = Integer.parseInt(p.getProperty(EXECUTOR_THREAD_POOL_SIZE, DEFAULT_EXECUTOR_THREAD_POOL_SIZE));
                this.dataServiceURI = p.getProperty(DATA_SERVICE_URI, "http://localhost:" + String.valueOf(port));
		initialize();
	}

	private void initialize()
	{
		sampleController = new SampleController();
	}

	public int getPort()
	{
		return port;
	}
	
	public String getBaseUrl()
	{
		return baseUrl;
	}
        
        public String getDataUri()
	{
		return dataServiceURI;
	}
	
	public int getExecutorThreadPoolSize()
	{
		return executorThreadPoolSize;
	}

	public SampleController getSampleController()
	{
		return sampleController;
	}
}
