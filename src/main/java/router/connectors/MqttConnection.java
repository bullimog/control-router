package router.connectors;

import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttException;

public interface MqttConnection extends MqttCallback {

    void openConnection(String brokerUrl, String clientId, boolean cleanSession) throws MqttException;
    void subscribe(String topicName, int qos) throws MqttException;
    void unsubscribe(String topicName)  throws MqttException;

}
