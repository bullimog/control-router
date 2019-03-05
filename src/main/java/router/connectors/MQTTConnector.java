package router.connectors;

import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;

public interface MQTTConnector extends MqttCallback {

    void subscribe(String topicName, int qos) throws MqttException;
    void unsubscribe(String topicName)  throws MqttException;


    //        String broker = "test.mosquitto.org";
    //        String broker 		= "localhost";
    //        String topic = "#";


    public static MQTTConnector getConnector(String broker) throws MqttException {
        //	String protocol = "ssl://";
        String protocol = "tcp://";
        return getConnector(broker, 1883, protocol );
    }

    public static MQTTConnector getConnector(String broker, int port,
                                             String protocol ) throws MqttException{

        String clientId = MqttClient.generateClientId();
        String url = protocol + broker + ":" + port;

        return  new MQTTConnectorImpl(url, clientId, true);

    }

}
