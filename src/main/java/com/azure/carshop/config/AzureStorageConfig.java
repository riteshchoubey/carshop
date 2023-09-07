package com.azure.carshop.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "azure.storage")
public class AzureStorageConfig {
	
		@Value("${azure.storage.accountName}")
	  	private String accountName;
		@Value("${azure.storage.accountKey}")
	    private String accountKey;
		public String getAccountName() {
			return accountName;
		}
		public void setAccountName(String accountName) {
			this.accountName = accountName;
		}
		public String getAccountKey() {
			return accountKey;
		}
		public void setAccountKey(String accountKey) {
			this.accountKey = accountKey;
		}

}
