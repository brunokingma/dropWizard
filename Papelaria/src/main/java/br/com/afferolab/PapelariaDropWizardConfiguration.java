package br.com.afferolab;

import io.dropwizard.Configuration;
import io.dropwizard.bundles.assets.AssetsBundleConfiguration;
import io.dropwizard.bundles.assets.AssetsConfiguration;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.Valid;
import javax.validation.constraints.*;
import javax.validation.constraints.NotEmpty;

public class PapelariaDropWizardConfiguration extends Configuration implements AssetsBundleConfiguration {
    
	@Valid
    @NotNull
    @JsonProperty
    private final AssetsConfiguration assets = AssetsConfiguration.builder().build();
	
	@Override
	public AssetsConfiguration getAssetsConfiguration() {
		return assets;
	}
    
	@NotEmpty
	private String dbName,collName, host;
	
	@NotNull
	private int port;
	
    @JsonProperty
	public String getDbName() {
		return dbName;
	}
    
    @JsonProperty
	public void setDbName(String dbName) {
		this.dbName = dbName;
	}
    
    @JsonProperty
	public String getCollName() {
		return collName;
	}
    
    @JsonProperty
	public void setCollName(String collName) {
		this.collName = collName;
	}
    
    @JsonProperty
	public String getHost() {
		return host;
	}
    
    @JsonProperty
	public void setHost(String host) {
		this.host = host;
	}
    
    @JsonProperty
	public int getPort() {
		return port;
	}
    
    @JsonProperty
	public void setPort(int port) {
		this.port = port;
	}


	

}
