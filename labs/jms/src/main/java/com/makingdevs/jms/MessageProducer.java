package com.makingdevs.jms;

import com.makingdevs.model.Project;

public interface MessageProducer {
	void heavyOperationForDelegationAndProcessing(Project project);
}
