package com.makingdevs.jms;

import com.makingdevs.model.Project;

public interface MyMessageProducer {
	void heavyOperationForDelegationAndProcessing(Project project);
}
