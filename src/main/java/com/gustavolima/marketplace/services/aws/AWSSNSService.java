package com.gustavolima.marketplace.services.aws;

import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.model.Topic;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class AWSSNSService {
    private AmazonSNS _amazonSNS;

    private Topic _catalogTopic;

    public AWSSNSService(AmazonSNS amazonSNS, @Qualifier("catalogEventsTopic") Topic topic) {
        _amazonSNS = amazonSNS;
        _catalogTopic = topic;
    }

    public void publish(MessageDTO messageDTO) {
        _amazonSNS.publish(_catalogTopic.getTopicArn(), messageDTO.toString());
    }
}
